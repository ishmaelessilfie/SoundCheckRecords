/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Service;


import com.SoundTrackRecords.Model.Invoice;
import com.SoundTrackRecords.Model.Project;
import com.SoundTrackRecords.Repository.InvoiceRepository;
import com.SoundTrackRecords.Repository.ProjectRepository;
import com.SoundTrackRecords.Repository.UserRepository;

import java.util.Date;
//import javax.activation.DataSource;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

//import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


/**
 *
 * @author Ish
 */
@Service
public class CommonService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    UserRepository usersRepository;
    @Autowired
    private final SerialNumber sn;

    public CommonService(SerialNumber sn) {
        this.sn = sn;
    }
 


     
    @Transactional
    public void saveInvoice( Long id, Double studiotimecost,  Double mixingcost, int timeinhr,Double masteringcost, Double costofintruments) {
      Invoice inv=new Invoice();
        inv.setMixingcost(mixingcost);
        inv.setMasteringcost(masteringcost);
        inv.setTimeinhr(timeinhr);
        inv.setCostofintruments(costofintruments);
        inv.setStudiotimecost(studiotimecost);
        inv.setTotalstudicost(timeinhr * studiotimecost);
        inv.setProject(projectRepository.getOne(id)); //FK
        inv.setDatecreated(new Date());
        inv.setInvoiceno(sn.gebrateInvoice());
        inv.setTotalcost((studiotimecost * timeinhr) + (mixingcost + masteringcost + costofintruments));
        invoiceRepository.save(inv);
        projectRepository.getupdated(id);
               
    }
	public String deleteproj(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			projectRepository.deleteById(id);
			jsonObject.put("message","Project deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
        
        public String deleteinv(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			invoiceRepository.deleteById(id);
			jsonObject.put("message","Invoice deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
        
        
         public String deleteuser(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			usersRepository.deleteById(id);
			jsonObject.put("message","Invoice deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

    
}
    

    
  