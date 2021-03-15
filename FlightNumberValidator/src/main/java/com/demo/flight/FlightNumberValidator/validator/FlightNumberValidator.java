package com.demo.flight.FlightNumberValidator.validator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.flight.FlightDomain.exceptions.RetryableSystemException;
import com.demo.flight.FlightDomain.exceptions.ValidationException;
import com.demo.flight.FlightDomain.model.FlightMsg;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class FlightNumberValidator {
	
	private static final Logger logger = LoggerFactory.getLogger(FlightNumberValidator.class);
	
	public interface FlightNumberValidatorClient {
        @POST("/Prod/flight/validate")
        @Headers("accept: application/json")
        CompletableFuture<Boolean> validateFlightNumber(@Body FlightMsg msg);
    }

	public void validateFlightNumber(FlightMsg msg) {
		logger.info("begin flight number validation");
		Retrofit retrofit = new Retrofit.Builder()
	            .baseUrl(System.getenv("FLIGHT_VALIDATION_API_HOST"))
	            .addConverterFactory(JacksonConverterFactory.create())
	            .build();

		logger.info("initiate outbound POST call "
				+ "to FlightNumberValidator function with request {} "
				+ "and base url {}",msg.toString(),retrofit.baseUrl());
		
		FlightNumberValidatorClient validatorClient= retrofit.
				create(FlightNumberValidatorClient.class);
		
		
		boolean isValidFlightNumber;
		try {
			isValidFlightNumber = validatorClient.validateFlightNumber(msg).get();
			if(!isValidFlightNumber) {
				logger.error("flight number is not valid");
				throw new ValidationException("flight number is not valid");
		   } 
		}
			catch(InterruptedException | ExecutionException ex){
				logger.error(ex.getMessage());
				throw new RetryableSystemException(ex.getMessage());
			}
			
		} 
	

}
