/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.storage.repository;

import com.library.storage.model.Keyword;
import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pouyeh
 */
@Repository
public interface KeywordRepository  extends JpaRepository<Keyword, Long> {
    Keyword findByWord(String word);
    
}
