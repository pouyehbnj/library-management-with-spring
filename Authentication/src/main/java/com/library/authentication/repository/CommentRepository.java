/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.authentication.repository;

import com.library.authentication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Pouyeh
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
