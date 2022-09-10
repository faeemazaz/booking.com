package com.hotel.booking.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user_master")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotBlank(message = "Username should not be null !!")
	private String userName;
	
	@Email(message = "Email is not valid !!")
	private String email;
	
	@NotBlank(message = "Password should not be null !!")
	private String pasword;
	
	@NotBlank(message = "Please enter your contact number !!")
	private String contact;
	
	@NotBlank(message = "Please define your gender !!")
	private String gender;
	
	@NotBlank(message = "Please define your city !!")
	private String city;
	
	@NotBlank(message = "Please define your state !!")
	private String state;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Hotel hotel;

	@JsonBackReference(value = "user_role")
	@ManyToOne(targetEntity = Role.class)
	@JoinColumn(name = "roleId")
	private Role role;

	@JsonManagedReference(value = "user_review")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Review> reviews;

	public User() {

	}

	public User(Integer userId, String userName, String email, String pasword, String contact, String gender,
			String city, String state, Hotel hotel, Role role, Set<Review> reviews) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.pasword = pasword;
		this.contact = contact;
		this.gender = gender;
		this.city = city;
		this.state = state;
		this.hotel = hotel;
		this.role = role;
		this.reviews = reviews;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

}
