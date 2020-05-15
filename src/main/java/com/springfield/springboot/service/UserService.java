package com.springfield.springboot.service;


import com.springfield.springboot.model.ResetToken;
import com.springfield.springboot.model.User;
import org.json.JSONObject;

import java.util.List;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    void save(ResetToken token);
    User isValidResetTokenValue(String tokenValue);
    void savePassword(String email, String password);

    JSONObject getSexBreakdown(List<User> users);
    JSONObject getNationalityBreakdown(List<User> users);
    List<User> getUsers();
}

