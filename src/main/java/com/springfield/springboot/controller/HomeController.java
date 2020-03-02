package com.springfield.springboot.controller;

import com.springfield.springboot.exception.UserNotFoundException;
import com.springfield.springboot.model.Module;
import com.springfield.springboot.model.NationalityCount;
import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.ModuleRepository;
import org.javatuples.Pair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

//import com.springfield.springboot.repository.StaffRepository;
import com.springfield.springboot.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("name")
public class HomeController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @RequestMapping(value="/home")
    public String showHomePage(ModelMap model, HttpServletRequest request) throws SQLException {
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
