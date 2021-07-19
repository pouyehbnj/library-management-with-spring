/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.storage.repository;


import com.library.storage.model.BookKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Pouyeh
 */
public interface BookKeywordRepository extends JpaRepository<BookKeyword, Long>{
    
}
