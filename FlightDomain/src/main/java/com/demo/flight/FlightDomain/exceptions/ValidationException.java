package com.demo.flight.FlightDomain.exceptions;

public class ValidationException extends RuntimeException {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -5846750593486741266L;

	public ValidationException(String message) {
	        super("Validation Failed,"+message);
	    }
	
}
