package exception;

public class ContributionExceededException extends Throwable {
    public ContributionExceededException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
