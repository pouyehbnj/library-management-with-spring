/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.search.repository;

import com.library.search.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ASUS
 */
public interface KeywordRepository  extends JpaRepository<Keyword, Long> {
    
}