package com.lcwd.rating.service.entities;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "micro_rating")
public class Rating {

	@Id
	@Column(name = "ID")
	private String ratingId;
	private String hotelId;
	private String userId;
	private int rating;
	private String feedback;

}
