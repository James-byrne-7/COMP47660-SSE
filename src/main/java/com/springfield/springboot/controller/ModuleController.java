package com.springfield.springboot.controller;

import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.ModuleRepository;
import com.springfield.springboot.model.Module;
import com.springfield.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("name")
public class ModuleController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String viewRegistrationPage(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        long userID = (long) session.getAttribute("CURRENT_USER");
        try {
            User user = userRepository.findById(userID).get();

            List<Module> listModules = new ArrayList<>(user.getModules());
            model.addAttribute("listModules", listModules);

            return "modules";
         } catch (Exception e) {
            return "redirect:login?error=true";
        }

    }



}
