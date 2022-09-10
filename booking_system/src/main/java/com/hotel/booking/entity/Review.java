package com.hotel.booking.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "review_master")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewId;
	private String comment;
	
	@Size(min = 1, max = 10, message = "Please enter rating between 1 to 10 !!")
	private Integer rating;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "Please enter rating date !!")
	private Date ratingDate;

	@JsonBackReference(value = "hotel_review")
	@ManyToOne(targetEntity = Hotel.class)
	@JoinColumn(name = "hotelId")
	private Hotel hotel;

	@JsonBackReference(value = "user_review")
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userId")
	private User user;

	public Review(Integer reviewId, String comment, Integer rating, Date ratingDate, boolean status, Hotel hotel,
			User user) {
		this.reviewId = reviewId;
		this.comment = comment;
		this.rating = rating;
		this.ratingDate = ratingDate;
		this.hotel = hotel;
		this.user = user;
	}

	public Review() {

	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Date getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
