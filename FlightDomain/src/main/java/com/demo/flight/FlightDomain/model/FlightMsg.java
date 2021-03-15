package com.demo.flight.FlightDomain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FlightMsg {

	
	private String messageType;
	private String flightNumber;
}
