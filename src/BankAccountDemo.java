
import java.sql.SQLOutput;
import java.util.Scanner;
public class BankAccountDemo {
    public static void main (String args[]){
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        // Scanner set up
        Scanner scanner = new Scanner(System.in);
        int optionNum;
        double amount;

        BankAccount user1 = new BankAccount(5.01);
        BankAccount user2 = new BankAccount();

        while(true){
            System.out.println("Press corresponding keys to do an operation:");
            System.out.println(" (1) to view your current bank balance.");
            System.out.println(" (2) to withdraw amount from your bank account.");
            System.out.println(" (3) to deposit amount to your bank account");
            System.out.println(" (4) to transfer amount to another account");
            System.out.println(" (5) to exit.");
            System.out.println();
            try{
                optionNum = scanner.nextInt();
                switch(optionNum){
                    case 1:
                        user1.printBalance();
                        break;
                    case 2:
                        System.out.println("Write amount x.xx to be withdrawn: ");
                        amount = scanner.nextDouble();
                        // TODO check if correct decimal number is inputted
                        user1.withdraw(amount);
                        break;
                    case 3:
                        System.out.println("Write amount x.xx to be deposited: ");
                        amount = scanner.nextDouble();
                        user1.deposit(amount);
                        break;
                    case 4:
                        System.out.println("Write amount x.xx to be transfered to the other account: ");
                        amount = scanner.nextDouble();
                        user1.transfer(amount, user1, user2);
                        break;
                    case 5:
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
