/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Controller;

import com.SoundTrackRecords.Model.AppResponse;
import com.SoundTrackRecords.Model.Booking;
import com.SoundTrackRecords.Repository.BookingRepository;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ish
 */
@RestController
public class WebsiteUpdateController {

    @Autowired
    BookingRepository bookingRepository;

//    @CrossOrigin(origins="http://soundcheckgh.com")
    @CrossOrigin(origins= {"http://localhost/soundcheckstudio.com.gh","http://soundcheckgh.com"})
    @RequestMapping(value = "/booking", method = {RequestMethod.POST})
    public AppResponse booking(Booking booking) {
        bookingRepository.save(booking);
        
        return new AppResponse("You have successfully booked for session we will contact you and make necessary arrangement", "200");
    }
    
    @RequestMapping(value="/listbooking")
    public List<Booking> listbooking(){
       return  bookingRepository.findAll();
    }
}

