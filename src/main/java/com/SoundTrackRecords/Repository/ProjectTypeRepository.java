/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Repository;

import com.SoundTrackRecords.Model.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Ish
 */
public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long> {
    ProjectType findByProjecttype(String projecttype);
}
