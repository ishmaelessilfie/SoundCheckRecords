/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Repository;

import com.SoundTrackRecords.Model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Ish
 */
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
    ActivityType findByActivitytype(String activitytype);
}
