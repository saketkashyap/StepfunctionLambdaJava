package com.demo.flight.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.demo.flight.FlightDomain.exceptions.ValidationException;
import com.demo.flight.FlightDomain.model.FlightMsg;
import com.demo.flight.stepfunctions.trigger.StepFunctionTrigger;
import com.demo.flight.utils.FlightMessageUtils;
import com.fasterxml.jackson.core.JsonProcessingException;


public class FlightMsgSQSEventHandler implements RequestHandler<SQSEvent, Void> {

	private static final Logger logger = LoggerFactory.getLogger(FlightMsgSQSEventHandler.class);
	
	public Void handleRequest(SQSEvent event, Context context) throws ValidationException{
		
		logger.info("Received event from SQS"+event.toString());
		 for(SQSMessage msg : event.getRecords()){
			 logger.info("Msgs of the event {}",msg.getBody());
			 FlightMessageUtils msgUtils = new FlightMessageUtils();
			 FlightMsg flightMsgObj = new FlightMsg();
			 msgUtils.validateAndConstructFlightMsg(msg.getBody(),flightMsgObj);
			 StepFunctionTrigger trigger = new StepFunctionTrigger();
			 try {
				trigger.triggerStepFunction(flightMsgObj);
			} catch (JsonProcessingException e) {
			logger.error("exception occured in converting Flight Message object"
					+ " to Json String,cause is {}",e);
			}
	        }
		return null;
	}
   
}