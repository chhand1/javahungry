package repository;

import lombok.Getter;
import lombok.Setter;
import model.Expense;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ExpenseRepository {
    public static Map<String, Expense> expenseMap = new HashMap<>();
}
