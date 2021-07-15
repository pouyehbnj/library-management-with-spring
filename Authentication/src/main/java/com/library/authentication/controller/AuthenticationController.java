package com.library.authentication.controller;

import com.library.authentication.model.User;
import com.library.authentication.repository.UserRepository;
import com.library.authentication.service.SampleAuthenticationManager;
import com.library.authentication.service.RedisHandler;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
public class AuthenticationController {

    @Autowired
    FindByIndexNameSessionRepository<? extends Session> sessions;

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/test")
    String uid(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity login(@RequestParam Map<String, String> loginRequest, Principal principal,
            HttpServletResponse response) {

        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        try {
            User user = userRepository.findByusername(username);

            if (user.getPassword().equals(password)) {
                System.out.println("user:" + username);
                System.out.println("role:" + user.getRole());
                System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId());
//                try{
//                AuthenticationManager authenticationManager = new SampleAuthenticationManager(
//                        user.getRole());
//                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//                Authentication authentication = (Authentication) authenticationManager.authenticate(token);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                }catch(Exception e){
//                    System.out.println("error:"+e);
//                }
                
                //try {
                // Collection<? extends Session> usersSessions =
                // sessions.findByPrincipalName(principal.getName()).values();
                // for (Session session : usersSessions) {
                // System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT").toString());
                //Collection<? extends Session> usersSessions = sessions.findByPrincipalName(principal.getName()).values();
                //
                Cookie cookie = new Cookie("sessionID", RequestContextHolder.currentRequestAttributes().getSessionId());
                response.addCookie(cookie);
                cookie = new Cookie("username", username);
                response.addCookie(cookie);
                System.out.println("session id:" + RequestContextHolder.currentRequestAttributes().getSessionId());
                return new ResponseEntity<>(username, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(username, HttpStatus.UNAUTHORIZED);

            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(username, HttpStatus.UNAUTHORIZED);
        }

//
//            for (Session session : usersSessions) {
//                System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT").toString());
//
//            }
//        } catch (Exception exception) {
//
//            return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
//        }
        // Test test = new Test();
        // try {
        // test.testSessionIdKeys(username);
        // } catch (Exception ex) {
        // System.out.println(ex);
        // System.out.println("error ! u need to sleep now");
        // }
    }

    @RequestMapping(value = "/checkSessions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
        MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity checkSessions(@RequestBody Map<String, String> req) {
        String username = req.get("username");
        String id = req.get("sessionID");
        System.out.println("user --------------------- " + username);
        System.out.println("ID --------------------- " + id);

        try {
            RedisHandler redis = new RedisHandler();
            Map<String, Object> result = new HashMap<String, Object>();
            String role;
            try {
                role = userRepository.findByusername(username).getRole();
            } catch (NullPointerException ex) {
                System.out.println("user not found!");
                result.put("authenticated", false);
                return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            }
            result = redis.findSessions(id, role);

            if ((Boolean) result.get("authenticated")) {
                System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId());
                return ResponseEntity.ok(result);
            } else {
                return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            System.out.println("error:" + ex);
            return new ResponseEntity<>("Redis Error", HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
