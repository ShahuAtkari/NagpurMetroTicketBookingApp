package com.NagpurMetro.Binding;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
@Entity
@Table(name="MetroTicket")
public class MetroTicketInfo {
	@Id
	@Column(name="Ticket_Number")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketNumber;
	
	@Column(name="Boarding_Station")
	private String boardingStation;
	
	@Column(name="Destination_Station")
	
	private String destinationStation;
	
	@Column(name="Ticket_Price")
	private Integer ticketPrice;
	
	@Column(name="Transaction_Id")
	private String transactionId;
	
	@Column(name="Number_Of_Passenger")
	private Integer numberofPassenger;
	
	@Column(name="Platform_Number")
	private Integer platformNumber;
	
	
	@Column(name="Booking_Time")
	private String bookingTime;
	

	@Column(name="Ticket_Validity")
	private String ticketValidity;
	
	private String journeyType;
	

	public void setTicketPrice(String boardingStation2, String destinationStation2) {
		// TODO Auto-generated method stub
		
		
	}
}
