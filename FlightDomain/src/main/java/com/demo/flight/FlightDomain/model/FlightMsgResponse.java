package com.demo.flight.FlightDomain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FlightMsgResponse {

	private FlightMsg msg;
	private boolean hasError;
	private boolean isRetryable;
	private Error error;
}
