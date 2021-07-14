/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.storage.service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
public class AuthenticationManager {
    public JSONObject AuthenticationUser(String username, String session) {
        final String uri = "http://localhost:8000/checkSessions";
        Map<String, String> reqBody = new HashMap<String, String>();
        reqBody.put("username", username);
        reqBody.put("sessionID", session);
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.postForObject(uri, reqBody, String.class);
       // JSONObject jsonResponse;
        try {
              JSONObject jsonResponse = new JSONObject(result);
            
            if(jsonResponse.getBoolean("authenticated")){
//                System.out.println("hellllooo!");
//                System.out.println("role:"+jsonResponse.getString("role"));
                
            }else{
                System.out.println("not found!");   
            }
            return jsonResponse;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
