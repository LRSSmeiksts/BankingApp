public class BankAccount {
    private double balance;

    // Default ctor
    public BankAccount(){
        balance = 0;
    }

    // Paramaterized ctor
    public BankAccount(double balance){
        this.balance = balance;
    }

    // Getters
    public double getBalance() {return balance;}
    // Setters
    public void setBalance(double balance) { this.balance=balance;}
}
