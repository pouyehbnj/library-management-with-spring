/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.search.service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.HttpClientErrorException;
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
       // System.out.println("uri:"+session);
        // JSONObject jsonResponse;
        try {
            //System.out.println("uri:"+username);
            
            String result = restTemplate.postForObject(uri, reqBody, String.class);
            System.out.println("res:" + result);
            JSONObject jsonResponse = new JSONObject(result);
            //if (jsonResponse.has("authenticated")) {
                System.out.println("helloooooooooo");
                if (jsonResponse.getBoolean("authenticated")) {

                } else {

                    System.out.println("not found!");
                }
                return jsonResponse;
//            } else {
//                Map<String, String> res = new HashMap<String, String>();
//                JSONObject rejected = new JSONObject(res);
//
//                return rejected;
//            }
        } catch (JSONException e) {
            System.out.println("user authentication faild");
            Map<String, Object> res = new HashMap<String, Object>();
            res.put("authenticated", false);
            JSONObject rejected = new JSONObject(res);
            //e.printStackTrace();
            return rejected;
        }catch(HttpClientErrorException error){
            System.out.println("user authentication faild");
            //error.printStackTrace();
            Map<String, Object> res = new HashMap<String, Object>();
            res.put("authenticated", false);
            JSONObject rejected = new JSONObject(res);
            return rejected;
        }
    }
}
