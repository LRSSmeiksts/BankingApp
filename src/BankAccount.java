import java.lang.Math;


public class BankAccount {

    public static String GREEN = "\u001B[32m";
    public static String RESET = "\u001B[0m";
    public static String RED = "\u001B[31m";
    private double balance;

    // Default ctor
    public BankAccount(){
        balance = 0;
    }

    // Parameterized ctor
    public BankAccount(double balance){
        this.balance = balance;
    }

    // Getters
    public double getBalance() {return balance;}
    // Setters
    public void setBalance(double balance) { this.balance = balance;}

    // Will round number to 2 decimal places for printing purposes
    public double roundToTwo(double number){
        return Math.round(number * 100.0) / 100.0;
    }

    // This method will print the account balance
    public void printBalance(){
        System.out.println("Your balance is: "+ GREEN +roundToTwo(balance)+ RESET);
        System.out.println();
    }
    // This method will deposit specified amount of money to the account
    public void deposit(double amount){
        if(amount >0) {
            balance += amount;
            System.out.println("Amount of " + GREEN + amount + " EUR" + RESET + " has been added to the balance.");
            System.out.println();
        }
        else{
            System.out.println(RED +"Input amount can't be negative!"+ RESET);
            System.out.println();
        }
    }
    // This method will

    public void withdraw(double amount){
        //check if there is enough balance
        if(balance >= amount){
            balance = balance - amount;
            System.out.println("Amount of "+ GREEN +amount + " EUR"+RESET+ " has been withdrawn from your account!");
            System.out.println();
        }
        else{
            System.out.println(RED +"Not enough balance on the account, please try different amount!"+ RESET);
            System.out.println();
        }
    }
    public void transfer(double amount, BankAccount user1, BankAccount user2){
        //check if user1 has enough money
        if(user1.balance >= amount){
            user1.balance -= amount;
            user2.balance += amount;
            System.out.println("Amount of "+ GREEN +amount + " EUR "+RESET+"has been transferred to the other account!");
        }
        else{
            System.out.println(RED +"Not enough balance on the account, please try different amount!"+ RESET);
        }
    }
}

