package com.app.BankingApplication.BankAccount;

import jakarta.persistence.*;

@Entity
@Table
public class BankAccount {
    @Id
    @SequenceGenerator(
            name ="bankaccount_sequence",
            sequenceName = "bankaccount_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bankaccount_sequence"
    )
    private Long id;
    private String username;
    private String password;
    private Double balance;

    public BankAccount() {
    }

    public BankAccount(String username, String password, Double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public BankAccount(Long id, String username, String password, Double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
