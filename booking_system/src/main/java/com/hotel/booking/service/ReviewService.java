package com.hotel.booking.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hotel.booking.entity.Review;
import com.hotel.booking.exception.HotelNotFoundException;
import com.hotel.booking.exception.ReviewNotFoundException;
import com.hotel.booking.model.ReviewCommentModel;
import com.hotel.booking.model.ReviewModel;

public interface ReviewService {
	Review addUpdateReview(ReviewModel reviewModel, Principal principal) throws HotelNotFoundException;
	
	Page<ReviewCommentModel> showReview(Pageable pageable, Integer hotelId) throws ReviewNotFoundException;
	List<ReviewCommentModel> showReview(Integer hotelId) throws ReviewNotFoundException;
}
