/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.storage.controller;

import com.library.storage.model.Book;
import com.library.storage.model.BookKeyword;
import com.library.storage.model.Comment;
import com.library.storage.model.Keyword;
import com.library.storage.model.User;
import com.library.storage.repository.BookKeywordRepository;
import com.library.storage.repository.BookRepository;
import com.library.storage.repository.KeywordRepository;
import com.library.storage.service.AuthenticationManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Pouyeh
 */
@Controller
public class KeywordController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    BookKeywordRepository bookKeywordRepository;

    @GetMapping("/keyword/add/{book_id}")
    public ModelAndView addKeyword(@PathVariable("book_id") String bookId,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session) {
        System.out.println("got id:" + bookId);
        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();
        Long id = Long.valueOf(bookId);
        System.out.println("got id:" + id);
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("publisher")) {

                return new ModelAndView("addKeyword");
            } else {
                result.put("succes", false);
                return new ModelAndView("401");
            }
        } catch (Exception e) {
            System.err.println("error with authentication module!" + e.getMessage());
            result.put("succes", false);
            return new ModelAndView("500");

        }
    }

    @RequestMapping(value = "/keyword/add/{book_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Object addKeyword(@RequestParam Map<String, String> req,
            @PathVariable("book_id") String bookId,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        System.out.println("user --------------------- " + username);
        System.out.println("ID --------------------- " + session);
        System.out.println("Book ID --------------------- " + bookId);
        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();
        Long id = Long.valueOf(bookId);

        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("publisher")) {
                // User user = userRepository.findByusername(username);
                String word = req.get("keyword");
                Optional<Book> bookInfo = bookRepository.findById(id);
                Book book = bookInfo.get();
                Keyword keyword = new Keyword(word);
                try {
                    keywordRepository.save(keyword);
                } catch (Exception e) {
                    System.out.println("repeated keyword!");
                } finally {
                    Keyword keywordInfo = keywordRepository.findByWord(word);
                    BookKeyword bookKeyword = new BookKeyword(keywordInfo, book);
                    bookKeywordRepository.save(bookKeyword);
                }

                return "redirect:/book/" + id;
                //return new ModelAndView("redirect:/users");

            } else {
                result.put("succes", false);
                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            result.put("succes", false);
            return new ModelAndView("500");

        }

    }

}
