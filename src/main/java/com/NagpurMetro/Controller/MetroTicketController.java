package com.NagpurMetro.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.NagpurMetro.Binding.MetroTicketBook;
import com.NagpurMetro.Binding.MetroTicketInfo;
import com.NagpurMetro.Binding.PassengerInfo;
import com.NagpurMetro.Service.MetroTicketService;
import com.NagpurMetro.ServiceImpl.ValidData;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
public class MetroTicketController {
	
	@Autowired
	private MetroTicketService metroticketservice;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@GetMapping("/home")
	public String home()
	{
		return "home";
	}
	
	
	@PostMapping(value="/tkt", produces = {"Application/json"},consumes = {"Application/json"})
	public ResponseEntity<MetroTicketInfo> bookMetroTicket(@RequestBody MetroTicketBook ticketbooking) throws Exception
	{	
		MetroTicketInfo mt=new MetroTicketInfo();
	
		mt.setTicketNumber(ValidData.generateTicket());
		
		mt.setBoardingStation(ValidData.checkBoardingStation(ticketbooking.getBoardingStation()));
		
		mt.setDestinationStation(ValidData.checkBoardingStation(ticketbooking.getDestinationStation()));
		
		mt.setNumberofPassenger(ticketbooking.getNumberofPassenger());
		
		mt.setPlatformNumer(2);
		
		mt.setTicketPrice(ValidData.CalculateTicketPrice(ticketbooking.getBoardingStation(),ticketbooking.getDestinationStation())*mt.getNumberofPassenger());
		
		mt.setTransactionId(ValidData.GenerateTransactionId());
		
		mt.setBookingTime(ValidData.BookTimes());
		
		mt.setTicketValidity(ValidData.ValidTimes());
		
		
		metroticketservice.CreateTicket(mt);
		
		return new ResponseEntity<MetroTicketInfo>(mt, HttpStatus.CREATED) ;
	}
	
	@PutMapping("/updateticket")
	public ResponseEntity<String> UpdateMetroTicket(@RequestBody MetroTicketInfo ticketbooking)
	{
		MetroTicketInfo mt=new MetroTicketInfo();
		
		mt.setTicketNumber(ticketbooking.getTicketNumber());
		
		mt.setBoardingStation(ticketbooking.getBoardingStation());
		
		mt.setDestinationStation(ticketbooking.getDestinationStation());
		
		mt.setNumberofPassenger(ticketbooking.getNumberofPassenger());
		
		mt.setPlatformNumer(ticketbooking.getPlatformNumer());
		
		mt.setTicketPrice(ticketbooking.getTicketPrice());
		
		mt.setTransactionId(ticketbooking.getTransactionId());
		
		String msg=metroticketservice.ModifyTicket(ticketbooking);
		
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/ListTicket")
	public ResponseEntity<List<MetroTicketInfo>> ListMetroTicket()
	{
		
		List<MetroTicketInfo> list=metroticketservice.ListTicket();
		
		return new ResponseEntity<List<MetroTicketInfo>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> RegisterPassenger(@RequestBody PassengerInfo info)
	{
		boolean status= metroticketservice.RegisterPassenger(info);
		
		if(status)
			return new ResponseEntity<String>("Success",HttpStatus.CREATED);
		else
			return new ResponseEntity<String>("bad Request",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> LoginPassenger(@RequestBody PassengerInfo info)
	{
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(info.getPassengerEmail(), info.getPassengerPassword());
		
		org.springframework.security.core.Authentication authenticateuser=authmanager.authenticate(token);
			
			boolean status=authenticateuser.isAuthenticated();
			if(status)
				return new ResponseEntity<String>("success", HttpStatus.ACCEPTED); 
			else
				return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST); 
	}
	
}
