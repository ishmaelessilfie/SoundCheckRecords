/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Repository;
import com.SoundTrackRecords.Model.BookingLog;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ish
 */
public interface BookingLogRepository extends JpaRepository<BookingLog, UUID> {
    
}
