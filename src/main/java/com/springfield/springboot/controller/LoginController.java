package com.springfield.springboot.controller;

import com.springfield.springboot.exception.UserNotFoundException;
import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.springfield.springboot.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @GetMapping(value="/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "registered", required = false) String registered,
                                @RequestParam(value = "invalid", required = false) String invalid,
                                @RequestParam(value = "logout", required = false) String logout, ModelMap model,
                                HttpSession session){
        Long userID = (Long) session.getAttribute("CURRENT_USER");
        if (userID != null)
            return "redirect:/home";
        String loginMessage = null;
        if(invalid != null) {
            loginMessage = "Username or Password is incorrect !!";
        }
        if(error != null) {
            loginMessage = "An Error has occurred";
        }
        if(registered != null) {
            loginMessage = "You've been successfully registered !!";
        }
        if(logout != null) {
            loginMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("loginMessage", loginMessage);
        return "login";
    }

//    @PostMapping("/login")
//    public String loginUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
//        request.getSession().invalidate();
//        try {
//            Long userID = userRepository.findStudentIDByUsername(username);
//            User user = userRepository.findById(userID).get();
//            if (user.getPassword().equals(password)) {
//                request.getSession().setAttribute("CURRENT_USER", userID);
//                request.getSession().setAttribute("username", user.getUsername());
//                if (user.getRoles().contains("staff"))
//                    request.getSession().setAttribute("staff", "true");
//                return "redirect:/home";
//            } else {
//                return "redirect:/login?invalid=true";
//            }
//        } catch (Exception e) {
//            return "redirect:/login?invalid=true";
//        }
//    }

    @RequestMapping(value="/logout")
    public String endUserSession(@RequestParam(value = "error", required = false) String error, ModelMap model, HttpServletRequest request) {
        request.getSession().invalidate();
        if (error != null)
            return "redirect:login?error=true";
        return "redirect:login?logout=true";
    }
    @RequestMapping(value="/dropout")
    public String deleteUser(ModelMap model, HttpServletRequest request) throws UserNotFoundException{
        Long userID = (Long) request.getSession().getAttribute("CURRENT_USER");
        User user = userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException(userID));
        userRepository.delete(user);
        return "redirect:logout";
    }
}
