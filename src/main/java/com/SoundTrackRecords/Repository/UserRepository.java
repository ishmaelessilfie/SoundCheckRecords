/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SoundTrackRecords.Model.Users;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ish
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    
   

   @Query ("SELECT photo as photo from Users where username= ?1")
   public String getUserPhoto(String username);
   @Query("SELECT password as password from Users where username=?1")  
   public String getPassword(String username);
   @Query("SELECT photo as photo from Users where username=?1")  
   public String getPhotos(String username);
   public Users findByUsername(String un);
//   public Users findUserById(String un);

   
    

   
   
    

 
    
}
