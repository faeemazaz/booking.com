package com.hotel.booking.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.hotel.booking.entity.Hotel;
import com.hotel.booking.entity.Review;
import com.hotel.booking.entity.User;
import com.hotel.booking.exception.HotelNotFoundException;
import com.hotel.booking.exception.ReviewNotFoundException;
import com.hotel.booking.model.ReviewCommentModel;
import com.hotel.booking.model.ReviewModel;
import com.hotel.booking.repository.HotelRepo;
import com.hotel.booking.repository.ReviewRepo;
import com.hotel.booking.repository.UserRepo;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private HotelRepo hotelRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ReviewRepo reviewRepo;

	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public Review addUpdateReview(ReviewModel reviewModel, Principal principal)
			throws HotelNotFoundException {
		Optional<Hotel> hotelRef = hotelRepo.findById(reviewModel.getHotelId());
		User userRef = userRepo.findByUserName(principal.getName());

		Review review = new Review();
		if (hotelRef.isPresent()) {
			review.setReviewId(reviewModel.getReviewId());
			review.setComment(reviewModel.getComment());
			review.setRating(reviewModel.getRating());
			review.setRatingDate(reviewModel.getRatingDate());
			review.setHotel(hotelRef.get());
			review.setUser(userRef);
		} else {
			throw new HotelNotFoundException("You entered hotel is not present. Please try again !!");
		}

		return reviewRepo.save(review);
	}

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@Override
	public Page<ReviewCommentModel> showReview(Pageable pageable, Integer hotelId) throws ReviewNotFoundException {
		Page<ReviewCommentModel> review = reviewRepo.showReview(pageable, hotelId);
		if (review.isEmpty()) {
			throw new ReviewNotFoundException("Review Not found !!");
		}
		return reviewRepo.showReview(pageable, hotelId);
	}

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@Override
	public List<ReviewCommentModel> showReview(Integer hotelId) throws ReviewNotFoundException {
		List<ReviewCommentModel> review = reviewRepo.showReview(hotelId);
		if (review.size() == 0) {
			throw new ReviewNotFoundException("Review Not found !!");
		}
		return reviewRepo.showReview(hotelId);
	}

}
