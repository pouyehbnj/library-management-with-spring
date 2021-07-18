/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.search.service;

import com.library.search.model.Book;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
//import static org.springframework.jdbc.core.JdbcOperationsExtensionsKt.query;

/**
 *
 * @author Pouyeh
 */
public class BookSpecification implements Specification<Book> {

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        EntityManager em;
        //riteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        if (criteria.getTitle() != null && !criteria.getTitle().equals("")) {
            predicates.add(cb.equal(root.get("title"), criteria.getTitle()));
            System.out.println("here:" + criteria.getTitle());
            System.out.println("now:" + predicates.get(predicates.size() - 1).toString());
        }
        if (criteria.getPublisher() != null && !criteria.getPublisher().equals("")) {
            predicates.add(cb.equal(root.get("publisher"), criteria.getPublisher()));
            System.out.println("here:" + criteria.getPublisher());
            System.out.println("now:" + predicates.get(predicates.size() - 1));
        }
        if (criteria.getAuthor() != null && !criteria.getAuthor().equals("")) {
            predicates.add(cb.equal(root.get("author"), criteria.getAuthor()));
            System.out.println("here:" + criteria.getAuthor());
            System.out.println("now:" + predicates.get(predicates.size() - 1));
        }
        if (!from.equals("") && !to.equals("")) {

            predicates.add(cb.between(root.get("publishYear"), from, to));
            System.out.println("here:" + from + " " + to);
            System.out.println("now:" + predicates.get(predicates.size() - 1));
        }

        return query.where(cb.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }

    private Book criteria;
    String from;
    String to;

    public BookSpecification(Book criteria, String from, String to) {
        this.criteria = criteria;
        this.from = from;
        this.to = to;
        //System.out.println("title:" + from+ " "+to);

    }

}
