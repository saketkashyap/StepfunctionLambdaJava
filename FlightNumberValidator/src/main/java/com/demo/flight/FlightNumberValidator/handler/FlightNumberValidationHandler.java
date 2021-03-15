package com.demo.flight.FlightNumberValidator.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.demo.flight.FlightDomain.model.Error;
import com.demo.flight.FlightDomain.model.FlightMsg;
import com.demo.flight.FlightDomain.model.FlightMsgResponse;
import com.demo.flight.FlightNumberValidator.validator.FlightNumberValidator;

public class FlightNumberValidationHandler implements RequestHandler<FlightMsg,FlightMsgResponse>{

	private static final Logger logger = LoggerFactory.getLogger(FlightNumberValidationHandler.class);
	
	public FlightMsgResponse handleRequest(FlightMsg input, Context context) {
		
		logger.info("received input for flight number validation"+input.toString());
		logger.info("received context:"+context);
		FlightMsgResponse response = new FlightMsgResponse();
		FlightNumberValidator validator = new FlightNumberValidator();
			validator.validateFlightNumber(input);
			response = new FlightMsgResponse(input,false,false,new Error());
		return response;
	}

	
}
