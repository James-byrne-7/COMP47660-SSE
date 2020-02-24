package com.springfield.springboot.controller;

import com.springfield.springboot.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    RegisterService service;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegisterPage(ModelMap model){
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model,
            @RequestParam String name, @RequestParam String password, @RequestParam String surname, @RequestParam String student_id,
            @RequestParam String address, @RequestParam String phone_number, @RequestParam String email_address){

        boolean areValidDetails = service.validateDetails(name, password, surname, student_id, address, phone_number, email_address);

        if (areValidDetails) {
            model.put("loginMessage", "User Created");
            return "login";
        }

        return "welcome";
    }

}
