package com.springfield.springboot.controller;

import com.springfield.springboot.exception.ModuleNotFoundException;
import com.springfield.springboot.exception.UserNotFoundException;
import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.InvolvementRepository;
import com.springfield.springboot.repository.ModuleRepository;
import com.springfield.springboot.model.Module;
import com.springfield.springboot.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("name")
public class ModuleController {

    @Autowired UserRepository userRepository;
    @Autowired ModuleRepository moduleRepository;
    @Autowired InvolvementRepository involvementRepository;

    @GetMapping("/modules")
    public String viewRegistrationPage(Model model, HttpSession session)
            throws UserNotFoundException, ModuleNotFoundException {
        @SuppressWarnings("unchecked")
        long userID = (long) session.getAttribute("CURRENT_USER");
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));
        model.addAttribute("user", user);

        List<Long> module_ids = involvementRepository.findModuleByUserId(user.getId());
        List<Module> listModules = new ArrayList<Module>();
        for(Long module_id: module_ids){
            Module module = moduleRepository.findById(module_id)
                    .orElseThrow(() -> new ModuleNotFoundException(module_id));
            listModules.add(module);
        }

        model.addAttribute("listModules", listModules);
        return "modules";
    }

//    @RequestMapping("/statistics/{moduleID}")
//    public String viewStatistics(@PathVariable(value = "moduleID") long moduleID, Model model) {
//        JSONObject data = new JSONObject();
//        data.put("female",staffRepository.countFemaleStaff() );
//        data.put("male", staffRepository.countMaleStaff() );
//        model.addAttribute("data", data);
//        model.addAttribute("username", username);
//        return "viewschoolstatistics";
//    }



}
