/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.search.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Pouyeh
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy =GenerationType.TABLE)
    public Long  id;

    @Column(name = "ISSN")
    private String ISSN;

    @Column(name = "title")
    private String title;

    @Column(name = "publisher")
    private String publisher;
    
    @Column(name = "author")
    private String author;

    @Column(name = "publish_year")
    private String publishYear;

    @Column(name = "image",length = 1000)
    private String image;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getISSN() {
        return ISSN;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public String getImage() {
        return image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    

    public Book(String ISSN, String title, String publisher, String author, String publishYear, String image){ //,Date createdAt) {
       // this.id = id;
        this.ISSN = ISSN;
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.publishYear = publishYear;
        this.image = image;
        //this.createdAt = createdAt;
    }

    public Book() {
    }

}
