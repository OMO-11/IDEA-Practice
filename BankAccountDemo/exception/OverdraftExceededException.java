package BankAccountDemo.exception;

/*
    姓名：张哲轩
    学号：240531102
 */

// 透支超额异常
public class OverdraftExceededException extends BankException {
    public OverdraftExceededException(String message) {
        super(1003, message);
    }
}
