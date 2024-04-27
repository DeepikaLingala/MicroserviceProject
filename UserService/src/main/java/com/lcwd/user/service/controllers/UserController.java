package com.lcwd.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import com.lcwd.user.service.services.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	// create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}

	int retryCount = 1;

	// get single user
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	
	@RateLimiter(name = "userRateLimitter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleuser(@PathVariable String userId) {
		logger.info("Retry count :{}", retryCount);
		retryCount++;
		User getUser = userService.getUser(userId);
		return ResponseEntity.ok(getUser);
	}

	// creating fallback method for circuitBreaker

	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
		// logger.info("FallBack is executed because service is down:",ex.getMessage());

		User user = User.builder().email("dummy@gmail.com").name("Dummy")
				.about("This user is created dummy because some service is doewn").userId("121212121").build();
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	// get all user
	@GetMapping()
	public ResponseEntity<List<User>> getAlluser() {
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
}
