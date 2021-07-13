/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.authentication.repository;

import com.library.authentication.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

/**
 *
 * @author Pouyeh
 */
public class UserManager {

    @Transactional
    public void insertUser() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("User_details");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = new User("user","test","test");
        em.persist(user);

        em.getTransaction().commit();
    }
}
