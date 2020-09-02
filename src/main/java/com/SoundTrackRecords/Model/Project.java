/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@Table(name="project")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project{

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Id 
    private UUID id;
    @NotBlank(message="This field is reqired")
    private String artistename;
    @NotBlank(message="This field is required")
    @Size(min=10, max=10)
    private String phone;
    @Email(message="This field is required")
    @NotBlank(message="This field is required")
    private String email;
    @NotBlank(message="This field is required")
    private String town;
     private Integer numberofhours=0;
    @NotBlank(message="Please enter country")
    private String country;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d MMMM,yyyy")
    @NotNull(message="Please enter date")
    private Date projectstartdate;
    private String number;
    @NotBlank(message="Please enter song title")
    private String songtitle;
    @NotBlank(message="Please enter writer name")
    private String writer;
    
    @NotBlank(message="Please enter producer name")
    private String producer;
    
    @NotBlank(message="Please enter engineer name")
    private String engineer;
    
    @NotBlank(message="This field is reqiured")
    private String genre;
    
    @NotBlank(message = "This field is required")
    private String combination;
    
    @NotBlank(message="This field is required")
    private String projecttype;
    
    @NotBlank(message="This field is required")
    private String activitytype;
    
    private boolean ispdfexcelcreated =false;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Invoice> invoice;  
//    private CheeseType type;
       
}
