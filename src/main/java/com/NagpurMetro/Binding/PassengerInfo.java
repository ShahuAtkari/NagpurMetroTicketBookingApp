package com.NagpurMetro.Binding;

import com.NagpurMetro.Service.LoginValidationGroup;
import com.NagpurMetro.Service.RegisterValidationGroup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@NotBlank(message = "please provide a first name",groups=RegisterValidationGroup.class)
	@Size(min=3,max=15,message = "first name should be 3 character or less than 15 character",groups=RegisterValidationGroup.class)
	private String passengerFirstName;
	
	@Column(name="Last_Name")
	@NotBlank(message = "please provide a last name" ,groups=RegisterValidationGroup.class)
	@Size(min=3,max=15,message = "last name should be 3 character or less than 15 character",groups=RegisterValidationGroup.class)
	private String passengerLastName;
	
	@Column(name="Mobile")
	@NotNull(message="please provide valid mobile number",groups=RegisterValidationGroup.class)
	@Size(min=10,max=10,message = "number should be 10 digit",groups=RegisterValidationGroup.class)
	private String passengerMobile;
	
	@Column(name="Email")
	@NotBlank(message= "email can not be empty",groups= {LoginValidationGroup.class,RegisterValidationGroup.class})
	@Email(message = "please provide valid email",groups= {LoginValidationGroup.class,RegisterValidationGroup.class})
	private String passengerEmail;
	
	@Column(name="Password")
	@NotBlank(message = "please provide valid password",groups= {LoginValidationGroup.class,RegisterValidationGroup.class})
	private String passengerPassword;
	

}
