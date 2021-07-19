package com.library.search.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.library.search.model.Book;
import com.library.search.model.BookKeyword;
import com.library.search.model.Keyword;
import com.library.search.repository.BookKeywordRepository;
import com.library.search.repository.BookRepository;
import com.library.search.repository.KeywordRepository;
import com.library.search.service.BookSpecification;
import com.library.search.service.AuthenticationManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Pouyeh
 */
@Controller
public class SearchController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    BookKeywordRepository bookKeywordRepository;

    @GetMapping("/book/search/form")
    public ModelAndView searchBooksForm(@CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session) {

        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("user")) {
                System.out.println("here?");
                return new ModelAndView("search-book");
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

    @RequestMapping(value = "/book/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView searchBooks(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
            @RequestParam(name = "size", required = false, defaultValue = "3") String size,
            @RequestParam Map<String, String> req,
            @ModelAttribute Book search,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        String from = req.get("from");
        String to = req.get("to");
        System.out.println("from --------------------- " + from);
        System.out.println("to --------------------- " + to);

        AuthenticationManager manager = new AuthenticationManager();
        System.out.println("page:" + page + " size:" + size);

        List<Book> books = null;
        JSONObject jsonResponse = null;
        try {
            jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            if (jsonResponse.getBoolean("authenticated") && role.equals("user")) {
                Pageable pages = (Pageable) PageRequest.of(Integer.valueOf(page) - 1, Integer.valueOf(size));
                //Page<Book> books;

                Specification<Book> spec;
                //  if ((search.getTitle() != null) ||(search.getAuthor()!=null)||(search.getPublisher()!=null)){
               // if (from != "" && to != "") {
                    spec = new BookSpecification(search, from, to);

//                } else {
//                    spec = new BookSpecification(search, null, null);
//
//                }

                books = bookRepository.findAll(spec);//, pages);
                //    } 
//                else {
//                    books = (Page<Book>) bookRepository.findAll(pages);
//                }

                //booksList = books.getContent();
                Map<String, Object> response = new HashMap<String, Object>();

                response.put("books", books);
//                response.put("currentPage", books.getNumber());
//                System.out.println("currentPage:" + books.getNumber());
//                response.put("noOfPages", books.getTotalPages());
//                System.out.println("noOfPages:" + books.getTotalPages());
//                response.put("filterValue", "createdAt");

                if (jsonResponse.getString("role").equals("user")) {
                    return new ModelAndView("books-user", response);
                }
                return new ModelAndView("books", response);
            } else {
                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            return new ModelAndView("500");
        }

    }

    @GetMapping("/book/search/keyword/form")
    public ModelAndView searchKeywordsForm(@CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session) {

        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("user")) {
                //System.out.println("here?");
                return new ModelAndView("search-keyword");
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

    @RequestMapping(value = "/book/search/keyword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView searchKeywords(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
            @RequestParam(name = "size", required = false, defaultValue = "3") String size,
            @RequestParam Map<String, String> req,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {

        AuthenticationManager manager = new AuthenticationManager();
        System.out.println("page:" + page + " size:" + size);

        List<Book> books=new ArrayList<Book>();
        JSONObject jsonResponse = null;
        try {
            jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            if (jsonResponse.getBoolean("authenticated") && role.equals("user")) {
                String request = req.get("keywords");
                String[] words = request.split(",");
                Object[] ids;
                 ids = bookKeywordRepository.findBookKeywords(Arrays.asList(words), words.length);
                 for(Object id : ids){
                     System.out.println("id is:"+Long.parseLong(String.valueOf(id)));
                    Optional<Book>  bookInfo = bookRepository.findById(Long.parseLong(String.valueOf(id)));
                    //if(books.ge)
                     books.add(bookInfo.get());
                    
                 }
               

                Map<String, Object> response = new HashMap<String, Object>();

                response.put("books", books);

                if (jsonResponse.getString("role").equals("user")) {
                    return new ModelAndView("books-user", response);
                }
                return new ModelAndView("books", response);
            } else {
                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            //e.printStackTrace();
            System.err.println("error with authentication module!");
            return new ModelAndView("500");
        }

    }

}
