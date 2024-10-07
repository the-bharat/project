package com.zykaExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zykaExpress.dto.RatingDto;
import com.zykaExpress.service.RatingServiceImpl;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	private RatingServiceImpl ratingService;
	
	@PostMapping("/add")
	public ResponseEntity<?> AddRating(@RequestBody RatingDto rating) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.addRating(rating));	
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> DeleteRating(@PathVariable int id) {
		return ResponseEntity.ok(ratingService.deleteRating(id));
	}
	
	@PutMapping("/edit/{ratingId}")
	public ResponseEntity<?> editRating(@RequestBody RatingDto rating , @PathVariable int ratingId){
		return ResponseEntity.ok().body(ratingService.editRating(rating, ratingId));
	}
}
