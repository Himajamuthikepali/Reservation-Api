package org.jsp.reservationapi.dto;

import java.time.LocalDate;

import org.jsp.reservationapi.model.Admin;

import lombok.Data;

@Data
public class BusRequest {
	private String name;
	private String bus_number;
	private String from_location;
	private String to_location;
	private int number_of_seats;
	private LocalDate date_of_departure;
	
	private Admin admin;

}
