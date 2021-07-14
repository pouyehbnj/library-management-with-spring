
package com.library.search.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.library.search.service.RedisHandler;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
/**
 *
 * @author ASUS
 */
@Controller
public class SearchController {
    	@GetMapping("/")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
         @RequestMapping(value = "/checkSessions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity checkSessions(@RequestBody Map<String, String> req) {
        String username = req.get("username");
        String id = req.get("sessionID");
        System.out.println("user --------------------- " + username);
        System.out.println("ID --------------------- " + id);

        try {
            RedisHandler redis = new RedisHandler();
            redis.findSessions(id);
            
        } catch (Exception ex) {
            System.out.println("error:" + ex);
            return new ResponseEntity<>("Redis Error", HttpStatus.INTERNAL_SERVER_ERROR);
            // Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE,
            // null, ex);
        }
        System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId());
        return ResponseEntity.ok("Authentication Confirmed");
        
    }
}
