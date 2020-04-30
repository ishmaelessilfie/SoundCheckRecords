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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ish
 */

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
       
// @Query("SELECT i.id, i.datecreated as datt, i.masteringcost as mas,i.mixingcost as mix,i.timeinhr as tr,i.studiotimecost as st,i.totalcost as tt, i.project as pi FROM Invoice as i WHERE i.project.id=?1 ORDER BY i.datecreated desc")
// List<Invoice> getInvoicesById(Long id);
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
            + "p.email as email,"
            + "p.phone as phone,"
            + "p.town as town,"
            + "p.country as country from Invoice i join Project p on i.project=p.id where i.id=?1")
    public InvoiceDto getInvoice(Long id);
    //long, java.util.Date, double, double, int, double, double
   @Query("SELECT count(i)+1 as invoicecount from Invoice i")
    public String getInvoiceCount();   
   @Query("SELECT p.id as project, i.id as id,i.datecreated as datecreated,i.invoiceno as invoiceno,p.number as number from Invoice i join Project p on i.project=p.id")
   public List<InvoiceList> invoiceList();
    
}

