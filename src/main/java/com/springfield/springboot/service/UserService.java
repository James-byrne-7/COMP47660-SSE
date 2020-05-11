package com.springfield.springboot.service;

import com.springfield.springboot.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}

