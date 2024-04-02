import java.lang.Math;
public class BankAccount {
    // For formatting purposes
    public static String GREEN = "\u001B[32m";
    public static String RESET = "\u001B[0m";
    public static String RED = "\u001B[31m";
    private double balance;
    private String username;

    // Default ctor
    public BankAccount(){
        balance = 0.0;
        username = "Default";
    }

    // Parameterized ctors
    public BankAccount(double balance, String username){
        this.balance = balance;
        this.username = username;
    }
    public BankAccount(String username){
        this.balance = 0.0;
        this.username = username;
    }

    // Getters
    public double getBalance() {return balance;}
    public String getUsername(){return username;}
    // Setters
    public void setBalance(double balance) { this.balance = balance;}
    public void setUsername(String username){ this.username = username;}

    // Check is the input is valid
    public  double inputIsValid(double number){
        if(number>0){
            String numText = Double.toString(number);
            int integerPlaces = numText.indexOf('.');
            int decimalPlaces = numText.length() - integerPlaces -1;
            if(decimalPlaces == 2){
                return number;
            }
            else{
                System.out.println(RED +"Please input a valid amount x.xx!"+ RESET);
                System.out.println();
                return 0;
            }
        }
        else {
            System.out.println(RED + "Input amount can't be negative!" + RESET);
            System.out.println();
            return 0;
        }
    }

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
        double validatedNum = inputIsValid(amount);
        balance += validatedNum;
        System.out.println("Amount of " + GREEN + validatedNum + " EUR" + RESET + " has been added to the balance.");
        System.out.println();
    }

    // This method withdraws specific amount from the account
    public void withdraw(double amount){
        //check if there is enough balance
        double validatedNum = inputIsValid(amount);
        if(balance >= validatedNum){
            balance = balance - validatedNum;
            System.out.println("Amount of "+ GREEN + validatedNum + " EUR"+RESET+ " has been withdrawn from your account!");
            System.out.println();
        }
        else{
            System.out.println(RED +"Not enough balance on the account, please try different amount!"+ RESET);
            System.out.println();
        }
    }
    public void transfer(double amount, BankAccount sender, BankAccount receiver){
        //check if user1 has enough money
        double validatedNum = inputIsValid(amount);
        if(sender.balance >= validatedNum){
            sender.balance -= validatedNum;
            receiver.balance += validatedNum;
            System.out.println("Amount of "+ GREEN + validatedNum + " EUR "+RESET+"has been transferred to "+ receiver.getUsername()+ " account!");
        }
        else{
            System.out.println(RED +"Not enough balance on the account, please try different amount!"+ RESET);
        }
    }
}




