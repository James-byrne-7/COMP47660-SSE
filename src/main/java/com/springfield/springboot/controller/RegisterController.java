package com.springfield.springboot.controller;

import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.UserRepository;
import com.springfield.springboot.service.SecurityService;
import com.springfield.springboot.service.UserService;
import com.springfield.springboot.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    InputValidator inputValidator;
    @Autowired
    private SecurityService securityService;

    @GetMapping(value="/register")
    public String showRegisterPage(/*@RequestParam(value = "error", required = false) String error,*/ ModelMap model, HttpSession session){
        session.invalidate();
        String loginMessage = null;
//        if(error != null) {
//            loginMessage = "Details are invalid, please try again";
//        }
//        model.addAttribute("errorMessage", loginMessage);
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("newUser") User newUser, BindingResult bindingResult, HttpSession session) {
        Object userID = session.getAttribute("CURRENT_USER");
        if (userID != null)
            session.invalidate();
        inputValidator.validate(newUser, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).toString());
            return "register";
        }
        userService.save(newUser);
        securityService.autoLogin(newUser.getUsername(), newUser.getPassword());

        return "redirect:/home";
    }



}
