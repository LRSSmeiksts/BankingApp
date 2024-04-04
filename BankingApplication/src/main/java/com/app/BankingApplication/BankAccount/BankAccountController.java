package com.app.BankingApplication.BankAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


@RestController
@RequestMapping("/bank")
public class BankAccountController {
    @Autowired
    private BankAccountService accountService;
//
//    @PostMapping("/signup")
//    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String confirmPassword, Model model);{
//        // Check if account exists
//        if(){
//
//        }
//    }
    @PostMapping("/login")
    public String login() {
        // Perform login logic here
        return "login success"; // Just a placeholder for now
    }

}
