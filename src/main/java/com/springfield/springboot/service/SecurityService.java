package com.springfield.springboot.service;

import com.springfield.springboot.model.User;

import java.security.Principal;

public interface SecurityService {
//    String getLoggedInUsername();

    User getLoggedInUser(Principal principal);
}