package com.springfield.springboot.controller;


import com.springfield.springboot.model.ResetToken;
import com.springfield.springboot.model.User;
import com.springfield.springboot.service.EmailService;
import com.springfield.springboot.service.SecurityService;
import com.springfield.springboot.service.UserService;
import com.springfield.springboot.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @RequestMapping({"/", "/welcome", "/home"})
    public String landingPage(Model model) {
        return "home";
    }

    @GetMapping(value="/register")
    public String showRegisterPage(/*@RequestParam(value = "error", required = false) String error,*/ ModelMap model, HttpSession session){
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("newUser") User newUser, BindingResult bindingResult, HttpSession session) {
        inputValidator.validate(newUser, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).toString());
            return "register";
        }
        userService.save(newUser);

        return "redirect:/home";
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

//    @RequestMapping(value="/dropout")
//    public String deleteUser(ModelMap model, HttpServletRequest request) throws UserNotFoundException{
//        Long userID = (Long) request.getSession().getAttribute("CURRENT_USER");
//        User user = userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException(userID));
//        userRepository.delete(user);
//        return "redirect:logout";
//    }

    @GetMapping("/forgotpassword")
    public String forgotPassword(Model model) {
        model.addAttribute("email", new String());

        return "forgotpassword";
    }

    @PostMapping("/forgotpassword")
    public String forgotUserPassword(@ModelAttribute("email") String email, Model model) {

        User existingUser = userService.findByEmail(email);

        if (existingUser != null) {
            // Create token
            ResetToken token = new ResetToken(existingUser);
            userService.save(token);
            emailService.sendResetToken(existingUser.getEmail(), token);
            model.addAttribute("loginMessage", "Password Reset Email Sent");
            return "loglin";
        } else {
            System.out.println("USER NOT FOUND");
        }
        return "forgotpassword";
    }

    @GetMapping(value = "/confirm-reset")
    public String validateResetToken(@RequestParam("token") String tokenValue, Model model) {
        User user = userService.isValidResetTokenValue(tokenValue);
        if (user != null) {
            user.setPassword("");
            model.addAttribute("user", user);
            //model.addAttribute("error", "Reset Success");
            return "resetpassword";
        } else {
            model.addAttribute("loginMessage", "The link is invalid or broken!");
            return "login";

        }
    }

    @PostMapping("/confirm-reset")
    public String resetUserPassword(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        inputValidator.validatePassword(user.getPassword(), bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).toString());
            return "resetpassword";
        }
        userService.savePassword(user.getEmail(), user.getPassword());
        model.addAttribute("loginMessage", "Password Reset Successful");
        return "login";
    }
}
