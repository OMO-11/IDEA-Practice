package src.Test_1.exception;

// 商品不存在异常
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
