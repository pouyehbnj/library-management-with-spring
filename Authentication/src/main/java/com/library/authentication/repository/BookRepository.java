/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.authentication.repository;

import com.library.authentication.model.Book;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
/**
 *
 * @author Pouyeh
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAndPublisher(String title, String publisher);
   // List<Book> findAll(Pageable pageable);
}
