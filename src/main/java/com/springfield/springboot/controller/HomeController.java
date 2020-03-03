package com.springfield.springboot.controller;

import com.springfield.springboot.model.NationalityCount;
import com.springfield.springboot.repository.ModuleRepository;
import com.springfield.springboot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import org.json.JSONObject;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @GetMapping(value="/home")
    public String showHomePage(ModelMap model, HttpServletRequest request) throws SQLException {
        Object userID = request.getSession().getAttribute("CURRENT_USER");
        if (userID == null)
            return "redirect:logout?error=true";
        JSONObject sexData = new JSONObject();
        sexData.put("female",userRepository.countUsersBySex('F'));
        sexData.put("male", userRepository.countUsersBySex('M'));
        model.addAttribute("sexData", sexData);

        JSONObject nationalityData = new JSONObject();
        List<NationalityCount> rs = userRepository.findNationalityCounts();
        for (NationalityCount n : rs){
                nationalityData.put(n.nationality, n.count);
            System.out.println("Loop");
        }
        model.addAttribute("nationalityData", nationalityData);
        return "home";
    }




}
