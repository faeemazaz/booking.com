package com.hotel.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	private Integer userId;
	private String userName;
	private String email;
	private String pasword;
	private String contact;
	private String gender;
	private String city;
	private String state;
	private Integer roleId;
}
