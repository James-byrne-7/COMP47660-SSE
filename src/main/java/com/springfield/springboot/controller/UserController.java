package com.springfield.springboot.controller;


import com.springfield.springboot.model.User;
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
        securityService.autoLogin(newUser.getUsername(), newUser.getPassword());

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

}
