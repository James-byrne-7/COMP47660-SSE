package com.springfield.springboot.service;

import com.springfield.springboot.controller.UserController;
import com.springfield.springboot.model.ResetToken;
import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.ResetTokenRepository;
import com.springfield.springboot.repository.UserRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimpleUserService implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ResetTokenRepository resetTokenRepository;

    private static final Logger logger = LoggerFactory.getLogger(SimpleUserService.class);

    @Override
    public void save(User user) {
        logger.debug("ENCRYPTING PASSWORD");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setRoles(new HashSet<>(roleRepository.findAll()));
        logger.debug(String.format("SAVING USER (%s) IN DATABASE", user.getUsername()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        logger.debug(String.format("RETRIEVING USER FROM DATABASE WITH USERNAME: %S", username));
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        logger.debug(String.format("RETRIEVING USER FROM DATABASE WITH EMAIL: %s", email));
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(ResetToken token) {
        logger.debug("SAVING RESET TOKEN");
        resetTokenRepository.save(token);
    }

    @Override
    public User isValidResetTokenValue(String tokenValue) {
        logger.debug("LOOKING UP RESET TOKEN IN DATABASE");
        ResetToken token = resetTokenRepository.findByValue(tokenValue);
        if (token != null) {
            if(!isTokenExpired(token.getCreationDate()) && !token.isUsed()) {
                logger.debug("VALID TOKEN FOUND");
                logger.debug("RETRIEVING USER");
                User user = userRepository.findByEmail(token.getUser().getEmail());
                logger.debug("SETTING TOKEN TO USED");
                token.setUsed(true);
                resetTokenRepository.save(token);
                return user;
            }
        } else { logger.debug("NOT FOUND"); }
            return null;
    }

    @Override
    public void savePassword(String email, String password) {
        logger.debug("UPDATING USER PASSWORD IN DATABASE");
        logger.debug("LOOKING UP USER");
        User user = userRepository.findByEmail(email);
        logger.debug("ENCRYPTING PASSWORD");
        user.setPassword(bCryptPasswordEncoder.encode(password));
        logger.debug("UPDATING DATABASE");
        userRepository.save(user);
    }

    private boolean isTokenExpired(Date createdDate){
        Date now = new Date();
        long milliseconds1 = createdDate.getTime();
        long milliseconds2 = now.getTime();
        long diffMinutes = (milliseconds2 - milliseconds1)/ (60 * 1000);
        boolean isExpired = diffMinutes > 20;
        if(isExpired) logger.debug("TOKEN EXPIRED");

        return isExpired;
    }
    @Override
    public JSONObject getSexBreakdown(List<User> users) {
        int M = 0, F = 0, U = 0;
        for (User u : users)
            if (u.getSex().equals('M')) M++;
            else if (u.getSex().equals('F')) F++;
            else U++;
        JSONObject data = new JSONObject();
        data.put("female", F);
        data.put("male", M);

        return data;
    }
    public JSONObject getNationalityBreakdown(List<User> users) {
        JSONObject nationalityData = new JSONObject();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (User user : users) {
            String nationality = user.getNationality();
            Integer count = map.get(nationality);
            map.put(nationality, (count == null) ? 1 : count + 1);
        }
        for (String nationality : map.keySet())
            nationalityData.put(nationality, map.get(nationality));
        return nationalityData;
    }
    public List<User> getUsers() { return userRepository.findAll(); }
}
