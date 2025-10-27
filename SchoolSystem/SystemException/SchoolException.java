package SchoolSystem.SystemException;

public class SchoolException extends Exception {
    private final int errorCode;
    public SchoolException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
