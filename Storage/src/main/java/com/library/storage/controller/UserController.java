package com.library.storage.controller;

import com.library.storage.model.User;
import com.library.storage.repository.UserRepository;
import com.library.storage.service.AuthenticationManager;
import java.util.Arrays;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;



@Controller

public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/add")
    public ModelAndView addUsers(@CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session) {

        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("admin")) {

                return new ModelAndView("addUser");
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

    @RequestMapping(value = "/user/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView addUsers(@RequestParam Map<String, String> req,
            @CookieValue(value = "username") String username, @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        System.out.println("user --------------------- " + username);
        System.out.println("ID --------------------- " + session);
        System.err.println(req.get("ISSN"));
        AuthenticationManager manager = new AuthenticationManager();
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            System.out.println("user role:" + role);

            if (jsonResponse.getBoolean("authenticated") && role.equals("admin")) {
                User user = new User(req.get("role"), req.get("username"), req.get("password"));
                System.out.println(req);

                userRepository.save(user);

                return new ModelAndView("redirect:/users");

            } else {
                result.put("succes", false);
                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            result.put("succes", false);
            return new ModelAndView("500");

        }

        // return "greeting";
    }

    @GetMapping("/users")
    public ModelAndView users(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
            @RequestParam(name = "size", required = false, defaultValue = "5") String size,
            @RequestParam(name = "filter", required = false, defaultValue = "role") String filter,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        AuthenticationManager manager = new AuthenticationManager();
        System.out.println("page:" + page + " size:" + size);
        List<User> usersList = null;
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            if (jsonResponse.getBoolean("authenticated")) {
                System.out.println("user role:" + jsonResponse.getString("role"));
                Pageable pages = (Pageable) PageRequest.of(Integer.valueOf(page) - 1, Integer.valueOf(size),
                        Sort.by(filter).descending());
                Page<User> users = (Page<User>) userRepository.findAll(pages);
                usersList = users.getContent();
                //   model.addAttribute(users);
//                for(User user:usersList)
//                System.out.println(user.getPublisher());

            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
        }
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("users", usersList);

        for (Map.Entry<String, Object> entry : response.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
       }
        return new ModelAndView("users", response);

        //return "greeting";        
    }

    @GetMapping("/user/{id}")
    public ModelAndView userDetails(@PathVariable String id,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        AuthenticationManager manager = new AuthenticationManager();
        User user = null;
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            if (jsonResponse.getBoolean("authenticated")) {
                System.out.println("user role:" + jsonResponse.getString("role"));
                Optional<User> userDetails = userRepository.findById(Long.valueOf(id));
                user = userDetails.get();
                System.err.println("got it :" + user.getUsername());
                //   model.addAttribute(users);
//                for(User user:usersList)
//                System.out.println(user.getPublisher());

            } else {
                //result.put("succes", false);
                //@TODO --> CHANGE TO UNAUTHORIZED
                return new ModelAndView("user");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
        }
        Map<String, Object> response = new HashMap<String, Object>();

        response.put("user", user);
        return new ModelAndView("user", response);

        //return "greeting";        
    }

    @GetMapping("/user/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        AuthenticationManager manager = new AuthenticationManager();
        User user = null;
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            if (jsonResponse.getBoolean("authenticated") && role.equals("admin")) {
                System.out.println("user role:" + jsonResponse.getString("role"));
                Optional<User> userDetails = userRepository.findById(id);
                user = userDetails.get();
                System.err.println("got it :" + user.getUsername());

            } else {
                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            return new ModelAndView("401");
        }
        Map<String, Object> response = new HashMap<String, Object>();

        response.put("user", user);
        return new ModelAndView("update-user", response);

    }

    @RequestMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") Long id, User user, BindingResult result, Model model) {
        user.setId(id);
        System.out.println("title" + user.getUsername());

        userRepository.save(user);

        return "redirect:/users";
    }

    @RequestMapping("/remove-user/{id}")
    public Object deleteUser(@PathVariable("id") Long id,
            @CookieValue(value = "username") String username,
            @CookieValue(value = "sessionID") String session,
            Model model) throws JSONException {
        AuthenticationManager manager = new AuthenticationManager();
        try {
            JSONObject jsonResponse = manager.AuthenticationUser(username, session);
            String role = jsonResponse.getString("role");
            if (jsonResponse.getBoolean("authenticated") && role.equals("admin")) {
                System.out.println("user role:" + jsonResponse.getString("role"));
            } else {
                return new ModelAndView("401");
            }
        } catch (NullPointerException e) {
            System.err.println("error with authentication module!");
            return new ModelAndView("401");
        }

        userRepository.deleteById(id);
        return "redirect:/users";
    }

    // public String greeting(@RequestParam(name = "name", required = false,
    // defaultValue = "World") String name,
    // @CookieValue(value = "username") String username,
    // @CookieValue(value = "sessionID") String session,
    // Model model) throws JSONException {
    // AuthenticationManager manager = new AuthenticationManager();
    // try{
    // JSONObject jsonResponse = manager.AuthenticationUser(username, session);
    // if(jsonResponse.getBoolean("authenticated")){
    // System.out.println("user role:"+jsonResponse.getString("role"));
    // }
    // }catch(NullPointerException e){
    // System.err.println("error with authentication module!");
    // }
    //
    // model.addAttribute("name", name);
    // return "greeting";
    // }
}

