
import java.io.*;
import java.util.*;
public class BankApp {
    private static final String FILE_NAME = "bank_accounts.txt";
    private static final List<BankAccount> users = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    // Function to saveUser data in .txt file
    private static void saveUser(BankAccount user) {
        List<BankAccount> updatedUsers = new ArrayList<>();
        try {
            // Check if the file exists, create it if not
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String existingUsername = parts[1];
                        double existingBalance = Double.parseDouble(parts[0]);
                        if (existingUsername.equals(user.getUsername())) {
                            // If user exists, replace the data
                            updatedUsers.add(new BankAccount(user.getBalance(), user.getUsername()));
                        } else {
                            updatedUsers.add(new BankAccount(existingBalance, existingUsername));
                        }
                    }
                }
            }

            // Add new user, if not on the list
            boolean userFound = false;
            for (BankAccount u : updatedUsers) {
                if (u.getUsername().equals(user.getUsername())) {
                    userFound = true;
                    break;
                }
            }
            if (!userFound) {
                updatedUsers.add(user);
            }
            // Write updated users to file
            try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
                for (BankAccount u : updatedUsers) {
                    writer.println(u.getBalance() + "," + u.getUsername());
                }
            }
        }
        catch(Exception e){
            System.err.println("Error saving user data: " + e.getMessage());
        }
    }
    public static double inputIsValid(double number){
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
    public static void main (String args[]){
        int optionNum;
        double amount = 0.0;
        boolean flag = true;

        BankAccount user1 = new BankAccount(5.01,"user1");
        BankAccount user2 = new BankAccount();

        saveUser(user1);

        while(flag){
            System.out.println("Press corresponding keys to do an operation:");
            System.out.println(" (1) to view your current bank balance.");
            System.out.println(" (2) to withdraw amount from your bank account.");
            System.out.println(" (3) to deposit amount to your bank account");
            System.out.println(" (4) to transfer amount to another account");
            System.out.println(" (5) to exit.");
            System.out.println();
            try{
                optionNum = scanner.nextInt();
                double amountValue;
                switch(optionNum){
                    case 1:
                        user1.printBalance();
                        break;
                    case 2:
                        System.out.println("Write amount x.xx to be withdrawn: ");
                        amountValue = scanner.nextDouble();
                        amount = inputIsValid(amountValue);
                        user1.withdraw(amount);
                        break;
                    case 3:
                        System.out.println("Write amount x.xx to be deposited: ");
                        amountValue = scanner.nextDouble();
                        amount = inputIsValid(amountValue);
                        user1.deposit(amount);
                        break;
                    case 4:
                        System.out.println("Write amount x.xx to be transferred to the other account: ");
                        amountValue = scanner.nextDouble();
                        amount = inputIsValid(amountValue);
                        user1.transfer(amount, user1, user2);
                        break;
                    case 5:
                        flag = false;
                        break;
                    default:
                        System.out.println(RED + "Input correct operation number!"+ RESET);
                        break;
                }
            }
            catch(Exception e){
                System.out.println(RED + "Input correct operation number!"+ RESET);
                System.out.println();
                scanner.next();
            }
        }
    }
}
