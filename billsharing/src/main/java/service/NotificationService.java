package service;

import model.Expense;
import model.User;

public interface NotificationService {
    void notifyUser(User user, Expense expense);
}
