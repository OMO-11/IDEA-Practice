package src.Test_1.exception;

// 商品已存在异常
public class ProductExistsException extends Exception {
    public ProductExistsException(String message) {
        super(message);
    }
}
