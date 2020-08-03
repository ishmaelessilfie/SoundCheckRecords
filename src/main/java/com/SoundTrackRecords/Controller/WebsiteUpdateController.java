/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Controller;

import com.SoundTrackRecords.Model.AppResponse;
import com.SoundTrackRecords.Model.Booking;
import com.SoundTrackRecords.Repository.BookingRepository;
import com.SoundTrackRecords.Service.SerialNumber;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ish
 */

@RestController
public class WebsiteUpdateController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired 
    SerialNumber serialNumber;


    @CrossOrigin(origins= "http://localhost")
    @RequestMapping(value = "/booking", method = {RequestMethod.POST})
    public AppResponse booking(Booking booking ) {
        booking.setNumberofours((serialNumber.convert(booking.getNumberofours())));
        booking.setPhone((serialNumber.convert(booking.getPhone())));
        booking.setDatebooked(new Date());  // dateofbooking
        bookingRepository.save(booking);     
        return new AppResponse("You have successfully booked for session we will contact you and make necessary arrangement", "200");
    }
    
    @RequestMapping(value="/listbooking")
    public List<Booking> listbooking(){
       return  bookingRepository.findAll();
    }
    
     @RequestMapping(value = "/reschedule/{id}", method =  RequestMethod.PUT)
    ResponseEntity<Booking> rescheduleappointment(Booking booking, @PathVariable long id) {
       
        Booking result = bookingRepository.save(booking);
        return ResponseEntity.ok().body(result);
    }
    
   @GetMapping(value = "/delete_booking/{id}")
    public void addressProect(@PathVariable Long id) {
        bookingRepository.deleteById(id);
    }
    
    
}

