
import java.util.Scanner;
public class BankAccountDemo {
    public static String GREEN = "\u001B[32m";
    public static String RESET = "\u001B[0m";
    public static String RED = "\u001B[31m";
    public static void main (String args[]){
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        // Scanner set up
        Scanner scanner = new Scanner(System.in);
        int optionNum;
        double amount = 0.0;
        boolean flag = true;

        BankAccount user1 = new BankAccount(5.01);
        BankAccount user2 = new BankAccount();

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
    // Will check if the input number is valid
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


}
