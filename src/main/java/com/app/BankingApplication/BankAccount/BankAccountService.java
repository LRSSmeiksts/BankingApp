package com.app.BankingApplication.BankAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository accountRepository;


    public boolean isUsernameTaken(String username){
        BankAccount existingAccount = accountRepository.findByUsername(username);
        return existingAccount != null;
    }
    public BankAccount createAccount(BankAccount account){
        return accountRepository.save(account);
    }
    public BankAccount getUserByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
    public  void depositAmount(Long id, double amount){
        BankAccount account=accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Account not found!"));
        double currentBalance = account.getBalance();
        double newBalance = currentBalance + amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

}
