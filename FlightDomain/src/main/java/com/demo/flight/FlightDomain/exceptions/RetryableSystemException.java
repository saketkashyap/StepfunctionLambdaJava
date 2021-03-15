package com.demo.flight.FlightDomain.exceptions;

public class RetryableSystemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4507975291827491360L;
	
	public RetryableSystemException(String message) {
        super("retryable exception occured,"+message);
    }

}
