package com.NagpurMetro.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.NagpurMetro.Binding.MetroTicketBook;
import com.NagpurMetro.Binding.MetroTicketInfo;
import com.NagpurMetro.Binding.PassengerInfo;
import com.NagpurMetro.Service.LoginValidationGroup;
import com.NagpurMetro.Service.MetroTicketService;
import com.NagpurMetro.ServiceImpl.ValidData;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class MetroTicketViewController {
	

	@Autowired
	public MetroTicketService metroticketservice;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	//for UI
		@GetMapping("/tkt/ListTicket")
		public String ListMetroTicketUI(org.springframework.ui.Model model)
		{
			
			List<MetroTicketInfo> list=metroticketservice.ListTicket();
			
			model.addAttribute("alltickets", list);
			
			return "MetroListTicket"; //<--view name of html file.
		}

		
		
		@RequestMapping("/")
		public String index(Model model) {
			model.addAttribute("passenger", new PassengerInfo()); // Important!
			return "index";
			}

		
		@RequestMapping("/updatePassword")
	    public String Updatepassword() {
	        return "updatePassword";
	    }
		
		@RequestMapping("/registershow")
		public String register(Model model) {
			
		    model.addAttribute("passenger", new PassengerInfo());
		    return "Register";
		}


	    @GetMapping("/generateQRCode")
	    public void generateQRCode(String qrContent, HttpServletResponse response) throws IOException {
	        response.setContentType("image/png");
	        byte[] qrCode = metroticketservice.generateQRCode(qrContent, 250, 250);
	        OutputStream outputStream = response.getOutputStream();
	        outputStream.write(qrCode);
	    }
		
	    
	    @GetMapping("/tkt/new")
	    public String bookMetroTicket(Model model)
	    {
	    	MetroTicketBook ticketbooking = new MetroTicketBook();
	    	model.addAttribute("bookingdataXML", ticketbooking);
	    	
	    	return "new";
	    }
	    
	    @PostMapping("/ticketdata")
	    public String CreateMetroTicket(@ModelAttribute("bookingdataXML") MetroTicketBook ticketbook,org.springframework.ui.Model model) throws Exception {
	        MetroTicketInfo mt = new MetroTicketInfo();

	        mt.setTicketNumber(ValidData.generateTicket());
	        mt.setBoardingStation(ValidData.checkBoardingStation(ticketbook.getBoardingStation()));
	        mt.setDestinationStation(ValidData.checkBoardingStation(ticketbook.getDestinationStation()));
	        mt.setNumberofPassenger(ticketbook.getNumberofPassenger());
	        mt.setPlatformNumer(2);
	        mt.setTicketPrice(ValidData.CalculateTicketPrice(ticketbook.getBoardingStation(), ticketbook.getDestinationStation()) * mt.getNumberofPassenger());
	        mt.setTransactionId(ValidData.GenerateTransactionId());
	        mt.setBookingTime(ValidData.BookTimes());
	        mt.setTicketValidity(ValidData.ValidTimes());

	        metroticketservice.CreateTicket(mt);
	        
	       
	        model.addAttribute("qrCodeContent", "/generateQRCode?qrContent=" + mt.getTicketNumber());
	        model.addAttribute("newTicket", mt);
	        
	        return "realTicket";
//	        return  "redirect:/successPage";
	    }
	    
//	    @GetMapping("/successPage")
//	    public String ticketSuccessPage() {
//	        return "realTicket";
//	    }

	
	    @PostMapping("/registerUI")
	    public String RegisterPassenger(@Validated @ModelAttribute("passenger") PassengerInfo info, BindingResult result, Model model)
 {
	    	
	    	if(result.hasErrors())
			{
				return "Register";
			}
	        model.addAttribute("passenger", metroticketservice.RegisterPassenger(info));
	        return "new";
	    }

	    @PostMapping("/loginUI")
		public String LoginPassenger(@Validated(LoginValidationGroup.class) @ModelAttribute("passenger") PassengerInfo info, BindingResult result , Model model)
		{
	    	boolean status=false;
	    	
	    	if(result.hasErrors())
	    	{
	    		return "index";
	    	}
	   
			UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(info.getPassengerEmail(), info.getPassengerPassword());
			
			try 
			{
					org.springframework.security.core.Authentication authenticateuser=authmanager.authenticate(token);
					status=authenticateuser.isAuthenticated();
			}
			catch (Exception e) {
				
				model.addAttribute("msg", "Email or Password Incorrect");
				return "index";	
			}
				if(status)
				{
					return "redirect:/tkt/new";
				}
				else
				{
					model.addAttribute("msg", "Email or Password Incorrect");
					return "index";
				}
			
				
		}
}
	    

