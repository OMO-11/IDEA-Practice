package BankAccountDemo.exception;

/*
    姓名：张哲轩
    学号：240531102
 */

// 余额不足异常
public class InsufficientFundsException extends BankException {
    public InsufficientFundsException(String message) {
        super(1001, message);
    }
}

