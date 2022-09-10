package com.hotel.booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.booking.entity.Role;
import com.hotel.booking.entity.User;
import com.hotel.booking.exception.RoleNotFoundException;
import com.hotel.booking.model.UserModel;
import com.hotel.booking.repository.RoleRepo;
import com.hotel.booking.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User addUpdateUser(UserModel userModel) throws RoleNotFoundException {
		Optional<Role> roleRef = roleRepo.findById(userModel.getRoleId());

		User user = new User();
		if (roleRef.isPresent()) {
			user.setUserId(userModel.getUserId());
			user.setUserName(userModel.getUserName());
			user.setEmail(userModel.getEmail());
			user.setPasword(bCryptPasswordEncoder.encode(userModel.getPasword()));
			user.setContact(userModel.getContact());
			user.setGender(userModel.getGender());
			user.setCity(userModel.getCity());
			user.setState(userModel.getState());
			user.setRole(roleRef.get());
		} else {
			throw new RoleNotFoundException("Role is not found. Add 1 for Admin and add 2 for User !!");
		}

		return userRepo.save(user);
	}

}
