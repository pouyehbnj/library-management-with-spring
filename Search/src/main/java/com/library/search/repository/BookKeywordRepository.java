/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.search.repository;

import com.library.search.model.Book;
import com.library.search.model.BookKeyword;
import com.library.search.model.Keyword;
import java.util.List;
import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pouyeh
 */
@Repository
public interface BookKeywordRepository extends JpaRepository<BookKeyword, Long> {

    // List<BookKeyword> findAllByKeyword(Keyword keyword);

    // String query = "SELECT distinct b.id FROM books b INNER JOIN (SELECT
    // bk.book_id "
    // + "FROM book_keywords bk INNER JOIN books b "
    // + "ON b.id = bk.book_id "
    // + "INNER JOIN keywords k "
    // + "ON k.id = bk.keyword_id "
    // + "WHERE k.word IN :words) bb "
    // + "ON b.id = bb.book_id";

   String query = "SELECT distinct b.id"
+"  FROM   books b"
+"  INNER JOIN (SELECT   bk.book_id"
+"                 FROM  book_keywords bk"
+"                          INNER JOIN books b"
+"                            ON b.id = bk.book_id"
+"                          INNER JOIN keywords kw"
+"                            ON kw.id = bk.keyword_id"
+"                 WHERE    kw.word IN :words"
+"                 GROUP BY bk.book_id"
+"                 HAVING   Count(bk.book_id) = :length) bb"
+"       ON b.id = bb.book_id;";



    @Query(value = query, nativeQuery = true)

    Object[] findBookKeywords(@Param List<String> words, @Param int length);

}
