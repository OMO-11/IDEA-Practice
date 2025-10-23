package BankAccountDemo.Account;

import BankAccountDemo.exception.InvalidAmountException;
import BankAccountDemo.exception.OverdraftExceededException;

/*
    姓名：张哲轩
    学号：240531102
 */

public class CheckingAccount extends BankAccount {
    // 透支额度
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, balance);
        this.overdraftLimit = overdraftLimit;
    }

    // 存款逻辑：支持透支恢复
    @Override
    public void deposit(double amount) throws InvalidAmountException {
        double Bal = getBalance();
        if (amount <= 0) {
            throw new InvalidAmountException("存款金额必须大于0！");
        }

        Bal += amount;
        System.out.println("成功存入 " + amount + " 元。当前余额：" + Bal);

        if (Bal >= 0) {
            System.out.println("透支额度已恢复，可继续使用最高 " + overdraftLimit + " 元的透支额度。");
        }
    }

    // 取款逻辑：支持透支
    @Override
    public void withdraw(double amount) throws OverdraftExceededException, InvalidAmountException {
        double Bal = getBalance();
        if (amount <= 0) {
            throw new InvalidAmountException("取款金额必须大于0！");
        }

        if (amount > Bal + overdraftLimit) {
            throw new OverdraftExceededException("透支额度超限，无法取出 " + amount + " 元。");
        }

        Bal -= amount;
        System.out.println("成功取出 " + amount + " 元。当前余额：" + Bal);

        if (Bal < 0) {
            double usedOverdraft = -Bal;
            double remainingLimit = overdraftLimit - usedOverdraft;
            System.out.println("已透支 " + usedOverdraft + " 元，剩余额度：" + remainingLimit + " 元。");
        }
    }

    // 展示账户信息
    @Override
    public void displayAccountInfo() {
        double Bal = getBalance();
        super.displayAccountInfo();
        System.out.println("账户类型: 支票账户");
        System.out.println("透支额度: " + overdraftLimit);

        if (Bal < 0) {
            System.out.println("当前已透支: " + (-Bal) + " 元");
        }
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}
