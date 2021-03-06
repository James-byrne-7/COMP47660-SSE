package com.springfield.springboot.service;

import com.springfield.springboot.controller.UserController;
import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Service
public class SimpleUserDetailsService implements UserDetailsService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LoginAttemptService loginAttemptService;
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSecurityService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        String ip = getClientIP();
        logger.info(String.format("LOGIN ATTEMPT FROM: %s", ip));
        if (loginAttemptService.isBlocked(ip)) {
            logger.info("Login Source IP Blocked");
            throw new RuntimeException("Login Source IP Blocked");
        }
        logger.debug("RETRIEVING USER DETAILS BY USERNAME");
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        logger.debug("GRANTING USER AUTHORITIES");
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//        for (Role role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

        private final String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}