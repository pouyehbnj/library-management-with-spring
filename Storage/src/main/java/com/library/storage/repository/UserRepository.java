/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.storage.repository;

import com.library.storage.model.Book;
import com.library.storage.model.User;
import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pouyeh
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByusername(String username);
}
