package com.NagpurMetro.ServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.NagpurMetro.Binding.MetroTicketBook;
import com.NagpurMetro.Binding.MetroTicketInfo;
import com.NagpurMetro.Binding.PassengerInfo;
import com.NagpurMetro.Repository.BookTicketRepository;
import com.NagpurMetro.Repository.RegisterPassengerRepository;
import com.NagpurMetro.Service.MetroTicketService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class MetroTicketServiceImpl implements MetroTicketService {
	@Autowired
	BookTicketRepository repository;
	
	@Autowired
	RegisterPassengerRepository repo;
	
	@Autowired
	BCryptPasswordEncoder cryptpassword;
	
	// Creating a Logger instance to log messages and errors for this class
		 private Logger logger = LoggerFactory.getLogger(MetroTicketServiceImpl.class);

	public MetroTicketServiceImpl(BookTicketRepository repository) {
		this.repository=repository;
	}

	@Override
	public MetroTicketInfo CreateTicket(MetroTicketInfo request) {
		
		return repository.save(request);
			
	}



	@Override
	public List<MetroTicketInfo> ListTicket() {
		
		return repository.findAll();
	}



	@Override
	public String ModifyTicket(MetroTicketInfo request) {
		
		repository.save(request);
		if(request.getTicketNumber()!=null)
			return "updated";
		else
			return "not Updated...";
	}

	

//	@Override
//	public MetroTicketInfo DeleteTicket(Integer request) {
//		
//		repository.deleteById(request);
//		return request;
//	}
	
	

	 // Overriding the method from the QRCodeService interface
	 @Override
	 public byte[] generateQRCode(String qrContent, int width, int height) {
	     try {
	         // Creating a QRCodeWriter instance, which helps in generating QR codes
	         QRCodeWriter qrCodeWriter = new QRCodeWriter();
	         
	         // Encoding the content (text) into a QR code format, creating a BitMatrix
	         BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, width, height);
	         
	         // ByteArrayOutputStream is used to write data into a byte array (memory stream)
	         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	         
	         // Converts the BitMatrix (QR Code) into an image format (PNG) and writes it to the ByteArrayOutputStream
	         MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
	         
	         // Returning the generated QR code as a byte array (image in memory)
	         return byteArrayOutputStream.toByteArray();
	         
	     } catch (WriterException e) {
	         // If QR code encoding fails, log the error message
	         logger.error(e.getMessage(), e);
	         
	     } catch (IOException e) {
	         // If writing image stream fails, log the error message
	         logger.error(e.getMessage(), e);
	     }
	     
	     // If any exception occurs, return null
	     return null;
	 }




	@Override
	public boolean RegisterPassenger(PassengerInfo info) {
		
		String encodedpwd=cryptpassword.encode(info.getPassengerPassword());
		info.setPassengerPassword(encodedpwd);
		PassengerInfo info1=repo.save(info);
		return info1.getPassengerId()!=null;
	}



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
	    PassengerInfo pi=repo.findByPassengerEmail(email);
		
		return new User(pi.getPassengerEmail(), pi.getPassengerPassword(), Collections.emptyList());
	}


}
