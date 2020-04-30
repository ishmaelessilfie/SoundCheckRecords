/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Service;

import com.SoundTrackRecords.Repository.UserRepository;
import com.SoundTrackRecords.Service.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.SoundTrackRecords.Model.Users;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ish
 */
@Service
public class UsersDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       Users user = userRepository.findByUsername(username);
       if(user==null){
           throw new UsernameNotFoundException("username not found");
       }
       return new UserPrincipal(user);
    }
    
}
