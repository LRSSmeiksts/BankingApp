package com.app.BankingApplication.BankAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


@Controller
public class BankAccountController {
    @Autowired
    private BankAccountService accountService;
    
    private BankAccount currentUser;

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        // Check if username already exists
        if (accountService.isUsernameTaken(username)) {
            model.addAttribute("error", "Username already exists. Please choose a different username.");
            return "signup"; // Return signup page with error message
        }

        // Check if password and confirm password match
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match. Please enter the password again.");
            return "signup"; // Return signup page with error message
        }

        // Create a new user
        BankAccount currentUser = new BankAccount(username, password, 0.0);
        accountService.createAccount(currentUser);

        // Redirect to the main menu page
        return "redirect:/bank/" + currentUser.getId() + "/menu";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        currentUser = accountService.getUserByUsername(username);
        if(currentUser != null && currentUser.getPassword().equals(password)){

            return "redirect:/bank/" + currentUser.getId() + "/menu";
        }
        model.addAttribute("error","Invalid login information, please try again!");
        return "login";
    }
    @PostMapping("/menu")
    public String menu(@RequestParam String option){
       switch (option){
           case "viewDetails":
               return "redirect:/bank/"+currentUser.getId()+"/viewDetails";
           default:
               return "redirect:/bank/" + currentUser.getId() + "/menu";
       }
    }
    @GetMapping("/viewDetails")
    public String printBalance(Model model){
        if(currentUser!=null) {
            model.addAttribute("username", "Username: ");
            model.addAttribute("balance", "Balance");
            return "redirect:/bank/"+currentUser.getId()+"/viewDetails";
        }
        else{
            model.addAttribute("error", "There was an error!");
            return "redirect:/bank/"+currentUser.getId()+"/viewDetails";
        }


    }

}

