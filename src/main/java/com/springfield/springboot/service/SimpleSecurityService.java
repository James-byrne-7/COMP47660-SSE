package com.springfield.springboot.service;

import com.springfield.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SimpleSecurityService implements SecurityService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

//    @Override
//    public String getLoggedInUsername() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if (principal instanceof UserDetails)
//            return ((UserDetails)principal).getUsername();
//        else
//            return null;
//    }

    @Override
    public User getLoggedInUser(Principal principal){
        User user = userService.findByUsername(principal.getName());
        if (user == null)
            System.out.println("USER NOT FOUND!!!!!!!!!!");
        return user;
    }

    public void authenticate(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (AuthenticationException e) {
            System.out.println("PROBLEM AUTHENTICATING USER");
        }
    }
}