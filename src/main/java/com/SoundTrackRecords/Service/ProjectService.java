/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Service;

import com.SoundTrackRecords.DTO.ProjectDto;
import com.SoundTrackRecords.Repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ish
 */
@Service
public class ProjectService {
   @Autowired
   ProjectRepository projectRepository;
 
   
//   @Cacheable(cacheNames = { "projectCache" })
   public List<ProjectDto> getProjectList(){
      return projectRepository.getProjectlist();
   }
   
   
}
