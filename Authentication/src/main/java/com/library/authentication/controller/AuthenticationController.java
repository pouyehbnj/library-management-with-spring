package com.library.authentication.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@Controller
@RestController
public class AuthenticationController {

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model){
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
    
    @GetMapping("/name")
    public String getName(Principal principal) {
        System.out.print(principal.getName());
        return principal.getName();
    }
}
