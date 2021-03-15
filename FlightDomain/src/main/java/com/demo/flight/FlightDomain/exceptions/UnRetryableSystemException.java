package com.demo.flight.FlightDomain.exceptions;

public class UnRetryableSystemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2298848196099993489L;
	
	public UnRetryableSystemException(String message) {
        super("unretryable exception occured,"+message);
    }

}
