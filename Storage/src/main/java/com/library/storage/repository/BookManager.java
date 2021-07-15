/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.storage.repository;

import com.library.storage.model.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

/**
 *
 * @author Pouyeh
 */
public class BookManager {
    @Transactional
    public void insertBook(String ISSN, String title, String publisher, String author, String publishYear, String image) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Book_details");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Book book = new Book("1234","test","test","1377","test","book");
        em.persist(book);

        em.getTransaction().commit();
    }
}
