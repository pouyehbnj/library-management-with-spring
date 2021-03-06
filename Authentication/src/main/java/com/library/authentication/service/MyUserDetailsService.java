package com.library.authentication.service;

import com.library.authentication.model.User;
import com.library.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    //
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("userrrr nameee:" + username);
        User user = userRepository.findByusername(username);
        System.out.println("userrrr :" + user.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found !");
        }

        return (UserDetails) user;
    }
    // public UserDetails loadUserByUsernameAndDomain(String username, String
    // password)
    // throws UsernameNotFoundException {
    // if (StringUtils.isAnyBlank(username, password)) {
    // throw new UsernameNotFoundException("Username and domain must be provided");
    // }
    // User user = userRepository.findUser(username, password);
    // if (user == null) {
    // throw new UsernameNotFoundException(
    // String.format("Username not found for domain, username=%s, domain=%s",
    // username, domain));
    // }
    // return user;
    // }
}
