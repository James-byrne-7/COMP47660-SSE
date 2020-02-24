package com.springfield.springboot.controller;

import com.springfield.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springfield.springboot.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(value={"/","/login"}, method=RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String username, @RequestParam String password){

        String StudentPassword = studentRepository.lookupStudentPassword(username);
        if (password.equals(StudentPassword)) {
            model.addAttribute("username", username);
            return "homepage";
        } else {
            return "login";
        }
    }

}
