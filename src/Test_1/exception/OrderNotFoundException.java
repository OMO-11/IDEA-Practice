// 自定义异常类
package src.Test_1.exception;

// 订单不存在异常
public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }
}