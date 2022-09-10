package com.hotel.booking.exception;

public class ReviewNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ReviewNotFoundException(String messege)
	{
		super(messege);
	}
	

}
