package com.app.BankingApplication.BankAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


@Controller
public class BankAccountController {
    @Autowired
    private BankAccountService accountService;

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
        BankAccount newUser = new BankAccount(username, password, 0.0);
        accountService.createAccount(newUser);

        // Redirect to the main menu page
        return "redirect:/bank/" + newUser.getId() + "/menu";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        BankAccount user = accountService.getUserByUsername(username);
        if(user != null && user.getPassword().equals(password)){

            return "redirect:/bank/" + user.getId() + "/menu";
        }
        model.addAttribute("error","Invalid login information, please try again!");
        return "login";
    }

}
