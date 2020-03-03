package com.springfield.springboot.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping(value="/error")
    public String showLoginPage(ModelMap model, HttpSession session){
        return "redirect:/logout?error=true";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
