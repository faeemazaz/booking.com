package com.hotel.booking.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionHandler {
	@ExceptionHandler(HotelNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleHotelNotFoundException(HotelNotFoundException hotelNotFoundException) {
		return hotelNotFoundException.getMessage();
	}

	@ExceptionHandler(RoleNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleRoleNotFoundException(RoleNotFoundException roleNotFoundException) {
		return roleNotFoundException.getMessage();
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleUserNotFoundException(UserNotFoundException userNotFoundException) {
		return userNotFoundException.getMessage();
	}

	@ExceptionHandler(ReviewNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleReviewNotFoundException(ReviewNotFoundException reviewNotFoundException) {
		return reviewNotFoundException.getMessage();
	}

	@ExceptionHandler(UnAuthorizedException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public String handleUnAuthorizedException(UnAuthorizedException unAuthorizedException) {
		return unAuthorizedException.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
	public Map<String, String> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		Map<String, String> fieldException = new HashMap<>();
		methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(e -> {
			fieldException.put(((FieldError) e).getField(), e.getDefaultMessage());
		});
		return fieldException;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
	public Map<Path, String> handleConstraintViolationException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

		Map<Path, String> fieldException = new HashMap<>();
		constraintViolations.stream().forEach(e -> {
			fieldException.put(e.getPropertyPath(), e.getMessage());
		});
		return fieldException;
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleDuplicateEntryException(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
		return sqlIntegrityConstraintViolationException.getMessage();
	}

}
