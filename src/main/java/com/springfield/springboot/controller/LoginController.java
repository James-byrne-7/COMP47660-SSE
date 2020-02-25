package com.springfield.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springfield.springboot.repository.StaffRepository;
import com.springfield.springboot.repository.StudentRepository;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired StudentRepository studentRepository;
    @Autowired StaffRepository staffRepository;

    @RequestMapping(value={"/","/login"}, method=RequestMethod.GET)
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout, ModelMap model){
        String errorMessage = null;
        if(error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String username, @RequestParam String password){

        String studentPassword = studentRepository.lookupStudentPassword(username);
        if (password.equals(studentPassword)) {
            model.addAttribute("username", username);
            model.addAttribute("privelegeLevel", "student");
            return "homepage";
        }
        String staffPassword = staffRepository.lookupStaffPassword(username);
        if (password.equals(staffPassword)) {
            model.addAttribute("username", username);
            model.addAttribute("privilegeLevel", "staff");
            return "homepage";
        } else {
            return "redirect:/login?error=true";
        }
    }

//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout=true";
//    }

}
