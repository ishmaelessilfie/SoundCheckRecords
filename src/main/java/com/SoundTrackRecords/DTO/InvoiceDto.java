/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.DTO;

import java.util.Date;

/**
 *
 * @author Ish
 */
public interface InvoiceDto {
    Date   getDatecreated();    
    Double getMasteringcost();
    Double getMixingcost(); 
    Double getStudiotimecost(); 
    Integer getTimeinhr();
    Double getTotalcost(); 
    String getInvoiceno(); 
    Double getCostofintruments(); 
    Double getTotalstudicost();
    String getCountry();
    String getArtistename();
    String getPhone();
    String getEmail();
    String getTown();
    
}
