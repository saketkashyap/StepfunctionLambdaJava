package com.demo.flight.utils;

import com.demo.flight.FlightDomain.exceptions.ValidationException;
import com.demo.flight.FlightDomain.model.FlightMsg;
import com.demo.flight.validator.FlightMsgValidator;

/**
 * 
 * @author SaketKashyap
 *
 */
public class FlightMessageUtils {

	
	/**
	 * 
	 * @param message
	 * @return
	 * @throws ValidationException
	 */
	
	public FlightMsg validateAndConstructFlightMsg(String message,FlightMsg msg) throws ValidationException {
		String[] properties = message.split("\\r?\\n");
		if(validateFlightMsg(properties)) {
			constructFlightMsgJson(properties,msg);
		}
		return msg;
}

	/**
	 * 
	 * @param properties
	 * @return
	 */
	public boolean validateFlightMsg(String[] properties) throws ValidationException{
		
		boolean isValidFlightMsg = false;
		FlightMsgValidator validator = new FlightMsgValidator();
		
		//validate length of the inbound msg
		if(validator.validateFlightMsgLength(properties,FlightMsgConstants.FLIGHT_MSG_LENGTH)) {

			//validate the msg type of the inbound msg
			if(validator.validateFlightMsgType(properties[0])) {
				if(validator.validateFlightNumberLength(properties[1])) {
					isValidFlightMsg = true;
				}
			}
			
			
		}
		
		return isValidFlightMsg;
		
	}

	/**
	 * 
	 * @param properties
	 * @param msg
	 * @return
	 */
	public FlightMsg constructFlightMsgJson(String[] properties,FlightMsg msg) {

		msg.setMessageType(properties[0].trim());
		msg.setFlightNumber(properties[1].trim());
		return msg;
	}
}