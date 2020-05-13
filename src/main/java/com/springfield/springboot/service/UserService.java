package com.springfield.springboot.service;


import com.springfield.springboot.model.ResetToken;
import com.springfield.springboot.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    void save(ResetToken token);
    User isValidResetTokenValue(String tokenValue);
    void savePassword(String email, String password);
}

