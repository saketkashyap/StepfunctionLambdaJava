package com.demo.flight.FlightDomain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Error {

	private String errorCode;
	private String errorMsg;
	private String errorType;
	private String errorSubType;
}
