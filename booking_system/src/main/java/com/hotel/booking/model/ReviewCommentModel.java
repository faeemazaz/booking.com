package com.hotel.booking.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCommentModel {
	private String userName;
	private String city;
	private String gender;
	private Integer rating;
	private String comment;
	private Date ratingDate;

}
