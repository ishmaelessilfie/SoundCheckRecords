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
import javax.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ish
 */


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    //Project List
    @Query("SELECT p.id as id,p.artistename AS artistename, p.songtitle AS songtitle, p.projecttype.projecttype AS projecttype, p.activitytype.activitytype AS activitytype,p.number as number, p.genre.genre AS genre, p.projectstartdate As projectstartdate FROM Project  p ")
  
    public List<ProjectDto> getProjectlist();
    //Vocal Recording count
    @Query("SELECT count(p.activitytype) as projectcount from Project p where p.activitytype= activitytype.activitytype.id and activitytype.activitytype='Recording' group by activitytype.activitytype ")
    public String getProjectVocalRecording();
    //Writing count
    @Query("SELECT count(p.activitytype) as projectcount FROM  Project p where activitytype.activitytype.id=p.activitytype and activitytype.activitytype='Writing' group by activitytype.activitytype ")
    public String getProjectWriting();
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
    public void getupdated(Long id);
    @Transactional
    @Modifying
    @Query("UPDATE Project p set ispdfexcelcreated=false where p.id=(SELECT project as project from Invoice i where p.id=project and i.id=?1)")
    void updatedelete(Long id);
    
    @Query("SELECT ispdfexcelcreated as ispdfexcelcreated from Project where id=?1")
    public boolean getIspdfexcelcreated(Long id);
    
    


  
    
}