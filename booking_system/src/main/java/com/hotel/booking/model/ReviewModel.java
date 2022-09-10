package com.hotel.booking.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewModel {
	private Integer reviewId;
	private String comment;
	private Integer rating;
	private Integer hotelId;
	private Date ratingDate;

}
