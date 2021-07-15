package com.library.storage.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.library.storage.repository.BookManager;
import com.library.storage.repository.BookRepository;
import com.library.storage.service.AuthenticationManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@Controller
public class StorageController {
    @Autowired
    BookRepository bookRepository;
    
    @RequestMapping(value = "/addBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
        MediaType.APPLICATION_JSON_VALUE})
    
    public ResponseEntity greeting(@RequestBody Map<String, String> req,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        System.out.println("user --------------------- " + username);
        System.out.println("ID --------------------- " + session);
        System.err.println(req.get("ISSN"));
        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();
//        JSONObject jsonResponse = manager.AuthenticationUser(username, session);
//            String role = jsonResponse.getString("role");
//            System.out.println("user role:" + role);
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);
            

            if (jsonResponse.getBoolean("authenticated") && role.equals("publisher")) {

                BookManager book = new BookManager();
                book.insertBook(req.get("ISSN"), req.get("title"), req.get("publisher"),
                        req.get("author"), req.get("publishYear"), req.get("image"));
                result.put("success", true);
                System.out.println("the book:"+bookRepository.findByTitleAndPublisher(req.get("publisher"),
                        req.get("title")));
                return ResponseEntity.ok(result);

            } else {
                result.put("succes", false);
                return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            result.put("succes", false);
            return new ResponseEntity<>(result,  HttpStatus.INTERNAL_SERVER_ERROR);

        }

        //return "greeting";
    }

//    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
//            @CookieValue(value = "username") String username,
//            @CookieValue(value = "sessionID") String session,
//            Model model) throws JSONException {
//        AuthenticationManager manager = new AuthenticationManager();
//        try{
//            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
//            if(jsonResponse.getBoolean("authenticated")){
//                System.out.println("user role:"+jsonResponse.getString("role"));
//            }
//        }catch(NullPointerException e){
//            System.err.println("error with authentication module!");
//        }
//        
//        model.addAttribute("name", name);
//        return "greeting";
//    }
}
