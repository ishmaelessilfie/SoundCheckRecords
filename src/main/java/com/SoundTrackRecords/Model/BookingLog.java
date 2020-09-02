/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Data
@Table(name="bookinglog")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookingLog {
    
    @Id
     @GeneratedValue(generator = "uuid2")
     @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    private String name;
    private String emai;
    private String phone;
    private String address;
    private String projecttype;
    private Integer numberofours;
    private Date datecreated;
   }

    
