/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Service;

import com.SoundTrackRecords.Repository.InvoiceRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ish
 */
@Service
public class SerialNumber {
    @Autowired
    InvoiceRepository invoiceRepository;
    public String gebrateInvoice(){
        
        String invno ="SCR"+new SimpleDateFormat("dMYY").format(new Date())+invoiceRepository.getInvoiceCount();
        return invno;
    }
   
    public String generateRegistrationNumber()
	{
	       Random rand = new Random();  
	        int rand_int2 = rand.nextInt(10);
	         System.out.println(rand_int2);
	         String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	         String randomString = "";
	         int length = 3;
	         char [] text = new char [length];
	         for(int i=0; i<length; i++)
	         {
	         	text[i] = characters.charAt(rand.nextInt(characters.length()));
	         }
	         
	         for(int i = 0 ; i<text.length; i++)
	          {
	          	randomString+= text[i];
	            }
	         System.out.println(randomString);
             int rand_int1= rand.nextInt(1000); 
            System.out.println(rand_int1);
	               String vehicleRegistrationNumber = rand_int2+ randomString+rand_int1;
	               System.out.println(vehicleRegistrationNumber);
		         return vehicleRegistrationNumber;
	      	
	}
	
	
}

