package com.springfield.springboot.controller;

import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.ModuleRepository;
import com.springfield.springboot.model.Module;
import com.springfield.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String viewModulePage(Model model) {
        return "modules";
    }



}
