package com.demo.flight.stepfunctions.trigger;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionResult;
import com.demo.flight.FlightDomain.model.FlightMsg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StepFunctionTrigger {

	private static final Logger logger = LoggerFactory.getLogger(StepFunctionTrigger.class);
	
	/**
	 * Trigger step function
	 * @param msg
	 * @throws JsonProcessingException 
	 */
	public void triggerStepFunction(FlightMsg msg) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		StartExecutionRequest request = new StartExecutionRequest()
                .withStateMachineArn(System.getenv("FLIGHT_MSG_STATE_MACHINE_ARN"))
                .withName(UUID.randomUUID().toString())
                .withInput(mapper.writeValueAsString(msg));
		logger.info("Triggering State Machine with ARN {} and input {}"
				,request.getStateMachineArn(),request.toString());
		
		StartExecutionResult result = stepFunctionBuilder().startExecution(request);
		logger.info("State Machine triggered succesfully with result {}"
				,result.toString());
	}
	
	
	public AWSStepFunctions stepFunctionBuilder() {
	
		// (1) Define the AWS Region in which the function is to be invoked.
				Regions region = Regions.fromName(System.getenv("EXECUTION_REGION"));
				// (2) Instantiate AWSStepFunctionsClientBuilder to build the client
				AWSStepFunctionsClientBuilder builder = AWSStepFunctionsClientBuilder.standard()
				                                          .withRegion(region);
				// (3) Build the client, which will ultimately do the work
				AWSStepFunctions client = builder.build();
				return client;
				
	}
	
}
