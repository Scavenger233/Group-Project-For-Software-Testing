import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountManagementSystemTest {

    /*
    * methods preCreate, testAccountExistsWithPositiveBalance,
    * testAccountNotExistsWithNegativeBalance and testAccountNotExistsWithPositiveBalance
    *  are written by Xiaoxuan Li 23005491
    */
    private BankAccountManagementSystem system;

    @BeforeEach
    public void preCreate(){
        //this method is to set up an account with accountNumber = 1
        system = new BankAccountManagementSystem();
        system.createAccount(1, 5.0);
    }

    @Test
    public void testAccountExistsWithPositiveBalance(){
        //Failed to create an account as the account already exists
        //account exists, the balance is 5.0
        boolean result = system.createAccount(1, 5.0);
        assertFalse(result, "Expected result is false since the account already exists.");
    }

    @Test
    public void testAccountNotExistsWithNegativeBalance(){
        //Failed to create an account as the initial balance is negative
        //account does not exist, the balance is -5.0
        boolean result = system.createAccount(2, -5.0);
        assertFalse(result, "Expected result is false since the balance is negative.");
    }

    @Test
    public void testAccountNotExistsWithPositiveBalance(){
        //Successfully create an account with the positive initial balance
        //account does not exist, the balance is 5.0
        boolean result = system.createAccount(3, 5.0);
        assertTrue(result, "Expected result is true since the account does not exist and the balance is positive.");
    }

    @org.junit.jupiter.api.Test
    void createAccount() {
    }

    @org.junit.jupiter.api.Test
    void deposit() {
    }

    @org.junit.jupiter.api.Test
    void withdraw() {
    }

    @org.junit.jupiter.api.Test
    void getAccountBalance() {
    }
}
