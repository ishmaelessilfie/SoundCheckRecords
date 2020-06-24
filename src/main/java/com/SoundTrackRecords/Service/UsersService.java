/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Service;


import com.SoundTrackRecords.Model.Users;
import org.springframework.stereotype.Service;


/**
 *
 * @author Ish
 */
@Service
public interface UsersService {

    public boolean saveUsers(Users users) ;
       
    
            
       
    
}
