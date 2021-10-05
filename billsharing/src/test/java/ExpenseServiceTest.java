import exception.ExpenseDoesNotExistsException;
import model.Expense;
import model.ExpenseStatus;
import model.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import repository.ExpenseRepository;
import service.ExpenseService;
import service.UserService;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExpenseServiceTest {
    static UserService userService = new UserService();
    static ExpenseService expenseService = new ExpenseService();

    @BeforeAll
    public static void initClass() {
        userService = new UserService();
        expenseService = new ExpenseService();
    }

    @Test
    @DisplayName("Create expense test")
    public void createUserTest() {
        User user = userService.createUser("bagesh@gmail.com", "bagesh", "3486199635");
        assertNotNull(user);
        assertEquals("bagesh@gmail.com", user.getEmailId());
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, user.getEmailId());

        assertNotNull(expense);
        assertEquals(2000, (long) expense.getExpenseAmount());
    }

    @Test
    @DisplayName("Add user to test")
    public void addUserToExpenseTest() throws ExpenseDoesNotExistsException {
        User user = userService.createUser("bagesh@gmail.com", "bagesh", "3486199635");
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, user.getEmailId());
        expenseService.addUsersToExpense(expense.getId(),  "bagesh@gmail.com");


        assertNotNull(expense.getExpenseGroup().getGroupMembers());
        assertEquals(1, expense.getExpenseGroup().getGroupMembers().size());
    }

    @Test
    @DisplayName("Add user to test")
    public void assignExpenseShareTest() {
        User user = userService.createUser("bagesh@gmail.com", "bagesh", "3486199635");
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, user.getEmailId());
        try {
            expenseService.addUsersToExpense(expense.getId(), "bagesh@gmail.com");
            expenseService.assignExpenseShare(expense.getId(),
                    ExpenseRepository.expenseMap.get(expense.getId()).getUserId(), 2000);
        } catch (ExpenseDoesNotExistsException expenseDoesNotExistsException) {
            System.out.println(expenseDoesNotExistsException.getMessage());
        }
        assertNotNull(expense.getExpenseGroup().getGroupMembers());
        assertEquals(1, expense.getExpenseGroup().getGroupMembers().size());
        assertNotNull(expense.getExpenseGroup().getExpenseGroupId());
    }

    @Test
    @DisplayName("Add user to test")
    public void setExpenseStatusTest() {
        User user = userService.createUser("bagesh@gmail.com", "bagesh", "3486199635");
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, user.getEmailId());
        expense.setExpenseStatus(ExpenseStatus.SETTLED);
        assertEquals(ExpenseStatus.SETTLED, expense.getExpenseStatus());
    }

}