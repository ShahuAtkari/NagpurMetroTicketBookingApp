package com.NagpurMetro.Binding;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MetroTicketBook {
	
	
	private String boardingStation;
	
	private String destinationStation;
	
	@NotBlank(message="Number of Passenger can not be Empty")
	private Integer numberofPassenger;		
}
