package com.springfield.springboot.controller;

import com.springfield.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

//import com.springfield.springboot.repository.StaffRepository;
import com.springfield.springboot.repository.UserRepository;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    UserRepository userRepository;
//    @Autowired StaffRepository staffRepository;

    @RequestMapping(value={"/","/login"}, method=RequestMethod.GET)
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "registered", required = false) String registered,
                                @RequestParam(value = "logout", required = false) String logout, ModelMap model){
        String loginMessage = null;
        if(error != null) {
            loginMessage = "Username or Password is incorrect !!";
        }
        if(error != null) {
            loginMessage = "You've been successfully registered !!";
        }
        if(logout != null) {
            loginMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("loginMessage", loginMessage);
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String username, @RequestParam String password){
//        long userID = userRepository.findStudentIDByUsername(username);
        String studentPassword = userRepository.lookupStudentPassword(username);
        if (password.equals(studentPassword)) {
            model.addAttribute("username", username);
            model.addAttribute("privelegeLevel", "student");
            return "home";
//        }
//        String staffPassword = staffRepository.lookupStaffPassword(username);
//        if (password.equals(staffPassword)) {
//            model.addAttribute("username", username);
//            model.addAttribute("privilegeLevel", "staff");
//            return "home";
        } else {
            return "redirect:/login?error=true";
        }
    }

}
