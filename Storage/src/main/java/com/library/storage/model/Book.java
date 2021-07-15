/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.storage.model;

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
    @GeneratedValue(strategy =GenerationType.IDENTITY)
// GenerationType.AUTO)
    @Column(name = "id",updatable = false, nullable = false)
    private int  id;

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

    @Column(name = "image")
    private String image;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public int getId() {
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

}
