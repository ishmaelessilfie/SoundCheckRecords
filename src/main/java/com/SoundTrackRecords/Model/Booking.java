
package com.SoundTrackRecords.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
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
@Data
@Table(name="booking")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Booking{
    
//    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(generator = "uuid2")
     @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    private String email;
    private String phone; 
    private ActivityType activitytype;
    private String numberofours ; 
    private String country;
    private String address;
    private String message;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateofbooking;
    @PrePersist
    public void setDateofbooking(){this.dateofbooking=new Date();} 

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date rescheduleddate;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datebooked; 
    private int seen ;
  
    

    
    
   }

    
