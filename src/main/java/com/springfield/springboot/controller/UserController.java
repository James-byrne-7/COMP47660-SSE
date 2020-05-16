package com.springfield.springboot.controller;

import com.springfield.springboot.model.ResetToken;
import com.springfield.springboot.model.User;
import com.springfield.springboot.service.EmailService;
import com.springfield.springboot.service.SecurityService;
import com.springfield.springboot.service.SimpleEmailService;
import com.springfield.springboot.service.UserService;
import com.springfield.springboot.validator.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private InputValidator inputValidator;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping({"/", "/welcome", "/home"})
    public String landingPage(Model model) {
        logger.debug("GATHERING USER STATS");
        model.addAttribute("sexData", userService.getSexBreakdown(userService.getUsers()));
        model.addAttribute("nationalityData", userService.getNationalityBreakdown(userService.getUsers()));
        return "home";
    }

    @GetMapping(value="/register")
    public String showRegisterPage(ModelMap model){
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("newUser") User newUser, BindingResult bindingResult) {
        logger.debug("VALIDATING USER REGISTRATION DATA");
        inputValidator.validate(newUser, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.debug("ERRORS FOUND:");
            for (ObjectError error : bindingResult.getAllErrors())
                logger.debug(String.format("ERROR: %s", error.toString()));
            return "register";
        } else {
            logger.debug("DATA VALIDATED");
            userService.save(newUser);
            return "redirect:/home";
        }
    }

    @GetMapping(value="/login")
    public String showLoginPage(Model model, String error, String logout){
        if(error != null) {
            model.addAttribute("loginMessage","An Error has occurred");
        }
        if(logout != null) {
            model.addAttribute("loginMessage","You have been successfully logged out !!");
        }
        return "login";
    }

    @GetMapping("/forgotpassword")
    public String forgotPassword(Model model) {
        model.addAttribute("email", new String());
        return "forgotpassword";
    }

    @PostMapping("/forgotpassword")
    public String forgotUserPassword(@ModelAttribute("email") String email, Model model) {
        logger.debug("SEARCHING FOR USER");
        User existingUser = userService.findByEmail(email);
        if (existingUser != null) {
            logger.debug("GENERATING RESET TOKEN FOR USER : %s", existingUser.getUsername());
            ResetToken token = new ResetToken(existingUser);
            userService.save(token);
            emailService.sendResetToken(existingUser.getEmail(), token);
        } else { logger.debug("USER DOES NOT EXIST"); }
        return "forgotpassword";
    }

    @GetMapping(value = "/confirm-reset")
    public String validateResetToken(@RequestParam("token") String tokenValue, Model model) {
        logger.debug("VALIDATING RESET TOKEN");
        User user = userService.isValidResetTokenValue(tokenValue);
        if (user != null) {
            user.setPassword("");
            model.addAttribute("user", user);
            return "resetpassword";
        } else {
            model.addAttribute("loginMessage", "The link is invalid or broken!");
            return "login";
        }
    }

    @PostMapping("/confirm-reset")
    public String resetUserPassword(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        logger.debug("RESETTING USER PASSWORD");
        inputValidator.validatePassword(user.getPassword(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "resetpassword";
        }
        userService.savePassword(user.getEmail(), user.getPassword());
        model.addAttribute("loginMessage", "Password Reset Successful");
        return "login";
    }
}
