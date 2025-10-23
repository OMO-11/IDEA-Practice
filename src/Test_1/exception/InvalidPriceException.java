package src.Test_1.exception;

// 价格异常
public class InvalidPriceException extends Exception {
    public InvalidPriceException(String message) {
        super(message);
    }
}
