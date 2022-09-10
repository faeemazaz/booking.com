package com.hotel.booking.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.booking.entity.Hotel;
import com.hotel.booking.model.SearchHotelModel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {

	@Query("SELECT new com.hotel.booking.model.SearchHotelModel(h.hotelName, h.city, h.wifi, h.restaurantAvailable, h.airConditioning, h.meal, r.rating, h.costOfLeaving) FROM Review r RIGHT JOIN r.hotel h WHERE h.city = ?1")
	public Page<SearchHotelModel> searchHotel(Pageable pageable, String city);

	@Query("SELECT new com.hotel.booking.model.SearchHotelModel(h.hotelName, h.city, h.wifi, h.restaurantAvailable, h.airConditioning, h.meal, r.rating, h.costOfLeaving) FROM Review r RIGHT JOIN r.hotel h WHERE h.city = ?1")
	public List<SearchHotelModel> searchHotel(String city);

}
