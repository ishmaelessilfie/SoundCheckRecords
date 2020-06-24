/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ish
 */
@Controller
public class AuthenticationController {
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/logout")
    public String logoutPage() {   
        return "login";
    }
      
}
