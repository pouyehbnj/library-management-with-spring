/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.authentication.repository;

import com.library.authentication.model.User;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pouyeh
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@Query("SELECT u FROM users u WHERE u.username = :username")
           // ,nativeQuery = true)
    List<User> findByUsername(String username);
    @Override
    List<User> findAll();
}
