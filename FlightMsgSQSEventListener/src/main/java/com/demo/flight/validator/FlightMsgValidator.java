package com.demo.flight.validator;

import com.demo.flight.FlightDomain.exceptions.ValidationException;
import com.demo.flight.FlightDomain.model.FlightMsgType;
import com.demo.flight.utils.FlightMsgConstants;

/**
 * 
 * @author SaketKashyap
 *
 */
public class FlightMsgValidator{

	
	/**
	 * 
	 * @param properties
	 * @param expectedLength
	 * @return
	 */
	public boolean validateFlightMsgLength(String[] properties,int expectedLength) throws ValidationException{
		
		if(properties.length!=expectedLength) {
			throw new ValidationException("inbound msg length validation failed,"
					+ "must only have properties which are"+
					FlightMsgConstants.FLIGHT_MSG_PROPETIES);
		}
		else {
			return true;
		}
	}
	
/**
 * 	
 * @param type
 * @return
 */
public boolean validateFlightMsgType(String type) throws ValidationException {
		
	boolean isValidMsgType = false;
	 
	for (FlightMsgType msgType : FlightMsgType.values())
	{
	   if(msgType.toString().equalsIgnoreCase(type)) {
		   isValidMsgType = true;
		   break;
	   }
	}
	if(!isValidMsgType) {
		throw new ValidationException("Message Type can only be {}"+FlightMsgType.values().toString());
	}
	return isValidMsgType;
	}

/**
 * 
 * @param flightNumber
 * @return
 */
public boolean validateFlightNumberLength(String flightNumber) throws ValidationException {
	boolean isValidFlightNumber = true;
	if(flightNumber.length()!=10) {
		isValidFlightNumber = false;
		throw new ValidationException("Flight Number must be of length 10");
	}
	
	return isValidFlightNumber;
	
}
}
