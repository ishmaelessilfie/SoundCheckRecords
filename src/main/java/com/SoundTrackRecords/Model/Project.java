/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Ish
 */

@Entity
@ToString
@NoArgsConstructor
@Data
@Table(name="project")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project implements Serializable{

  
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message="Please enter artiste name")
    private String artistename;
    @NotNull(message="Please enter phone number")
    @Size(min=10, max=10)
    private String phone;
    @Email(message="Please email is invalid, check and enter again")
    @NotNull(message="Please enter email")
    private String email;
    @NotNull(message="Please enter town/village/city")
    private String town;
     private Integer numberofhours=0;
    @NotNull(message="Please enter country")
    private String country;
    @NotNull(message="Please enter date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date projectstartdate;
    private String number;
    @NotNull(message="Please enter song title")
    private String songtitle;
    @ManyToOne
    @NotNull(message="Please select genre")
    private Genre genre;
    @NotNull(message="Please enter writer name")
    private String writer;
    @NotNull(message="Please enter producer name")
    private String producer;
    @NotNull(message="Please enter engineer name")
    private String engineer;
    @ManyToOne
    @NotNull(message="Please select combination")
    private Combination combination;
    @ManyToOne
    @NotNull(message="Please select project type")
    private ProjectType projecttype;
    @ManyToOne
    @NotNull(message="Please enter activity type")
    private ActivityType activitytype;
    private boolean ispdfexcelcreated =false;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Invoice> invoice;
    
    private CheeseType type;
    
    
  
    
}
