package com.library.storage.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.library.storage.service.AuthenticationManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@Controller
public class StorageController {

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        AuthenticationManager manager = new AuthenticationManager();
        try{
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            if(jsonResponse.getBoolean("authenticated")){
                System.out.println("user role:"+jsonResponse.getString("role"));
            }
        }catch(NullPointerException e){
            System.err.println("error with authentication module!");
        }
        
        model.addAttribute("name", name);
        return "greeting";
    }

}
