/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Pouyeh
 */
public class SimpleAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Override
//    public Authentication attemptAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response)
//            throws AuthenticationException {
//        UsernamePasswordAuthenticationToken authRequest
//                = getAuthRequest(request);
//        setDetails(request, authRequest);
//
//        return this.getAuthenticationManager()
//                .authenticate(authRequest);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthRequest(
//            HttpServletRequest request) {
//
//        String username = obtainUsername(request);
//        String password = obtainPassword(request);
//        System.out.println("userrrrrrrrrr;"+username);
//        System.out.println("passsssssssssss;"+password);
//        return new UsernamePasswordAuthenticationToken(
//                username, password);
//    }
}
