package com.lcwd.hotel.service.services;

import java.util.List;

import com.lcwd.hotel.service.entities.Hotel;

public interface HotelService {

	// create hotel
	Hotel create(Hotel hotel);

	// get all Hotels
	List<Hotel> getAllHotels();

	// get single hotel
	Hotel getHotelById(String id);

}
