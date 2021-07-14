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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.library.authentication.SampleAuthenticationManager;
import com.library.authentication.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.context.request.RequestContextHolder;

/**
 *
 * @author Pouyeh
 */
@Controller
public class AuthenticationController {

    @Autowired
    FindByIndexNameSessionRepository<? extends Session> sessions;

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        //HttpServletRequest request) {
        model.addAttribute("name", name);
        // request.setAttribute("hello", "test");
        return "greeting.html";
    }

    @GetMapping("/test")
    String uid(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    ResponseEntity login(@RequestBody User loginRequest, Principal principal) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        System.out.println("user:" + username);
        System.out.println("role:" + userRepository.findByusername(username).getRole());
        System.out.println("user:" + username);

        AuthenticationManager authenticationManager = new SampleAuthenticationManager(userRepository.findByusername(username).getRole());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = (Authentication) authenticationManager.authenticate(token);
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        Collection<? extends Session> usersSessions = sessions.findByPrincipalName(principal.getName()).values();
        for (Session session : usersSessions) {
            session.setAttribute(username, userRepository.findByusername(username));
            System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT").toString());

        }
        Test test = new Test();
        try {
            test.testSessionIdKeys(username);
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("error ! u need to sleep now");
        }
        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }

    

    @RequestMapping("/sessions")
    public String index(Principal principal) {

        System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId());
        return "greeting";

    }

}
