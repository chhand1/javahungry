import exception.ContributionExceededException;
import exception.ExpenseDoesNotExistsException;
import exception.ExpenseSettledException;
import exception.InvalidExpenseException;
import model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import repository.ExpenseRepository;
import service.ExpenseService;
import service.UserService;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {
    static UserService userService = new UserService();
    static ExpenseService expenseService = new ExpenseService();

    @BeforeAll
    public static void initClass() {
        userService = new UserService();
        expenseService = new ExpenseService();
    }

    @Test
    @DisplayName("Create user test")
    public void createUserTest() {
        User user = userService.createUser("bagesh@gmail.com", "bagesh", "3486199635");
        assertNotNull(user);
        Assert.assertEquals("bagesh@gmail.com", user.getEmailId());
    }

    @Test
    @DisplayName("Create user null email id test")
    public void createUserNullEmailTest() {
        assertThrows(NullPointerException.class, () -> {
            User user = userService.createUser(null, "bagesh", "3486199635");
        });
    }

    private static void bifurcateExpense(String expenseId) throws ExpenseDoesNotExistsException {
        expenseService.addUsersToExpense(expenseId, "bagesh@gmail.com");
        expenseService.assignExpenseShare(expenseId, ExpenseRepository.expenseMap.get(expenseId).getUserId(), 2000);
    }

    @Test
    @DisplayName("Contribute share to expense")
    public void contributeToExpense() throws ContributionExceededException, ExpenseSettledException, InvalidExpenseException {
        User user = userService.createUser("bagesh@gmail.com", "bagesh", "3486199635");
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, user.getEmailId());
        try {
            bifurcateExpense(expense.getId());
        } catch (ExpenseDoesNotExistsException expenseDoesNotExistsException) {
            System.out.println(expenseDoesNotExistsException.getMessage());
        }
        expense.setExpenseStatus(ExpenseStatus.PENDING);
        Set<User> users = expense.getExpenseGroup().getGroupMembers();
        for (User usr : users) {
            Contribution contribution = new Contribution();
            ExpenseGroup expenseGroup = expense.getExpenseGroup();
            UserShare userShare = expenseGroup.getUserContributions().get(usr.getEmailId());
            contribution.setContributionValue(600);
            contribution.setContributionDate(LocalDateTime.now());
            contribution.setTransactionId("T" + Instant.EPOCH);
            contribution.setTransactionDescription("Transferred from UPI");
            userService.contributeToExpense(expense.getId(), usr.getEmailId(), contribution);
        }

        Assert.assertEquals(600, (long) expense.getExpenseGroup().getUserContributions()
                .get(user.getEmailId()).getContributions().get(0).getContributionValue());
    }
}