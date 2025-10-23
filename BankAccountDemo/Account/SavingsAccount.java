package BankAccountDemo.Account;

import BankAccountDemo.exception.InsufficientFundsException;
import BankAccountDemo.exception.InvalidAmountException;

/*
    姓名：张哲轩
    学号：240531102
 */

public class SavingsAccount extends BankAccount {
    //年利率
    private double annualInterestRate;

    public SavingsAccount(String accountNumber, String accountHolder, double balance, double annualInterestRate) {
        super(accountNumber, accountHolder, balance);
        this.annualInterestRate = annualInterestRate;
    }

    //存款重写
    @Override
    public void deposit(double amount) throws InvalidAmountException {
        double Bal = getBalance();
        if (amount <= 0) {
            throw new InvalidAmountException("存款金额必须大于0！");
        }
        Bal += amount;
        System.out.println("成功存入 " + amount + " 元。当前余额：" + Bal);
    }

    //取款重写
    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        double Bal = getBalance();
        //判断取款金额多个条件
        if (amount <= 0) {
            throw new InvalidAmountException("取款金额必须大于0！");
        }
        if (amount > Bal) {
            throw new InsufficientFundsException("余额不足，无法取出 " + amount + " 元！");
        }
        Bal -= amount;
        System.out.println("成功取出 " + amount + " 元。当前余额：" + Bal);
    }

    //利息计算
    public void calculateInterest() {
        double Bal = getBalance();
        double interest = Bal * annualInterestRate / 100;
        Bal += interest;
        System.out.println("利息 " + interest + " 元已添加到账户。新余额：" + Bal);
    }

    //账户类型信息展示
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("账户类型：储蓄账户");
        System.out.println("年利率：" + annualInterestRate);
    }
}


