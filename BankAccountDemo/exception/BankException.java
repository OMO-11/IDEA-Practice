package BankAccountDemo.exception;

public class BankException extends Exception {
    private int errorCode;

    public BankException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

