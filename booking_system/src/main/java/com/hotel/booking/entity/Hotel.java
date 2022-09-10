package com.hotel.booking.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "hotel_master")
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hotelId;
	
	@NotBlank(message = "Hotel name should not be null !!")
	private String hotelName;
	
	@NotBlank(message = "Please define hotel is in which city !!")
	private String city;
	
	private Boolean wifi;
	
	private Boolean restaurantAvailable;
	
	private Boolean airConditioning;
	
	private Boolean meal;
	
	@Min(value = 1, message = "Please define how many rooms are availabe in your hotel !!")
	private Integer totalRoom;
	
	@DecimalMin(value = "1.0", message = "Please define what is the cost of leaving in your hotel !!") 
	private Double costOfLeaving;

	@OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
	@JoinColumn(name = "userId", unique = true)
	private User user;

	@JsonManagedReference(value = "hotel_review")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
	private Set<Review> reviews;

	public Hotel() {

	}

	public Hotel(Integer hotelId, String hotelName, String city, boolean wifi, boolean restaurantAvailable,
			boolean airConditioning, boolean meal, Integer totalRoom, Double costOfLeaving, User user,
			Set<Review> reviews) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.city = city;
		this.wifi = wifi;
		this.restaurantAvailable = restaurantAvailable;
		this.airConditioning = airConditioning;
		this.meal = meal;
		this.totalRoom = totalRoom;
		this.costOfLeaving = costOfLeaving;
		this.user = user;
		this.reviews = reviews;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isRestaurantAvailable() {
		return restaurantAvailable;
	}

	public void setRestaurantAvailable(boolean restaurantAvailable) {
		this.restaurantAvailable = restaurantAvailable;
	}

	public boolean isAirConditioning() {
		return airConditioning;
	}

	public void setAirConditioning(boolean airConditioning) {
		this.airConditioning = airConditioning;
	}

	public boolean isMeal() {
		return meal;
	}

	public void setMeal(boolean meal) {
		this.meal = meal;
	}

	public Integer getTotalRoom() {
		return totalRoom;
	}

	public void setTotalRoom(Integer totalRoom) {
		this.totalRoom = totalRoom;
	}

	public Double getCostOfLeaving() {
		return costOfLeaving;
	}

	public void setCostOfLeaving(Double costOfLeaving) {
		this.costOfLeaving = costOfLeaving;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

}
