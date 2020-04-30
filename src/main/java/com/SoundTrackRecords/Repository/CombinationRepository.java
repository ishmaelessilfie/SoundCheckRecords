/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Repository;

import com.SoundTrackRecords.Model.Combination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ish
 */

@Repository
public interface CombinationRepository extends JpaRepository<Combination, Long> {
    //Combination findByCombinationtype(String combinationtype);
}
