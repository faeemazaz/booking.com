package com.hotel.booking.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.booking.entity.Review;
import com.hotel.booking.exception.HotelNotFoundException;
import com.hotel.booking.exception.ReviewNotFoundException;
import com.hotel.booking.model.ReviewCommentModel;
import com.hotel.booking.model.ReviewModel;
import com.hotel.booking.service.ReviewService;

@RestController
@RequestMapping("/hotel")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public ResponseEntity<Review> addReview(@Valid @RequestBody ReviewModel reviewModel, Principal principal)
			throws HotelNotFoundException {
		Review review = reviewService.addUpdateReview(reviewModel, principal);
		return ResponseEntity.ok(review);
	}

	@RequestMapping(value = "/review", method = RequestMethod.PUT)
	public ResponseEntity<Review> updateReview(@Valid @RequestBody ReviewModel reviewModel, Principal principal)
			throws HotelNotFoundException {
		Review review = reviewService.addUpdateReview(reviewModel, principal);
		return ResponseEntity.ok(review);
	}

	@RequestMapping(value = "/{hotelId}/review", method = RequestMethod.GET)
	public ResponseEntity<List<ReviewCommentModel>> showReviewByHotelFilterGender(
			@PathVariable("hotelId") Integer hotelId,
			@RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "sortByGender", required = false) String sortByGender,
			@RequestParam(name = "sortByCity", required = false) String sortByCity,
			@RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir)
			throws ReviewNotFoundException {

		Sort sortByGenders = (sortByGender != null)
				? (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortByGender).ascending()
						: Sort.by(sortByGender).descending()
				: null;
		Sort sortByCitys = (sortByCity != null)
				? (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortByCity).ascending() : Sort.by(sortByCity).descending()
				: null;

		Sort sort = (sortByGenders == null && sortByCitys == null) ? null
				: (sortByGenders == null) ? sortByCitys
						: (sortByCitys == null) ? sortByGenders : sortByGenders.and(sortByCitys);

		Pageable pageable;
		Page<ReviewCommentModel> page;
		List<ReviewCommentModel> reviewModel;

		if (sort != null) {
			pageable = PageRequest.of(pageNo, pageSize, sort);
			page = reviewService.showReview(pageable, hotelId);
			reviewModel = page.getContent();
		} else {
			reviewModel = reviewService.showReview(hotelId);
		}

		return ResponseEntity.ok(reviewModel);
	}

}
