package src.Test_1.exception;

// 库存不足异常
public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}
