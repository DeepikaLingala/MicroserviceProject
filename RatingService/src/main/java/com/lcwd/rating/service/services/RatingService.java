package com.lcwd.rating.service.services;

import java.util.List;

import com.lcwd.rating.service.entities.Rating;

public interface RatingService {

	// create rating
	Rating create(Rating raating);

	// get all rating
	List<Rating> getAllRatings();

	// get all rating by userId
	List<Rating> getRatingByUserId(String userId);

	// get all rating by hotelId
	List<Rating> getRatingByhotelId(String hotelId);

}
