package com.lcwd.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {
	
	@Autowired
	private RatingService ratingService;
	
	@Test
	void contextLoads() {
	}
//	@Test
//	void creatingRating() {
//		Rating rating = Rating.builder().rating(10).userId("abc").ratingId("def").feedback("This is created using feign Client").build();
//		Rating creatingRating = ratingService.creatingRating(rating);
//		System.out.println("++++++++++ New rating creating");
//	}
}
