package com.springfield.springboot.controller;

import com.springfield.springboot.exception.UserNotFoundException;
import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class FeesController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/fees", method=RequestMethod.GET)
    public String showFeesPage(@RequestParam(value = "success", required = false) Long success, ModelMap model, HttpSession session) throws UserNotFoundException{
        Object userID = session.getAttribute("CURRENT_USER");
        if (userID == null)
            return "redirect:/login";
        String message = "";
        if (success != null) {
            if (success == 1)
                message = "Your payment has been processed";
            if (success == 0)
                message = "Invalid amount";
        }
        User user= userRepository.findById((Long)userID).orElseThrow(() -> new UserNotFoundException((Long)userID));
        String amount = "€" + user.getFees();
        model.addAttribute("successMessage", message);
        model.addAttribute("amount", amount);
        return "fees";
    }

    @PostMapping("/fees")
    public String payFees(@RequestParam Long payment, ModelMap model, HttpServletRequest request) throws Exception{
        try {
            Long userID = (Long) request.getSession().getAttribute("CURRENT_USER");
            User user = userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException(userID));
            Long feesDue = user.getFees();
            System.out.println(feesDue);
            if (payment<= feesDue) {
                user.setFees(feesDue - (payment));
                userRepository.saveAndFlush(user);
                return "redirect:/fees?success=1";
            } else {
                return "redirect:/fees?success=0";
            }
        } catch (Exception e) {
            return "redirect:/fees?success=0";
        }
    }


}
