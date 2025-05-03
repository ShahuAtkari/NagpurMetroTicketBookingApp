package com.NagpurMetro.ServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Supplier;

import com.NagpurMetro.Binding.MetroTicketInfo;

import jakarta.persistence.PrePersist;

public class ValidData {
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int DEFAULT_LENGTH = 10; // You can change the length

	
	public static Long generateTicket()
	{

		Supplier<String> otpGenarate=()->{
			String otp="";
			for(int i=1;i<=6;i++)
			{
				otp=otp + (int)(Math.random()*10);
			}
			return otp;
		};
		
		DateTimeFormatter formater=DateTimeFormatter.ofPattern("yyMMdd");
		LocalDate d1=LocalDate.now();
		String dd=d1.format(formater);
		
		Long tktnumber=Long.parseLong(dd+"00000"+otpGenarate.get());
		
		return tktnumber;
	}
	
	public static String checkBoardingStation(String boardingstation) throws Exception 
	{
		List<String> stationlist=Arrays.asList(
				"KHAPRI",
			    "NEW AIRPORT",
			    "SOUTH AIRPORT",
			    "AIRPORT",
			    "UJWAL NAGAR",
			    "JAI PRAKASH NAGAR",
			    "CHHATRAPATI SQUARE",
			    "AJNI SQUARE",
			    "RAHATE COLONY",
			    "CONGRESS NAGAR",
			    "SITABULDI (N-S)",
			    "ZEROMILE",
			    "KASTURCHAND PARK",
			    "GADDI GODAM",
			    "KADVI CHOWK",
			    "INDORA SQUARE",
			    "NARI ROAD",
			    "AUTOMOTIVE SQUARE",
			    "LOKMANYA NAGAR",
			    "BANSI NAGAR",
			    "VASUDEV NAGAR",
			    "RACHNA RING ROAD",
			    "SUBHASH NAGAR",
			    "DHARAMPETH COLLEGE",
			    "LAD CHOWK",
			    "SHANKAR NAGAR SQUARE",
			    "INSTITUTE OF ENGINEERS",
			    "JHANSI RANI SQUARE",
			    "SITABULDI (E-W)",
			    "COTTON MARKET",
			    "NAGPUR RAILWAY STATION",
			    "DOSAR VAISHYA CHOWK",
			    "AGRASEN CHOWK",
			    "CHITRANOLI CHOWK",
			    "TELEPHONIC EXCHANGE",
			    "AMBEDKAR CHOWK",
			    "VAISHNO DEVI CHOWK",
			    "PRAJAPATI NAGAR"
				);
		Integer numberofStation=stationlist.size();
		String stationname = null;
		if (boardingstation != null && !boardingstation.isEmpty()) {
		    boolean found = false;

		    for (int i = 0; i < numberofStation; i++) {
		        if (stationlist.get(i).equalsIgnoreCase(boardingstation)) {
		            stationname = stationlist.get(i);
		            found = true;
		            break;
		        }
		    }

		    if (!found) {
		        throw new IllegalArgumentException("please provide valid Metro Boarding station.");
		    }
		} else {
		    throw new IllegalArgumentException("Boarding station is null or empty.");
		}
		
		return stationname;
		
	}
	
	public static String checkDestinationStation(String destinationstation) throws Exception 
	{
		List<String> stationlist=Arrays.asList(
				"KHAPRI",
			    "NEW AIRPORT",
			    "SOUTH AIRPORT",
			    "AIRPORT",
			    "UJWAL NAGAR",
			    "JAI PRAKASH NAGAR",
			    "CHHATRAPATI SQUARE",
			    "AJNI SQUARE",
			    "RAHATE COLONY",
			    "CONGRESS NAGAR",
			    "SITABULDI (N-S)",
			    "ZEROMILE",
			    "KASTURCHAND PARK",
			    "GADDI GODAM",
			    "KADVI CHOWK",
			    "INDORA SQUARE",
			    "NARI ROAD",
			    "AUTOMOTIVE SQUARE",
			    "LOKMANYA NAGAR",
			    "BANSI NAGAR",
			    "VASUDEV NAGAR",
			    "RACHNA RING ROAD",
			    "SUBHASH NAGAR",
			    "DHARAMPETH COLLEGE",
			    "LAD CHOWK",
			    "SHANKAR NAGAR SQUARE",
			    "INSTITUTE OF ENGINEERS",
			    "JHANSI RANI SQUARE",
			    "SITABULDI (E-W)",
			    "COTTON MARKET",
			    "NAGPUR RAILWAY STATION",
			    "DOSAR VAISHYA CHOWK",
			    "AGRASEN CHOWK",
			    "CHITRANOLI CHOWK",
			    "TELEPHONIC EXCHANGE",
			    "AMBEDKAR CHOWK",
			    "VAISHNO DEVI CHOWK",
			    "PRAJAPATI NAGAR"
				);
		Integer numberofStation=stationlist.size();
		String stationname = null;
		if (destinationstation != null && !destinationstation.isEmpty()) {
		    boolean found = false;

		    for (int i = 0; i < numberofStation; i++) {
		        if (stationlist.get(i).equalsIgnoreCase(destinationstation)) {
		            stationname = stationlist.get(i);
		            found = true;
		            break;
		        }
		    }

		    if (!found) {
		        throw new IllegalArgumentException("please provide valid Metro Boarding station.");
		    }
		} else {
		    throw new IllegalArgumentException("Boarding station is null or empty.");
		}
		return stationname;
	}
	public static Integer CalculateTicketPrice(String boardingstation,String destinationStation)  throws Exception
	{
		List<String> stationlist = Arrays.asList(
				"NONE",
			    "KHAPRI",
			    "NEW AIRPORT",
			    "SOUTH AIRPORT",
			    "AIRPORT",
			    "UJWAL NAGAR",
			    "JAI PRAKASH NAGAR",
			    "CHHATRAPATI SQUARE",
			    "AJNI SQUARE",
			    "RAHATE COLONY",
			    "CONGRESS NAGAR",
			    "SITABULDI (N-S)",
			    "ZEROMILE",
			    "KASTURCHAND PARK",
			    "GADDI GODAM",
			    "KADVI CHOWK",
			    "INDORA SQUARE",
			    "NARI ROAD",
			    "AUTOMOTIVE SQUARE",
			    "LOKMANYA NAGAR",
			    "BANSI NAGAR",
			    "VASUDEV NAGAR",
			    "RACHNA RING ROAD",
			    "SUBHASH NAGAR",
			    "DHARAMPETH COLLEGE",
			    "LAD CHOWK",
			    "SHANKAR NAGAR SQUARE",
			    "INSTITUTE OF ENGINEERS",
			    "JHANSI RANI SQUARE",
			    "SITABULDI (E-W)",
			    "COTTON MARKET",
			    "NAGPUR RAILWAY STATION",
			    "DOSAR VAISHYA CHOWK",
			    "AGRASEN CHOWK",
			    "CHITRANOLI CHOWK",
			    "TELEPHONIC EXCHANGE",
			    "AMBEDKAR CHOWK",
			    "VAISHNO DEVI CHOWK",
			    "PRAJAPATI NAGAR"
			);
		
		Map<String, Integer> stationmap=new TreeMap<>();
		
		for(int i=1;i<stationlist.size();i++)
		{
			stationmap.put(stationlist.get(i), i);
		}
				
		Integer sum=0;
		sum=Math.abs(stationmap.get(boardingstation)-stationmap.get(destinationStation));
		System.out.println("total "+sum);
		Map<Integer, Integer> prices=new TreeMap<Integer, Integer>();
		prices.put(0, 0);
		prices.put(1, 10);
		prices.put(2, 10);
		prices.put(3, 10);
		
		prices.put(4, 15);
		prices.put(5, 15);
		
		prices.put(6, 20);
		prices.put(7, 20);
		prices.put(8, 20);
		prices.put(9, 20);
		
		prices.put(10, 25);
		prices.put(11, 25);
		
		prices.put(12, 30);
		prices.put(13, 30);
		prices.put(14, 30);
		
		prices.put(15, 35);
		prices.put(16, 35);
		prices.put(17, 35);
		
		prices.put(18, 40);
		prices.put(19, 40);
		prices.put(20, 40);
		prices.put(21, 40);
		
		prices.put(22, 35);
		prices.put(23, 35);
		prices.put(24, 35);
		
		prices.put(25, 30);
		prices.put(26, 30);
		prices.put(27, 30);
		prices.put(28, 30);
		
		prices.put(29, 25);
		
		prices.put(30, 30);
		prices.put(31, 30);
		prices.put(32, 30);
		prices.put(33, 30);
		
		prices.put(34, 35);
		prices.put(35, 35);
		prices.put(36, 35);
		
		prices.put(37, 40);
		prices.put(38, 40);
		
		
	 return prices.get(sum);	
	}
	
	public static String GenerateTransactionId()
	{
        Random random = new Random();
	        StringBuilder sb = new StringBuilder(20);

	        for (int i = 0; i < 15; i++) {
	            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
	        }

	        return sb.toString();
	}
	
	    public static String BookTimes() {
	        
			
	           LocalDateTime bookingTime = LocalDateTime.now();
	           DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm a");
	           String formattedDate = bookingTime.format(myFormatObj);
	           return formattedDate;
	        
	  }
	    public static String ValidTimes() {
	        
	    	LocalDateTime bookingTime = LocalDateTime.now();
	    	LocalDateTime validtime = bookingTime.plusHours(9);
	           
	    	 DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm a");
	    	 
	    	 String formateddate=validtime.format(formatdate); 
	           return formateddate;
	        
	  }
	             // Valid for
	      

}
