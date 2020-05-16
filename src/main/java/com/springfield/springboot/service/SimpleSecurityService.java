package com.springfield.springboot.service;

import com.springfield.springboot.controller.UserController;
import com.springfield.springboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(SimpleSecurityService.class);

    @Override
    public User getLoggedInUser(Principal principal){
        User user = userService.findByUsername(principal.getName());
        if (user == null)
            logger.error("UNABLE TO RETRIEVE LOGGED IN USER");
        return user;
    }

    public void authenticate(String username, String password) {
        logger.info(String.format("AUTHENTICATING USER: %s", username));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        logger.info("GENERATING AUTHENTICATION TOKEN");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        logger.info("AUTHENTICATING TOKEN");
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }
}