/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Ish
 */
@Entity
@ToString
@NoArgsConstructor
//@AllArgsConstructor
@Data
@Table(name="invoice")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Invoice{
    //private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private UUID id;
    private Double masteringcost;
    private Double studiotimecost;
    private Double mixingcost;
    @JsonFormat(pattern="yyyy/MM/dd")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @PrePersist
    public void setDatecreated(){this.datecreated=new Date();}
    
    private Double totalcost;
    private int timeinhr;
    private String invoiceno;
    private Double costofintruments;
    private Double totalstudicost;
    @ManyToOne
    private Project project;
   
    
    
    
    
    
    
    }

   

    
    
    
    
    
    

