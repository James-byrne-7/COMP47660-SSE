package com.springfield.springboot.validator;

import com.springfield.springboot.repository.UserRepository;
import com.springfield.springboot.model.User;
import com.springfield.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InputValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        if (!isUserValid(user.getUsername()))
            errors.rejectValue("username", "register.username.invalid");
        if (userService.findByUsername(user.getUsername()) != null)
            errors.rejectValue("username", "register.username.exists");
        if (!isStrong(user.getPassword()))
            errors.rejectValue("password", "register.password.invalid");
        if (user.getNationality() == null)
            errors.rejectValue("nationality", "register.required");
        if (user.getSex() == null)
            errors.rejectValue("sex", "register.required");
    }


    public void validatePassword(String password, Errors errors){
        if (doesNotMeet(password, PASSWORD_REQUIREMENTS))
            errors.rejectValue("password", "register.password.invalid");
    }

    private static boolean isStrong(String password){
        // Password must be 8-32 characters and contain at least 1 uppercase, lowercase and special character.
        final String PASSWORD_PATTERN = "((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[$@$!%*#?&^]).{8,32})";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
