package com.springfield.springboot.controller;

import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegisterPage(@RequestParam(value = "error", required = false) String error, ModelMap model, HttpSession session){
        session.invalidate();
        String loginMessage = null;
        if(error != null) {
            loginMessage = "Details are invalid, please try again";
        }
        model.addAttribute("errorMessage", loginMessage);
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String registerUser(ModelMap model,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "username", required = true) String username,
                                  @RequestParam(value = "password", required = true) String password,
                                  @RequestParam(value = "surname", required = false) String surname,
                                  @RequestParam(value = "sex", required = true) char sex,
                                  @RequestParam(value = "nationality", required = true) String nationality,
                                  @RequestParam(value = "student_id", required = false) String student_id,
                                  @RequestParam(value = "address", required = false) String address,
                                  @RequestParam(value = "phone_number", required = false) String phone_number,
                                  @RequestParam(value = "email_address", required = false) String email_address,
                                  HttpSession session){
        try {
            Object userID = session.getAttribute("CURRENT_USER");
            if (userID != null)
                session.invalidate();
            User user = new User(username, password, sex, nationality);
            userRepository.saveAndFlush(user);

            return "redirect:/login?registered=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:register?error=true";
        }
    }

}
