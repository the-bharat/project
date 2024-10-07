package com.zykaExpress.service;

import java.util.List;

import com.zykaExpress.dto.LoginRequestDto;
import com.zykaExpress.dto.UserDetailsDto;
import com.zykaExpress.entities.User;

public interface IUserService {

	User registerUser(User user);

	User authenticateUser(LoginRequestDto loginRequest);

	User updateUserProfile(UserDetailsDto userDto, int userId);

	User getUserDetails(Integer id);

	//User forgotPassword(ForgotPasswordDto forgetPassword);

	List<User> getAllCustomer();

	List<User> getAllDeliveryBoy();

	List<User> getAllRestaurant();

	User getUserDetailsByEmail(String username);
	
	User updateStatusOfUser(int id, int val);
	
	List<User> getAllActiveRestaurnts();
}
