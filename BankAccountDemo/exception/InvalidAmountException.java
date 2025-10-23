package BankAccountDemo.exception;

/*
    姓名：张哲轩
    学号：240531102
 */

// 无效金额异常
public class InvalidAmountException extends BankException {
    public InvalidAmountException(String message) {
        super(1002, message);
    }
}
