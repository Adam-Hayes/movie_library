package exception;

public class SQLConnectionException extends RuntimeException {

    public SQLConnectionException() {

    }

    public SQLConnectionException(String message) {
        super(message);
    }

    public SQLConnectionException(Throwable cause) {
        super(cause);
    }

    public SQLConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
