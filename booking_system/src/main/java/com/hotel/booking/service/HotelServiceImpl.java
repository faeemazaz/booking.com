package com.hotel.booking.service;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.hotel.booking.entity.Hotel;
import com.hotel.booking.entity.User;
import com.hotel.booking.exception.HotelNotFoundException;
import com.hotel.booking.exception.UnAuthorizedException;
import com.hotel.booking.model.SearchHotelModel;
import com.hotel.booking.repository.HotelRepo;
import com.hotel.booking.repository.UserRepo;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo hotelRepo;

	@Autowired
	private UserRepo userRepo;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public Hotel addUpdateHotel(Hotel hotel, Principal principal) throws SQLIntegrityConstraintViolationException{
		User user = userRepo.findByUserName(principal.getName());
		hotel.setUser(user);
		return hotelRepo.save(hotel);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public Page<SearchHotelModel> searchHotelByFilterRating(Pageable pageable, String city)
			throws HotelNotFoundException {
		Page<SearchHotelModel> hotel = hotelRepo.searchHotel(pageable, city);
		if (hotel.isEmpty()) {
			throw new HotelNotFoundException("Hotel not found based on " + city + " location !!");
		}
		return hotel;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public List<SearchHotelModel> searchHotel(String city) throws HotelNotFoundException {
		List<SearchHotelModel> hotel = hotelRepo.searchHotel(city);
		if (hotel.size() == 0) {
			throw new HotelNotFoundException("Hotel not found based on " + city + " location !!");
		}
		return hotel;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteHotel(Integer hotelId, Principal principal) throws HotelNotFoundException, UnAuthorizedException {
		Optional<Hotel> hotel = hotelRepo.findById(hotelId);
		User user2 = userRepo.findByUserName(principal.getName());

		if (hotel.isPresent()) {
			if (hotel.get().getUser().getUserId() != user2.getUserId()) {
				throw new UnAuthorizedException("You have not authority to delete this hotel !!");
			} else {
				System.out.println(hotel.get().getHotelId());
				hotelRepo.delete(hotel.get());
			}
		}

		else {
			throw new HotelNotFoundException("Hotel not found, Please try again !!");
		}

	}

}
