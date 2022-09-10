package com.hotel.booking.service;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hotel.booking.entity.Hotel;
import com.hotel.booking.exception.HotelNotFoundException;
import com.hotel.booking.exception.UnAuthorizedException;
import com.hotel.booking.model.SearchHotelModel;

public interface HotelService {
	Hotel addUpdateHotel(Hotel hotel, Principal principal) throws SQLIntegrityConstraintViolationException;

	Page<SearchHotelModel> searchHotelByFilterRating(Pageable pageable, String city) throws HotelNotFoundException;

	List<SearchHotelModel> searchHotel(String city) throws HotelNotFoundException;

	void deleteHotel(Integer hotelId, Principal principal) throws UnAuthorizedException, HotelNotFoundException;
}
