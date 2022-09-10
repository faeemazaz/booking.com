package com.hotel.booking.controller;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.booking.entity.Hotel;
import com.hotel.booking.exception.HotelNotFoundException;
import com.hotel.booking.exception.UnAuthorizedException;
import com.hotel.booking.model.SearchHotelModel;
import com.hotel.booking.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Hotel> addHotel(@Valid @RequestBody Hotel hotel, Principal principal)
			throws SQLIntegrityConstraintViolationException {
		hotelService.addUpdateHotel(hotel, principal);
		return ResponseEntity.ok(hotel);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Hotel> updateHotel(@Valid @RequestBody Hotel hotel, Principal principal)
			throws SQLIntegrityConstraintViolationException {
		hotelService.addUpdateHotel(hotel, principal);
		return ResponseEntity.ok(hotel);
	}

	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public ResponseEntity<List<SearchHotelModel>> searchHotelByCityWithFilter(
			@RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "sortByRating", required = false) String sortByRating,
			@RequestParam(name = "sortByCost", required = false) String sortByCost,
			@RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir,
			@RequestParam(name = "city") String city,
			@RequestParam(name = "checkin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkin,
			@RequestParam(name = "checkout") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkout,
			@RequestParam(name = "numberofguest") Integer numberofguest) throws HotelNotFoundException {

		Sort sortByRatings = (sortByRating != null)
				? (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortByRating).ascending()
						: Sort.by(sortByRating).descending()
				: null;
		Sort sortByCosts = (sortByCost != null)
				? (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortByCost).ascending() : Sort.by(sortByCost).descending()
				: null;

		Sort sort = (sortByCosts == null && sortByRatings == null) ? null
				: (sortByCosts == null) ? sortByRatings
						: (sortByRatings == null) ? sortByCosts : sortByRatings.and(sortByCosts);

		Pageable pageable;
		Page<SearchHotelModel> page;
		List<SearchHotelModel> hotelModels;

		if (sort != null) {
			pageable = PageRequest.of(pageNo, pageSize, sort);
			page = hotelService.searchHotelByFilterRating(pageable, city);
			hotelModels = page.getContent();
		} else {
			hotelModels = hotelService.searchHotel(city);
		}

		return ResponseEntity.ok(hotelModels);
	}

	@RequestMapping(value = "/{hotelId}", method = RequestMethod.DELETE)
	public void deleteHotel(@PathVariable("hotelId") Integer hotelId, Principal principal)
			throws UnAuthorizedException, HotelNotFoundException {
		hotelService.deleteHotel(hotelId, principal);
	}

}
