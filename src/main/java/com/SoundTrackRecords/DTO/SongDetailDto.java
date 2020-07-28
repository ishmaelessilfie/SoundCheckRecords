/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author Ish
 */
public interface SongDetailDto{
    
    String getArtistename();
    String getEngineer();
    String getProducer();
    String getWriter();
//    String getCombination();
    String getSongtitle();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date getProjectstartdate();
    
}
