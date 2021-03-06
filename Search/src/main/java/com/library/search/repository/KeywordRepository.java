/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.search.repository;

import com.library.search.model.Keyword;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pouyeh
 */
@Repository
public interface KeywordRepository  extends JpaRepository<Keyword, Long> {
    List<Keyword> findAllByWordIn(List<String> words);
    
}
