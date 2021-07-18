/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.search.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author Pouyeh
 */
@Entity
@Table(name = "book_keywords")
public class BookKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;
    
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    
    
    public BookKeyword(Keyword keyword, Book book) {
        this.keyword = keyword;
        this.book = book;
    }

    public BookKeyword() {
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public Book getBook() {
        return book;
    }
    
    
    
}
