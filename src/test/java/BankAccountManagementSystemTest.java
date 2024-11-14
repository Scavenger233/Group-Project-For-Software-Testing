import org.example.BankAccountManagementSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountManagementSystemTest {

    private BankAccountManagementSystem bank;

    @BeforeEach
    public void setUp() {
        bank = new BankAccountManagementSystem();
        bank.createAccount(123, 100.0); // Assume that there is an account already
        bank.createAccount(456, 0.0); // Assume that there is an account already
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
        bank.createAccount(456, 0.0); // 新建一个余额为0的账户456
        double resultAmountZero = bank.withdraw(123, 0); // 提款金额为0
        double resultBalanceZero = bank.withdraw(456, 10.0); // 账户余额为0

        assertEquals(-3.0, resultAmountZero);
        assertEquals(-3.0, resultBalanceZero);
        // Coverage: Statement, Branch (true branch for `else if (amount == 0 || balance == 0)`), Condition (evaluates `amount == 0` as true and `balance == 0` as true in separate tests)
    }

    // Test Case 5: Valid withdrawal (amount < balance) with a positive balance
    @Test
    public void testValidWithdrawal() {
        double result = bank.withdraw(123, 50.0); // 提款金额小于账户余额
        assertEquals(50.0, result); // 账户余额应该更新为50.0
        // Coverage: Statement (final part of method), Branch (false branches for all `if` and `else if` statements), Condition (evaluates all conditions as false)
    }
}
