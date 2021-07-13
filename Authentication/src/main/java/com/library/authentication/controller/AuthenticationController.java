package com.library.authentication.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.library.authentication.model.User;
import com.library.authentication.repository.UserRepository;
import java.io.ObjectInputFilter.Status;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Pouyeh
 */
@Controller
@RestController

public class AuthenticationController {

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        //HttpServletRequest request) {
        model.addAttribute("name", name);
        // request.setAttribute("hello", "test");
        return "greeting";
    }

    @GetMapping("/test")
    String uid(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

    @PostMapping("/login")
    ResponseEntity login(@RequestBody User loginRequest) {
        
    AuthenticationManager authenticationManager = null;
    String username = loginRequest.getUsername();
    String password = loginRequest.getPassword();
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
    Authentication authentication = (Authentication) authenticationManager.authenticate(token);
    SecurityContextHolder
        .getContext()
        .setAuthentication(authentication);
    return new ResponseEntity<> (authentication.getPrincipal(), HttpStatus.OK);
    }
    
}
