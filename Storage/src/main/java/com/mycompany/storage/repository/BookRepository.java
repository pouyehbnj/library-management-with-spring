/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.storage.repository;

import com.mycompany.storage.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Pouyeh
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}
