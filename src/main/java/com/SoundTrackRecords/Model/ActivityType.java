/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Ish
 */
@Entity
@NoArgsConstructor
@Data
@Table(name="activitytype")
public class ActivityType implements Serializable{
    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activitytype;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient 
    @OneToMany(mappedBy="activitytype", cascade = CascadeType.ALL)
    private List<Project> project;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient 
    @OneToMany(mappedBy="activitytype", cascade = CascadeType.ALL)
    private List<Booking> booking;
    
}
