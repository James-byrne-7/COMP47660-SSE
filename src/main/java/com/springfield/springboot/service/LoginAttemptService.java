package com.springfield.springboot.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 3;
    private final int LOCKOUT_TIME = 20;
    private Map<String, Integer> attempts = new HashMap<>();
    private Map<String, Date> locks = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(SimpleSecurityService.class);

    public void loginSucceeded(String ip) {
        attempts.put(ip, 0);
    }

    public void loginFailed(String ip) {
        if (attempts.get(ip) == null)
            attempts.put(ip, 1);
        else attempts.put(ip, attempts.get(ip)+1);
        logger.info("Number of Failed login Attempts: " + attempts.get(ip));
        if (attempts.get(ip) >= MAX_ATTEMPT)
            locks.put(ip, new Date());
    }

    public boolean isBlocked(final String ip) {
        if (attempts.get(ip) == null || attempts.get(ip) < 3)
            return false;
        Date now = new Date();
        long milliseconds1 = locks.get(ip).getTime();
        long milliseconds2 = now.getTime();
        long diffMinutes = (milliseconds2 - milliseconds1)/ (60 * 1000);
        boolean isBlocked = diffMinutes < LOCKOUT_TIME;
        if(isBlocked) logger.debug("IP BLOCKED for another " + (LOCKOUT_TIME-diffMinutes));

        return isBlocked;

    }
}
