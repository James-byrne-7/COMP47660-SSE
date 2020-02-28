package com.springfield.springboot.controller;

import com.springfield.springboot.exception.UserNotFoundException;
import com.springfield.springboot.model.Module;
import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

//import com.springfield.springboot.repository.StaffRepository;
import com.springfield.springboot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @RequestMapping(value={"/","/login"}, method=RequestMethod.GET)
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "registered", required = false) String registered,
                                @RequestParam(value = "logout", required = false) String logout, ModelMap model){
        String loginMessage = null;
        if(error != null) {
            loginMessage = "Username or Password is incorrect !!";
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

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String username, @RequestParam String password)
    {
        try {
            long userID = Long.parseLong(userRepository.findStudentIDByUsername(username));
            User user = userRepository.findById(userID).get();
            if (user.getPassword().equals(password)) {
                return "home";
            } else {
                return "redirect:/login?error=true";
            }
        } catch (Exception e) {
            return "redirect:/login?error=true";
        }

    }

    @RequestMapping(value="/logout")
    public String endUserSession(ModelMap model) {
        model.addAttribute("userID", "");
        return "redirect:login?logout=true";
    }

}
