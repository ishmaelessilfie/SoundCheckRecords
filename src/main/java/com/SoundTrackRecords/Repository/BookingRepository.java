/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Repository;

import com.SoundTrackRecords.Model.Booking;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ish
 */
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    @Query("select count(*) from Booking where seen = 0")
    public String getBooking();
    
    
}
