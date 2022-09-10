package com.hotel.booking.service;

import com.hotel.booking.entity.User;
import com.hotel.booking.exception.RoleNotFoundException;
import com.hotel.booking.model.UserModel;

public interface UserService {
	User addUpdateUser(UserModel userModel) throws RoleNotFoundException;
}
