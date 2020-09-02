/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.DTO;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Ish
 */
public interface InvoiceList {
    UUID getProject();
    UUID getId();
    Date getDatecreated();
    String getInvoiceno();
    String getNumber();
    
}
