package exception;

public class ExpenseSettledException extends Throwable {
    public ExpenseSettledException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
