package exception;

public class ExpenseDoesNotExistsException extends Throwable {
    public ExpenseDoesNotExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
