import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBankAccount {
    private static final double DELTA = 0.001; // Tolerance for double comparisons
    @Nested
    class DepositFunctionTesting{
        @Test
        @DisplayName("No amount is being deposited")
        void ifZeroIsDeposited(){
            BankAccount test1 = new BankAccount();
            test1.deposit(0);
            assertEquals(0,test1.getBalance(), DELTA);
        }
        @Test
        @DisplayName("Correct amount is being deposited")
        void correctAmountIsDeposited(){
            BankAccount test1 = new BankAccount();
            test1.deposit(250.04);
            assertEquals(250.04, test1.getBalance(), DELTA);
        }
        @Test
        @DisplayName("Negative amount is not accepted")
        void negativeAmountIsNotAccepted(){
            BankAccount test1 = new BankAccount();
            test1.deposit(-34.02);
            assertEquals(0, test1.getBalance(), DELTA);
        }
    }
    @Nested
    class WithdrawFunctionTesting{
        @Test
        @DisplayName("No amount is being withdrawn")
        void ifZeroIsWithdrawn(){
            BankAccount test1 = new BankAccount();
            test1.withdraw(0);
            assertEquals(0, test1.getBalance(), DELTA);

        }
        @Test
        @DisplayName("Correct amount is withdrawn")
        void ifCorrectAmountIsWithdrawn(){
            BankAccount test1 = new BankAccount(23.23, "test");
            test1.withdraw(13.12);
            assertEquals(10.11, test1.getBalance(), DELTA);

        }
        @Test
        @DisplayName("Negative amount can't be withdrawn")
        void ifNegativeAmountIsWithdrawn(){
            BankAccount test1 = new BankAccount(23.23, "test");
            test1.withdraw(-13.12);
            assertEquals(23.23, test1.getBalance(), DELTA);

        }
    }
    @Nested
    class TransferFunctionTesting{
        BankAccount test1 = new BankAccount(25, "test1");
        BankAccount test2 = new BankAccount(40, "test2");

        @Test
        @DisplayName("Negative amount can't be transferred")
        void ifNegativeAmountIsTransferred(){
            test1.transfer(-4, test1, test2);
            assertEquals(25, test1.getBalance(), DELTA);
        }

    }

}