/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.search.controller;

import com.library.search.model.Book;
import com.library.search.model.User;
import com.library.search.repository.UserRepository;
import com.library.search.service.AuthenticationManager;
import com.library.search.service.BookSpecification;
import com.library.search.service.UserSpecification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Pouyeh
 */
@Controller
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/user/search/form")
    public ModelAndView searchUsersForm(@CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session) {

        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("admin")) {
                System.out.println("here?");
                return new ModelAndView("search-user");
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

    @RequestMapping(value = "/user/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView searchBooks(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
            @RequestParam(name = "size", required = false, defaultValue = "3") String size,
            //@RequestParam Map<String, String> req,
            @ModelAttribute User search,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {

        AuthenticationManager manager = new AuthenticationManager();
        System.out.println("page:" + page + " size:" + size);

        List<User> users = null;
        JSONObject jsonResponse = null;
        try {
            jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            if (jsonResponse.getBoolean("authenticated") && role.equals("admin")) {
                
                System.out.println("reqqq:"+search.getPassword());
                Specification<User> spec;
                //  if ((search.getTitle() != null) ||(search.getAuthor()!=null)||(search.getPublisher()!=null)){
                // if (from != "" && to != "") {
                spec = new UserSpecification(search);
                users = userRepository.findAll(spec);
  
                Map<String, Object> response = new HashMap<String, Object>();

                response.put("users", users);
                return new ModelAndView("users", response);
                
            } else {
                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            return new ModelAndView("500");
        }

    }

}
