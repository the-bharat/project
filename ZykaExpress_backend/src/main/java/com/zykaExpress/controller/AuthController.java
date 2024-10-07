package com.zykaExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zykaExpress.dto.JwtResponse;
import com.zykaExpress.dto.LoginRequestDto;
import com.zykaExpress.dto.ResponseDto;
import com.zykaExpress.entities.User;
import com.zykaExpress.security.JwtHelper;
import com.zykaExpress.service.IUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private IUserService userService;


    @Autowired
    private JwtHelper helper;

    //private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {

    	System.out.println(request.getEmail()+request.getPassword());
    	
        this.doAuthenticate(request.getEmail(), request.getPassword());

        
        System.out.println("hii");
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        System.out.println("hii");
        String token = this.helper.generateToken(userDetails);
        
        User user = userService.getUserDetailsByEmail(userDetails.getUsername());

        JwtResponse response =  new JwtResponse(user,token) ;
        return new ResponseEntity<>(new ResponseDto<>("Success",response),HttpStatus.OK);
    }
    
    @PostMapping("/signup")
	public ResponseEntity<?> SignUp(@RequestBody User user) {

		return ResponseEntity.ok().body(new ResponseDto<>("Success", userService.registerUser(user)));
	}

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
