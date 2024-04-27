package com.lcwd.hotel.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_hotel")
public class Hotel {

	@Id
	@Column(name = "ID")
	private String hotelId;

	@Column(name = "HOTELNAME")
	private String hotelName;

	@Column(name = "LOCALTION")
	private String location;

	@Column(name = "ABOUT")
	private String about;

}
