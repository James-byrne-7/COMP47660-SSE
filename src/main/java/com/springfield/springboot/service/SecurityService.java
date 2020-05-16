package com.springfield.springboot.service;

import com.springfield.springboot.model.User;

import java.security.Principal;

public interface SecurityService {
    User getLoggedInUser(Principal principal);
}