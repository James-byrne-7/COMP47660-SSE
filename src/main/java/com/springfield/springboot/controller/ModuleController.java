package com.springfield.springboot.controller;

//import com.springfield.springboot.exception.*;
import com.springfield.springboot.model.*;
import com.springfield.springboot.model.Module;

import com.springfield.springboot.service.ModuleService;
import com.springfield.springboot.service.SecurityService;
import com.springfield.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class ModuleController {

    @Autowired
    ModuleService moduleService;
    @Autowired SecurityService securityService;
    @Autowired
    UserService userService;

    @GetMapping("/modules")
    public String viewUserModules(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        Set<Module> modules = user.getModules();
        model.addAttribute("modules", user.getModules());
        model.addAttribute("selectedModule", new Module());
        return "modules";
    }

    @GetMapping("/modules/all")
    public String viewAllModules(Model model){
        List<Module> modules = moduleService.getModules();
        model.addAttribute("modules", modules);
        model.addAttribute("selectedModule", new Module());
        return "modules";
    }

    @RequestMapping("/modules/statistics/")
    public String viewStatistics(@ModelAttribute("selectedModule") Module module, Model model , BindingResult bindingResult) {
        module = moduleService.findModule(module.getCode());
        model.addAttribute("data", moduleService.getSexBreakdown(module));
        return "modulestatistics";
    }

    @PostMapping("/modules/enrol")
    public String enrolModule(@ModelAttribute("selectedModule") Module module, Principal principal,  Model model, BindingResult bindingResult) {
        module = moduleService.findModule(module.getCode());
        if (moduleService.isOpenForEnrolment(module)) {
            moduleService.addParticipant(module, securityService.getLoggedInUser(principal));
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }
        return "redirect:/modules";
    }
    @RequestMapping("/modules/drop")
    public String dropModule(@ModelAttribute("selectedModule") Module module, Model model, Principal principal, BindingResult bindingResult) {
        User user = securityService.getLoggedInUser(principal);
        module = moduleService.findModule(module.getCode());
        moduleService.removeParticipant(module, user);
        return "redirect:/modules";
    }
    @GetMapping("/modules/edit")
    public String editModule(@ModelAttribute("selectedModule") Module module, Model model, BindingResult bindingResult) {
        model.addAttribute("module", module);
        return "editmodule";
    }
    @PostMapping("/modules/edit")
    public String updateModule(@ModelAttribute("selectedModule") Module module, Model model, BindingResult bindingResult) {
        moduleService.save(module);

        return "redirect:/modules";
    }




}
