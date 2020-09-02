/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Repository;

import com.SoundTrackRecords.DTO.ArtisteListDto;
import com.SoundTrackRecords.DTO.ProjectDto;
import com.SoundTrackRecords.DTO.SongDetailDto;
import com.SoundTrackRecords.Model.Project;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ish
 */


@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    //Project List
    @Query("SELECT p.id as id,p.artistename AS artistename, p.songtitle AS songtitle, p.projecttype AS projecttype, p.activitytype AS activitytype,p.number as number, p.genre AS genre, p.projectstartdate As projectstartdate FROM Project  p ")
  
    public List<ProjectDto> getProjectlist();
    //Vocal Recording count
     //Writing count
    @Query("SELECT count(activitytype) as projectcount FROM  Project  where activitytype='Writing' ")
    public String getProjectWriting();
    @Query("SELECT count(activitytype) as projectcount from Project where activitytype='Recording'")
    public String getProjectVocalRecording();
    @Query("SELECT count(activitytype) as projectcount from Project where activitytype='Mixing'")
    public String getProjectMixing();
    @Query("SELECT count(activitytype) as projectcount from Project where activitytype='Engineering'")
    public String getProjectEngineering();
    @Query("SELECT count(activitytype) as projectcount from Project where activitytype='Production'")
    public String getProjectProduction();
   
    //Total project count
    @Query("SELECT count(p) as projectcount from Project p ")
    public String getProjectCount();
    //Project detail
  
    //Song detail
    @Query("SELECT p.songtitle as songtitle, p.artistename as artistename, p.producer as producer, p.engineer as engineer, p.writer as writer, p.projectstartdate As projectstartdate  from Project p")
    public List<SongDetailDto> getSongDetail();
    //Artiste detail
    @Query("SELECT p.artistename as artistename, p.phone as phone, p.email as email, p.town as town, p.country as country,p.projectstartdate As projectstartdate from Project as p")
    public List<ArtisteListDto> getArtisteDetail();
    //
    @Modifying
    @Query("UPDATE Project p set ispdfexcelcreated=true where p.id=?1")
    public void getupdated(UUID id);
    @Transactional
    @Modifying
    @Query("UPDATE Project p set ispdfexcelcreated=false where p.id=(SELECT project as project from Invoice i where p.id=project and i.id=?1)")
    void updatedelete(UUID id);
    
    @Query("SELECT ispdfexcelcreated as ispdfexcelcreated from Project where id=?1")
    public boolean getIspdfexcelcreated(UUID id);

    List<Project> findAllByOrderByProjectstartdate();
    
    


  
    
}