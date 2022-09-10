package com.hotel.booking.exception;

public class UnAuthorizedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnAuthorizedException(String message) {
		super(message);
	}

}
