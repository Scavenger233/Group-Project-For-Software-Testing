import org.example.BankAccountManagementSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountManagementSystemTest {

    private BankAccountManagementSystem bank;

    @BeforeEach
    public void setUp() {
        bank = new BankAccountManagementSystem();
        bank.createAccount(1, 5.0);//this sets up an account with accountNumber = 1; for creatAccount method
        bank.createAccount(123, 100.0); // Assume that there is an account already
        bank.createAccount(456, 0.0); // Assume that there is an account already
    }

    @Test
    public void testAccountExistsWithPositiveBalance(){
        //Test requirement: The account already exists, with an initial balance that equals to 0 or is greater than 0
        //account exists, the balance is 5.0
        boolean result = bank.createAccount(1, 5.0);
        assertFalse(result, "Expected result is false since the account already exists.");
    }

    @Test
    public void testAccountNotExistsWithNegativeBalance(){
        //Test requirement: The account does not exist, with an initial balance that is less than 0
        //account does not exist, the balance is -5.0
        boolean result = bank.createAccount(2, -5.0);
        assertFalse(result, "Expected result is false since the balance is negative.");
    }

    @Test
    public void testAccountNotExistsWithPositiveBalance(){
        //Test requirement: The account does not exist, with an initial balance that equals to 0 or is greater than 0
        //account does not exist, the balance is 5.0
        boolean result = bank.createAccount(3, 5.0);
        assertTrue(result, "Expected result is true since the account does not exist and the balance is positive.");
    }

    @Test
    public void testValidDeposit() {
        //Test requirement: Deposit a amount greater than 0 into an existing account.
        //The account exists and 100 is successfully deposited, and the balance is 105.
        double newBalance = bank.deposit(1, 100.0);
        assertEquals(105.0, newBalance);
    }

    @Test
    public void testDepositWithInvalidAccount() {
        //Test requirement:Account does not exist. Try to deposit a amount greater than 0.
        //Account does not exist so deposit failed, balance unchanged, return -1.0.
        //Overwrite branches that do not exist in the account.
        double result = bank.deposit(2, 50.0);
        assertEquals(-1.0, result);
    }

    @Test
    public void testDepositZeroAmount() {
        //Test requirement:The account exists,but deposit amount is 0.
        //The account exists but the deposit amount is 0, so the balance remains the same.
        //This test covers special cases: critical values.
        double newBalance = bank.deposit(1, 0.0);
        assertEquals(5.0, newBalance);
    }

    @Test
    public void testDepositNegativeAmount() {
        //Test requirement:The account exists, but the deposit amount is less than 0.
        //The account exists but the balance remains the same.
        //Negative values may not exist in practice, but for system security reasons, spikes should probably throw an exception.
        double result = bank.deposit(1, -100.0);
        assertEquals(-95.0, result);
    }

    // Test Case 1: Account does not exist
    @Test
    public void testWithdrawAccountDoesNotExist() {
        double result = bank.withdraw(999, 100.0); // Assume that account 999 is not exist
        assertEquals(0.0, result);
        // Coverage: Statement, Branch (true branch for `if (!accounts.containsKey(accountNumber))`), Condition (evaluates `accounts.containsKey(accountNumber)` as false)
    }

    // Test Case 2: Existing account, withdrawal amount <= 0
    @Test
    public void testWithdrawAmountLessThanOrEqualToZero() {
        double result = bank.withdraw(123, -10.0); // withdraw amount less than 0
        assertEquals(-1.0, result);
        // Coverage: Statement, Branch (true branch for `if (amount <= 0)`), Condition (evaluates `amount <= 0` as true)
    }

    // Test Case 3: Existing account, withdrawal amount > balance
    @Test
    public void testWithdrawAmountGreaterThanBalance() {
        double result = bank.withdraw(123, 500.0); // withdraw amount more than balance
        assertEquals(-2.0, result);
        // Coverage: Statement, Branch (true branch for `if (amount >= balance)`), Condition (evaluates `amount >= balance` as true)
    }

    // Test Case 4: Existing account, amount = 0 or balance = 0
    @Test
    public void testWithdrawAmountOrBalanceIsZero() {
        bank.createAccount(456, 0.0); // Create an account with number 456 and balance 0
        double resultAmountZero = bank.withdraw(123, 0); // Withdraw amount 0
        double resultBalanceZero = bank.withdraw(456, 10.0); // balance 0

        assertEquals(-3.0, resultAmountZero);
        assertEquals(-3.0, resultBalanceZero);
        // Coverage: Statement, Branch (true branch for `else if (amount == 0 || balance == 0)`)
        // Those two cases will fail because the account sample cannot reach the last condition
    }

    // Test Case 5: Valid withdrawal (amount < balance) with a positive balance
    @Test
    public void testValidWithdrawal() {
        double result = bank.withdraw(123, 50.0); // Withdraw amount less than balance
        assertEquals(50.0, result); // Account balance updated
        // Coverage: Statement (final part of method), Branch (false branches for all `if` and `else if` statements), Condition (evaluates all conditions as false)
    }
}