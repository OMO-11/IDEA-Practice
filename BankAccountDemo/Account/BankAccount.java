package BankAccountDemo.Account;

import BankAccountDemo.exception.BankException;
import BankAccountDemo.exception.InvalidAmountException;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/*
    姓名：张哲轩
    学号：240531102
 */

public abstract class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    //所有账户存储区
    protected static Map<String, BankAccount> accounts = new HashMap<>();

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        // 每创建一个账户，自动加入系统
        accounts.put(accountNumber, this);
    }

    //抽象方法
    //存款
    public abstract void deposit(double amount) throws InvalidAmountException;
    //取款
    public abstract void withdraw(double amount) throws BankException;

    // 公共方法
    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void displayAccountInfo() {
        System.out.println("账号: " + accountNumber);
        System.out.println("持有人: " + accountHolder);
        System.out.println("余额: " + balance);
    }

    //静态账户管理方法
    //添加账户
    public static void addAccount(BankAccount account) {
        accounts.put(account.accountNumber, account);
    }

    //获取账户
    public static BankAccount getAccount(String number) {
        return accounts.get(number);
    }

    //用Collection获取所有账户
    public static Collection<BankAccount> getAllAccounts() {
        return accounts.values();
    }
}

