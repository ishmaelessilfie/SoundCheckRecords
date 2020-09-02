/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author Ish
 */
@Entity
@ToString
@NoArgsConstructor
@Data
@Table(name="websiteprojectupdatelog")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WebsiteProjectUpdateLog{
   @Id
     @GeneratedValue(generator = "uuid2")
     @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String photo;
    private String title;
    private String doneby;
    private Date datecreated;
}
