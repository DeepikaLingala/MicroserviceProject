package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwd.user.service.entities.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	// get

	// post
	@PostMapping("/ratings")
	Rating creatingRating(Rating values);

	// put
	@PutMapping("/ratings/{ratingId}")
	Rating UpdateRating(@PathVariable String ratingId, Rating values);
	
	//delete
	@DeleteMapping("/ratings/{ratingId}")
	void deleteRating(@PathVariable String ratingId);

}
