package com.zykaExpress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zykaExpress.custom_excpetions.ResourceNotFoundException;
import com.zykaExpress.dto.LoginRequestDto;
import com.zykaExpress.dto.UserDetailsDto;
import com.zykaExpress.entities.Status;
import com.zykaExpress.entities.User;
import com.zykaExpress.repository.UserRepository;


@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	// Update User Details
	@Override
	public User updateUserProfile(UserDetailsDto userDto, int userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User !!! Can not update details"));
		//System.out.println(userDto);
		user.setContact(userDto.getContact_no());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(encoder.encode(user.getPassword()));
		return user;
	}

	// Authenticate User
	@Override
	public User authenticateUser(LoginRequestDto loginRequest) {

		return userRepo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials !!"));
	}

	// Register User
	@Override
	public User registerUser(User user) {

		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus(Status.ACTIVE);
		return userRepo.save(user);
	}

	// Get User Details
	@Override
	public User getUserDetails(Integer id) {

		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found !!!"));
	}

//	@Override
//	public User forgotPassword(ForgotPasswordDto forgetPassword) {
//		User user = userRepo.findByEmail(forgetPassword.getEmail())
//				.orElseThrow(() -> new ResourceNotFoundException("Invalid User !!! Can not upadate details"));
//		user.setPassword(forgetPassword.getPassword());
//		;
//
//		return user;
//	}

	@Override
	public List<User> getAllCustomer() {

		return userRepo.findAllCustomer();
	}

	@Override
	public List<User> getAllDeliveryBoy() {

		return userRepo.findAllDeliveryBoy();
	}

	@Override
	public List<User> getAllRestaurant() {

		return userRepo.findAllRestaurant();
	}

	@Override
	public User getUserDetailsByEmail(String username) {
		return userRepo.findByEmail(username).orElseThrow(()->new RuntimeException("User Not Found"));
	}

	public User updateStatusOfUser(int id, int val) {
		
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found !!!"));
		if (val==1) {
			user.setStatus(Status.INACTIVE);
		}
		else {
			user.setStatus(Status.ACTIVE);
		}
		return user;
	}

	@Override
	public List<User> getAllActiveRestaurnts() {
		return userRepo.findByActiveRestaurent();
	}
}