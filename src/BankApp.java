
import java.io.*;
import java.util.*;
public class BankApp {
    private static final String FILE_NAME = "bank_accounts.txt";
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
    // Function to retrieve data from file to a list
    private static List<BankAccount> readUsersFromFile(){
        List<BankAccount> users = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String userName = parts[1];
                    double balance = Double.parseDouble(parts[0]);
                    users.add(new BankAccount(balance, userName));
                }
            }
        }
        catch(FileNotFoundException e){
            System.err.println("File not found: "+ e.getMessage());
        }
        return users;
    }

    private static BankAccount getUserData(String userName) {
        BankAccount user = null;
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[1].equals(userName)) {
                    user = new BankAccount(Double.parseDouble(parts[1]), parts[0]);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return new BankAccount();
        }
        return user;
    }

    public static void main (String args[]){
        int optionNum;
        String currentUserStr;
        boolean flag = true;
        BankAccount currentUser;
        BankAccount recipientAccount = null;

        List<BankAccount> users = readUsersFromFile();

        while(true) {
                try {
                    System.out.println("Please input your username and press Enter:  ");
                    currentUserStr = scanner.nextLine();
                    currentUser = new BankAccount(currentUserStr);
                    users.add(currentUser);
                    break;
                }
                catch(Exception e){
                        System.out.println(RED + "Input correct operation number!" + RESET);
                        System.out.println();
                        scanner.next();
                    }
        }
        System.out.println("Welcome! You're logged in as " + currentUserStr);
        while(flag){
                try {
                    System.out.println("Press corresponding keys to do an operation:");
                    System.out.println(" (1) to view your current bank balance.");
                    System.out.println(" (2) to withdraw amount from your bank account.");
                    System.out.println(" (3) to deposit amount to your bank account");
                    System.out.println(" (4) to transfer amount to another account");
                    System.out.println(" (5) to exit.");
                    System.out.println();
                    optionNum = scanner.nextInt();
                    double amountValue;
                    switch (optionNum) {
                        case 1:
                            currentUser.printBalance();
                            break;
                        case 2:
                            System.out.println("Write amount x.xx to be withdrawn: ");
                            amountValue = scanner.nextDouble();
                            currentUser.withdraw(amountValue);
                            break;
                        case 3:
                            System.out.println("Write amount x.xx to be deposited: ");
                            amountValue = scanner.nextDouble();
                            currentUser.deposit(amountValue);
                            break;
                        case 4:
                            boolean tempF = false;
                            System.out.println("Write the username of the recipient account: ");
                            String recipientUser = scanner.nextLine(); // clear buffer
                            recipientUser = scanner.nextLine();

                            for(BankAccount u: users){
                                if(recipientUser.equals(u.getUsername())) {
                                    tempF= true;
                                    recipientAccount = new BankAccount(u.getBalance(),u.getUsername());
                                }
                            }
                            if(!tempF){
                                System.out.println("No user such user doesn't exist, please try another one!");
                                break;
                            }
                            else{
                                System.out.println("Write amount x.xx to be transferred to the other account: ");
                                amountValue = scanner.nextDouble();
                                currentUser.transfer(amountValue, currentUser, recipientAccount);
                            }

                            break;
                        case 5:
                            flag = false;
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(RED + "Input correct operation number!" + RESET);
                    System.err.println(e.getMessage());
                    scanner.next();
                }
                for(BankAccount u : users){
                    saveUser(u);
                }

        }
    }
}
