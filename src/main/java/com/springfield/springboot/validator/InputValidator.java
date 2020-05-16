package com.springfield.springboot.validator;

import com.springfield.springboot.controller.UserController;
import com.springfield.springboot.model.User;
import com.springfield.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InputValidator implements Validator {
    final static String PASSWORD_REQUIREMENTS = "((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[$@$!%*#?&^]).{8,32})";
    final static String USERNAME_REQUIREMENTS = "^(\\w{1,32}$)";

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(InputValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        if (doesNotMeet(user.getUsername(), USERNAME_REQUIREMENTS))
            errors.rejectValue("username", "register.username.invalid");
        if (userService.findByUsername(user.getUsername()) != null)
            errors.rejectValue("username", "register.username.exists");
        if (doesNotMeet(user.getPassword(), PASSWORD_REQUIREMENTS))
            errors.rejectValue("password", "register.password.invalid");
        if (user.getNationality() == null)
            errors.rejectValue("nationality", "register.required");
        if (user.getSex() == null)
            errors.rejectValue("sex", "register.required");
        if (userService.findByEmail(user.getEmail()) != null)
            errors.rejectValue("email", "register.email.exists");
    }


    public void validatePassword(String password, Errors errors){
        logger.debug("VALIDATING PASSWORD MEETS REQUIREMENTS");
        if (doesNotMeet(password, PASSWORD_REQUIREMENTS)) {
            logger.debug("REQUIREMENTS MET");
            errors.rejectValue("password", "register.password.invalid");
        } else {
            logger.debug("INVALID PASSWORD");
        }
    }
    private boolean doesNotMeet(String input, String requirements) {
        Pattern pattern = Pattern.compile(requirements);
        Matcher matcher = pattern.matcher(input);
        return !matcher.matches();
    }
}
