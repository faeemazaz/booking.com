package com.hotel.booking.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.booking.entity.Review;
import com.hotel.booking.model.ReviewCommentModel;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
	@Query("SELECT new com.hotel.booking.model.ReviewCommentModel(u.userName, u.city, u.gender, r.rating, r.comment, r.ratingDate) FROM Review r INNER JOIN r.hotel h INNER JOIN r.user u WHERE h.hotelId = ?1")
	public Page<ReviewCommentModel> showReview(Pageable pageable, Integer hotelId);
	
	@Query("SELECT new com.hotel.booking.model.ReviewCommentModel(u.userName, u.city, u.gender, r.rating, r.comment, r.ratingDate) FROM Review r INNER JOIN r.hotel h INNER JOIN r.user u WHERE h.hotelId = ?1")
	public List<ReviewCommentModel> showReview(Integer hotelId);
}
