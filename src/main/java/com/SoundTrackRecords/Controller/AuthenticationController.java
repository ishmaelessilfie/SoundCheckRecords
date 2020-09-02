/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Controller;


import com.SoundTrackRecords.Model.Users;
import com.SoundTrackRecords.Repository.InvoiceRepository;
import com.SoundTrackRecords.Repository.ProjectRepository;
import com.SoundTrackRecords.Repository.UserRepository;
import com.SoundTrackRecords.Service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 *
 * @author Ish
 */
@Controller
public class AuthenticationController {

//    @Autowired
//    UserRepository userRepository;

    @RequestMapping("/login")
    public String login(Model model, String error) {
//        Users user = userRepository.findByUsername(username);
        if (error != null) {
            model.addAttribute("error", "username and password invalid");
        }
              return "login";
    }
    
    @RequestMapping("/logout")
    public String logoutPage() {   
        return "login";
    }
    
    @RequestMapping("/")
    public String index() {
        
        return "index"; //index.html page 
    }
    
    @GetMapping(value = "/invoiceList")
    public String invoiceList() {    
        return "invoicelist";
    }
    
    @RequestMapping("/projList")
    public String projectList() {
        return "table"; //tabel.html page 
    }
    
     @RequestMapping("/usersList")
    public String usersList() {
        return "userlist"; //tabel.html page 
    }
    @RequestMapping("/artistList")
    public String artistlist() {
        return "artistlist"; //tabel.html page 
    }
    
    @RequestMapping("/songList")
    public String songlist() {
        return "songlist"; //tabel.html page 
    }
    
    @RequestMapping("/bookings")
    public String bookings() {
        return "booking"; //tabel.html page 
    
    
}

    }