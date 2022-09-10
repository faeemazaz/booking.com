package com.hotel.booking.exception;

public class RoleNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public RoleNotFoundException(String message) {
		super(message);
	}
}
