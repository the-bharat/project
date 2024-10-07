package com.zykaExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zykaExpress.dto.ResponseDto;
import com.zykaExpress.service.UserServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserServiceImpl userService;

	// get all customers
	@GetMapping("/getAllCustomer")
	public ResponseEntity<?> getAllCustomer() {
		return ResponseEntity.ok().body(new ResponseDto<>("Success", userService.getAllCustomer()));
	}

	// get all deliveryBoys
	@GetMapping("/getAllDeliveryBoy")
	public ResponseEntity<?> getAllDeliveryBoy() {
		return ResponseEntity.ok().body(new ResponseDto<>("Success", userService.getAllDeliveryBoy()));
	}

	// get all restaurants 
	@GetMapping("/getAllRestaurant")
	public ResponseEntity<?> getAllRestaurants() {
		return ResponseEntity.ok().body(new ResponseDto<>("Success", userService.getAllRestaurant()));
	}
	
	@PutMapping("/changeStatus/{id}/{val}")
	public ResponseEntity<?> updateStatusOfUser(@PathVariable int id,@PathVariable int val) {
		return ResponseEntity.ok().body(new ResponseDto<>("Success", userService.updateStatusOfUser(id,val)));
	}
}