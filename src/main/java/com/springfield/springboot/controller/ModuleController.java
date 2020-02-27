package com.springfield.springboot.controller;

import com.springfield.springboot.repository.ModuleRepository;
import com.springfield.springboot.model.Module;
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

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String viewModulePage(Model model) {
        List<Module> listModules = moduleRepository.findAll();
        model.addAttribute("listModules", listModules);
        return "modules";
    }



}
