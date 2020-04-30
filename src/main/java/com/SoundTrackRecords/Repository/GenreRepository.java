/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Repository;

import com.SoundTrackRecords.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ish
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {
   Genre findByGenre(String genre);
}
