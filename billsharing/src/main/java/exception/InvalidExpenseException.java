package exception;

public class InvalidExpenseException extends Throwable {
    public InvalidExpenseException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
