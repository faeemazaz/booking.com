package com.hotel.booking;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hotel.booking.entity.Role;
import com.hotel.booking.repository.RoleRepo;

@SpringBootApplication
public class BookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingSystemApplication.class, args);
	}

	@Autowired
	private RoleRepo roleRepo;

	@PostConstruct
	private void insertRole() {

		/*
		 * On application startup ROLE_ADMIN, ROLE_USER automatically created
		 */
		List<Role> roles = new ArrayList<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setAuthGroup("ROLE_ADMIN");

		Role role2 = new Role();
		role2.setRoleId(2);
		role2.setAuthGroup("ROLE_USER");

		roles.add(role1);
		roles.add(role2);

		for (Role role : roles) {
			roleRepo.save(role);
		}

	}

}
