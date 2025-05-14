package com.NagpurMetro.Binding;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MetroTicketBook {
	
	@NotBlank(message="select the boarding station")
	private String boardingStation;
	
	@NotBlank(message="select the destination station")
	private String destinationStation;

	@NotNull(message="add number of passenger")
	private Integer numberofPassenger;		
}
