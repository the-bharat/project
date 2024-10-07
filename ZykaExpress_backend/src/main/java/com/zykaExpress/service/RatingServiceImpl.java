 package com.zykaExpress.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zykaExpress.custom_excpetions.ResourceNotFoundException;
import com.zykaExpress.dto.RatingDto;
import com.zykaExpress.entities.Rating;
import com.zykaExpress.entities.User;
import com.zykaExpress.repository.RatingRepository;
import com.zykaExpress.repository.UserRepository;

@Service
@Transactional

public class RatingServiceImpl implements IRatingService {

	@Autowired
	private RatingRepository ratingDao;
	@Autowired
	private UserRepository userDao;

	@Override
	public Rating addRating(RatingDto rating) {
		User customer = userDao.findById(rating.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials !!"));
		User restaurant = userDao.findById(rating.getRestId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials !!"));

		Rating rating1 = new Rating(rating.getRating(), rating.getComment(), restaurant, customer);
		return ratingDao.save(rating1);
	}

	@Override
	public Rating editRating(RatingDto rating, int ratingId) {
		Rating rating1 = ratingDao.findById(ratingId)
				.orElseThrow(() -> new ResourceNotFoundException("rating not found"));

		rating1.setRating(rating.getRating());
		rating1.setComment(rating.getComment());
		return rating1;
	}

	@Override
	public String deleteRating(int ratingId) {

		ratingDao.deleteById(ratingId);
		return "success";
	}
}
