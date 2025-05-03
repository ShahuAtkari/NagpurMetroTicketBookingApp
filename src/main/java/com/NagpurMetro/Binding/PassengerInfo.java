package com.NagpurMetro.Binding;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Register_Passenger")
public class PassengerInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Passenger_Id")
	private Integer passengerId;
	
	@Column(name="First_Name")
	private String passengerFirstName;
	
	@Column(name="Last_Name")
	private String passengerLastName;
	
	@Column(name="Mobile")
	private Long passengerMobile;
	
	@Column(name="Email")
	private String passengerEmail;
	
	@Column(name="Password")
	private String passengerPassword;
	

}
