package com.hotel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	User findByUserName(String userName);
	
}
