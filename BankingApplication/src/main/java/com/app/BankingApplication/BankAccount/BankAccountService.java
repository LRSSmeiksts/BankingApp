package com.app.BankingApplication.BankAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
