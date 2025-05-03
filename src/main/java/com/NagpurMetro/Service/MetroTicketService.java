package com.NagpurMetro.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.NagpurMetro.Binding.MetroTicketInfo;
import com.NagpurMetro.Binding.PassengerInfo;

public interface MetroTicketService extends UserDetailsService {

	public MetroTicketInfo CreateTicket(MetroTicketInfo request);
	
	public List<MetroTicketInfo> ListTicket();

	public String ModifyTicket(MetroTicketInfo request);

	//public MetroTicketInfo DeleteTicket(Integer request);
	
	 byte[] generateQRCode(String qrContent, int width, int height);
	 
	 public boolean RegisterPassenger(PassengerInfo info);
	 
}