/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Repository;

import com.SoundTrackRecords.DTO.InvoiceDto;
import com.SoundTrackRecords.DTO.InvoiceList;
import com.SoundTrackRecords.Model.Invoice;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ish
 */

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> { 
   @Query("SELECT i.datecreated as datecreated,"
            + "i.masteringcost as masteringcost,"
            + "i.mixingcost as mixingcost,"
            + "i.studiotimecost as studiotimecost,"
            + "i.timeinhr as timeinhr,"
            + "i.totalcost as totalcost,"
            + "i.invoiceno as invoiceno,"
            + "i.costofintruments as costofintruments,"
            + " i.totalstudicost as totalstudicost,"
            + "p.artistename as artistename,"
            + "p.email as email from Invoice i join Project p on i.project=p.id where i.id=?1")
   public List<InvoiceDto> getInoiceForPdf(UUID id);
   @Query("SELECT count(i)+1 as invoicecount from Invoice i")
    public String getInvoiceCount();   
   @Query("SELECT p.id as project, i.id as id,i.datecreated as datecreated,i.invoiceno as invoiceno,p.number as number from Invoice i join Project p on i.project=p.id")
   public List<InvoiceList> invoiceList();    
}

