package com.hotel.booking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.booking.entity.User;
import com.hotel.booking.exception.RoleNotFoundException;
import com.hotel.booking.model.UserModel;
import com.hotel.booking.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@Valid @RequestBody UserModel userModel) throws RoleNotFoundException {
		User user = userService.addUpdateUser(userModel);
		return ResponseEntity.ok(user);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@Valid @RequestBody UserModel userModel) throws RoleNotFoundException {
		User user = userService.addUpdateUser(userModel);
		return ResponseEntity.ok(user);
	}
}
