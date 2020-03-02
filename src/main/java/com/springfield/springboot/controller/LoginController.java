package com.springfield.springboot.controller;

import com.springfield.springboot.exception.UserNotFoundException;
import com.springfield.springboot.model.Module;
import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.ModuleRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

//import com.springfield.springboot.repository.StaffRepository;
import com.springfield.springboot.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
                                @RequestParam(value = "registered", required = false) String invalid,
                                @RequestParam(value = "logout", required = false) String logout, ModelMap model,
                                HttpSession session){
        Object userID = session.getAttribute("CURRENT_USER");
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

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        request.getSession().invalidate();
        try {
            long userID = Long.parseLong(userRepository.findStudentIDByUsername(username));
            User user = userRepository.findById(userID).get();
            if (user.getPassword().equals(password)) {
                request.getSession().setAttribute("CURRENT_USER", userID);
                request.getSession().setAttribute("username", user.getUsername());
                return "redirect:/home";
            } else {
                return "redirect:/login?invalid=true";
            }
        } catch (Exception e) {
            return "redirect:/login?invalid=true";
        }
    }

    @RequestMapping(value="/logout")
    public String endUserSession(ModelMap model, HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:login?logout=true";
    }



}
