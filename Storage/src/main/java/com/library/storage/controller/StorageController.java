package com.library.storage.controller;/*
                                       * To change this license header, choose License Headers in Project Properties.
                                       * To change this template file, choose Tools | Templates
                                       * and open the template in the editor.
 */

import com.library.storage.model.Book;
import com.library.storage.repository.BookManager;
import com.library.storage.repository.BookRepository;
import com.library.storage.service.AuthenticationManager;
import java.util.Arrays;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
public class StorageController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/book/add")
    public ModelAndView addBooks(@CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session) {

        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("publisher")) {

                return new ModelAndView("addBook");
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

    @RequestMapping(value = "/book/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView addBooks(@RequestParam Map<String, String> req,
            @CookieValue(value = "username") String username, @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        System.out.println("user --------------------- " + username);
        System.out.println("ID --------------------- " + session);
        System.err.println(req.get("ISSN"));
        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("publisher")) {
                Book book = new Book(req.get("ISSN"), req.get("title"), req.get("publisher"), req.get("author"),
                        req.get("publishYear"), req.get("image"));
                System.out.println(req);

                bookRepository.save(book);

                return new ModelAndView("redirect:/books");
            } else {

                result.put("succes", false);
                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            result.put("succes", false);
            return new ModelAndView("500");

        }

        // return "greeting";
    }

    @GetMapping("/books")
    public ModelAndView showBooks(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
            @RequestParam(name = "size", required = false, defaultValue = "3") String size,
            @RequestParam(name = "filter", required = false, defaultValue = "createdAt") String filter,
            @CookieValue(value = "username") String username, @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        AuthenticationManager manager = new AuthenticationManager();
        System.out.println("page:" + page + " size:" + size);
        List<Book> booksList = null;
        JSONObject jsonResponse = null;
        try {
            jsonResponse = manager.AuthenticationUser(username, session);
            if (jsonResponse.getBoolean("authenticated")) {
                System.out.println("user role:" + jsonResponse.getString("role"));
                Pageable pages = (Pageable) PageRequest.of(Integer.valueOf(page) - 1, Integer.valueOf(size),
                        Sort.by(filter).descending());
                Page<Book> books = (Page<Book>) bookRepository.findAll(pages);
                booksList = books.getContent();
                Map<String, Object> response = new HashMap<String, Object>();

                response.put("books", booksList);
                response.put("currentPage", books.getNumber());
                System.out.println("number:" + books.getNumber());
                response.put("noOfPages", books.getTotalPages());
                System.out.println("noOfPages:" + books.getTotalPages());
                response.put("totalElements", books.getTotalElements());
                System.out.println("totalElements:" + books.getTotalElements());
                response.put("size", books.getSize());
                System.out.println("size:" + books.getSize());
                response.put("books", booksList);
                List<Integer> pageNumbers = IntStream.rangeClosed(1, books.getTotalPages()).boxed()
                        .collect(Collectors.toList());
                response.put("pageNumbers", pageNumbers);
                response.put("filterValue", "createdAt");

                if (jsonResponse.getString("role").equals("user")) {
                    return new ModelAndView("books-user", response);
                }
                return new ModelAndView("books", response);
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            return new ModelAndView("500");
        }
        return new ModelAndView("401");

        // return "greeting";
    }

    @GetMapping("/book/{id}")
    public ModelAndView bookDetails(@PathVariable Long id, @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session, Model model) throws JSONException {
        AuthenticationManager manager = new AuthenticationManager();
        Book book = null;
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            if (jsonResponse.getBoolean("authenticated")) {
                System.out.println("user role:" + role);
                Optional<Book> bookDetails = bookRepository.findById(id);
                book = bookDetails.get();
                System.out.println("got it :" + book.getTitle());
                Map<String, Object> response = new HashMap<String, Object>();
                response.put("book", book);
                if (role.equals("user")) 
                    return new ModelAndView("book", response);
                else
                    return new ModelAndView("book-publisher", response);
                

            } else {
                return new ModelAndView("401");

            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            return new ModelAndView("401");
        }

        // return "greeting";
    }

    @GetMapping("/book/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id, @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session, Model model) throws JSONException {
        AuthenticationManager manager = new AuthenticationManager();
        Book book = null;
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            if (jsonResponse.getBoolean("authenticated") && role.equals("publisher")) {
                System.out.println("user role:" + jsonResponse.getString("role"));
                Optional<Book> bookDetails = bookRepository.findById(id);
                book = bookDetails.get();
                System.err.println("got it :" + book.getTitle());

            } else {
                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            return new ModelAndView("500");
        }
        Map<String, Object> response = new HashMap<String, Object>();

        response.put("book", book);
        return new ModelAndView("update-book", response);

    }

    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
        book.setId(id);
        book.setCreatedAt(new Date());
        System.out.println("title" + book.getTitle());
        System.out.println("creat at" + book.getCreatedAt());

        bookRepository.save(book);

        return "redirect:/books";
    }

    @RequestMapping("/remove-book/{id}")
    public Object deleteBook(@PathVariable("id") Long id, @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session, Model model) throws JSONException {
        AuthenticationManager manager = new AuthenticationManager();
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            if (jsonResponse.getBoolean("authenticated") && role.equals("publisher")) {
                System.out.println("user role:" + jsonResponse.getString("role"));
            } else {
                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            return new ModelAndView("500");
        }

        bookRepository.deleteById(id);
        return "redirect:/books";
    }

}
