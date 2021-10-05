package service;

import model.Expense;
import model.User;

public class NotificationServiceImpl implements NotificationService{
    @Override
    public void notifyUser(User user, Expense expense) {
        System.out.println("Notified");
    }
}
