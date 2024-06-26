package com.app.BankingApplication.BankAccount;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/bank/{id}/menu")
    public String menu(){
     return "menu";
    }
    @GetMapping("/bank/{id}/deposit")
    public String deposit(){
        return "deposit";
    }

}
