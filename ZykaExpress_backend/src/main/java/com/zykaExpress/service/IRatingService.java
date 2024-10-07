package com.zykaExpress.service;

import com.zykaExpress.dto.RatingDto;
import com.zykaExpress.entities.Rating;

public interface IRatingService {

	Rating addRating(RatingDto rating);

	Rating editRating(RatingDto rating, int ratingId);

	String deleteRating(int userId);
}
