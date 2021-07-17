/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.storage.controller;

import com.library.storage.model.Book;
import com.library.storage.model.Comment;
import com.library.storage.model.User;
import com.library.storage.repository.BookRepository;
import com.library.storage.repository.CommentRepository;
import com.library.storage.repository.UserRepository;
import com.library.storage.service.AuthenticationManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class CommentController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/comments/{book_id}")
    public ModelAndView comments(@PathVariable("book_id") String bookId,
            @RequestParam(name = "page", required = false, defaultValue = "1") String page,
            @RequestParam(name = "size", required = false, defaultValue = "3") String size,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        AuthenticationManager manager = new AuthenticationManager();
        System.out.println("page:" + page + " size:" + size);
        Long id = Long.valueOf(bookId);
        List<Comment> commentsList = null;
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);
            if (jsonResponse.getBoolean("authenticated") && role.equals("user")) {
                Pageable pages = (Pageable) PageRequest.of(Integer.valueOf(page) - 1,
                        Integer.valueOf(size));
                User user = userRepository.findByusername(username);
                Optional<Book> bookInfo = bookRepository.findById(id);
                Book book = bookInfo.get();
                Page<Comment> comments = commentRepository.findByBook(book, pages);
                commentsList = comments.getContent();
                Map<String, Object> response = new HashMap<String, Object>();
                response.put("comments", commentsList);
                response.put("user",user);
                response.put("book",book);
                response.put("currentPage", comments.getNumber());
                System.out.println("currentPage:" + comments.getNumber());
                response.put("noOfPages", comments.getTotalPages());
                System.out.println("noOfPages:" + comments.getTotalPages());
                for (Map.Entry<String, Object> entry : response.entrySet()) {
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                }
                return new ModelAndView("comments", response);
            } else {

                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            return new ModelAndView("500");
        }

        //return "greeting";        
    }

    @GetMapping("/comment/add/{book_id}")
    public ModelAndView addComments(@PathVariable("book_id") String bookId,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session) {
        System.out.println("HERE:" + bookId);
        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();
        Long id = Long.valueOf(bookId);
        System.out.println("got id:" + id);
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("user")) {

                return new ModelAndView("addComment");
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

    @RequestMapping(value = "/comment/add/{book_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Object addComments(@RequestParam Map<String, String> req,
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
        System.out.println("got id:" + id);
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("user")) {
                User user = userRepository.findByusername(username);
                Optional<Book> bookInfo = bookRepository.findById(id);
                Book book = bookInfo.get();
                Comment comment = new Comment(user, book, req.get("content"));
                System.out.println(req);
                commentRepository.save(comment);
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
