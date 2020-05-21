package com.springfield.springboot.service;

import javax.servlet.http.HttpServletRequest;

//import com.springfield.springboot.service.LoginAttemptService;
import com.springfield.springboot.service.SimpleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessListener.class);


    @Override
    public void onApplicationEvent(final AuthenticationSuccessEvent e) {
        logger.info("SUCCESSFUL LOGIN");
        loginAttemptService.loginSucceeded(request.getRemoteAddr());
    }

}