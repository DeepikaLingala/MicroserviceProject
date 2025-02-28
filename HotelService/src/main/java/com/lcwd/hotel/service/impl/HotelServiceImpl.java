package com.lcwd.hotel.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.hotel.service.entities.Hotel;
import com.lcwd.hotel.service.exception.ResourceNotFoundException;
import com.lcwd.hotel.service.repositories.HotelRepository;
import com.lcwd.hotel.service.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel hotel) {
		String randomUUID = UUID.randomUUID().toString();
		hotel.setHotelId(randomUUID);
		Hotel save = hotelRepository.save(hotel);
		return save;
	}

	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> all = hotelRepository.findAll();
		return all;
	}

	@Override
	public Hotel getHotelById(String id) {
		return hotelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));
	}

}
