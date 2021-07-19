/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.search.service;

import com.library.search.model.Book;
import com.library.search.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Pouyeh
 */
public class UserSpecification implements Specification<User>{
     @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        EntityManager em;
        //riteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        if (criteria.getUsername() != null && !criteria.getUsername() .equals("")) {
            predicates.add(cb.equal(root.get("username"), criteria.getUsername()));
            System.out.println("here:" + criteria.getUsername() );
            System.out.println("now:" + predicates.get(predicates.size() - 1).toString());
        }
        if (criteria.getPassword() != null && !criteria.getPassword().equals("")) {
            predicates.add(cb.equal(root.get("password"), criteria.getPassword()));
            System.out.println("here:" + criteria.getPassword());
            System.out.println("now:" + predicates.get(predicates.size() - 1));
        }
        if (criteria.getRole() != null && !criteria.getRole().equals("")) {
            predicates.add(cb.equal(root.get("role"), criteria.getRole()));
            System.out.println("here:" + criteria.getRole());
            System.out.println("now:" + predicates.get(predicates.size() - 1));
        }
        

        return query.where(cb.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }

    private User criteria;
 
    public UserSpecification(User criteria) {
        this.criteria = criteria;
       
        //System.out.println("title:" + from+ " "+to);

    }
}
