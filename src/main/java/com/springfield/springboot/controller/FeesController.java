package com.springfield.springboot.controller;

import com.springfield.springboot.model.Transaction;
import com.springfield.springboot.model.User;
import com.springfield.springboot.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class FeesController {
    @Autowired
    SecurityService securityService;

    @GetMapping(value="/fees")
    public String showFeesPage(ModelMap model, Principal principal) {
        User user = securityService.getLoggedInUser(principal);
        model.addAttribute("transaction", new Transaction(user.getFeeBalance()));
        return "fees";
    }

    @PostMapping("/fees")
    public String payFees(@ModelAttribute("transaction") Transaction transaction, ModelMap model, Principal principal){
        if (transaction.getAmount() <= transaction.getInitialBalance()) {
            User user = securityService.getLoggedInUser(principal);
            user.getTransactions().add(transaction);
        }
        return "fees";
    }


}
