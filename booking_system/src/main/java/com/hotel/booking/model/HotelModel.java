package com.hotel.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelModel {
	private Integer hotelId;
	private String hotelName;
	private String city;
	private boolean wifi;
	private boolean restaurantAvailable;
	private boolean airConditioning;
	private boolean meal;
	private Integer totalRoom;
	private Double costOfLeaving;
	private Integer userId;
}
