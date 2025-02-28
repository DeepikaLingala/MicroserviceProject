package com.lcwd.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFondException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
		// implementing RATING SERVICE CALL using RestTemplate
	}

	@Override
	public User getUser(String userId) {
		// get user from db with the help of user repository.
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFondException("User with given id is not found on server !! " + userId));
		// fetch rating of above user from RATING SERVICE
		// http://RATING-SERVICE/ratings/users/6d48cfc6-5a55-421c-9c48-fd6de801dd1e
		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
				Rating[].class);

		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
		// user.setRatings(ratingOfUser);
		logger.info("{}", ratingOfUser);

		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get hotel
			// http://HOTEL-SERVICE/hotels/bef9ddee-b290-47a2-abad-53e35b2ab99c
//			ResponseEntity<Hotel> forEntity = restTemplate
//					.getForEntity("http://HOTEL-SERIVCE/hotels/" + rating.getHotelId(), Hotel.class);
			//Hotel hotel = forEntity.getBody();
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			//logger.info("Response Code", forEntity.getStatusCode());
			// set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

}
