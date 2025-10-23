package BankAccountDemo;

import BankAccountDemo.Account.*;
import BankAccountDemo.exception.BankException;

/*
    姓名：张哲轩
    学号：240531102
 */

public class BankTest {
    public static void main(String[] args) {
        System.out.println("==== 银行账户系统测试开始 ====\n");

        //创建账户
        SavingsAccount sa = new SavingsAccount("1001", "Hiane", 5000, 3.5);
        CheckingAccount ca = new CheckingAccount("2001", "Bob", 2000, 1000);

        //打印当前系统中已存在的账户总数
        System.out.println("当前账户数量: " + BankAccount.getAllAccounts().size() + "\n");

        //异常处理测试：
        try {
            //根据账号查找账户
            BankAccount acc1 = BankAccount.getAccount("1001");
            acc1.deposit(1000);
            acc1.displayAccountInfo();
            System.out.println();

            //向下转型，将BankAccount转换为SavingsAccount类型，目的是调用储蓄账户独有的方法 calculateInterest()
            ((SavingsAccount) acc1).calculateInterest();

            //取款操作，支票账户允许透支
            BankAccount acc2 = BankAccount.getAccount("2001");
            acc2.withdraw(2500);
            acc2.displayAccountInfo();
            System.out.println();

            //余额不足异常
            acc1.withdraw(10000);

        } catch (BankException e) {
            //捕获自定义异常 BankException
            System.out.println("捕获异常：" + e.getMessage() + "（错误码：" + e.getErrorCode() + "）\n");
        }

        //透支超额异常
        try {
            BankAccount acc2 = BankAccount.getAccount("2001");
            acc2.withdraw(4000);
        } catch (BankException e) {
            System.out.println("捕获异常：" + e.getMessage() + "（错误码：" + e.getErrorCode() + "）\n");
        }

        //账号无效异常
        try {
            BankAccount acc3 = BankAccount.getAccount("9999");
            if (acc3 == null)
                throw new BankException(9999, "账号不存在！");
        } catch (BankException e) {
            System.out.println("捕获异常：" + e.getMessage() + "（错误码：" + e.getErrorCode() + "）\n");
        }

        System.out.println("当前系统账户信息：");
        for (BankAccount acc : BankAccount.getAllAccounts()) {
            acc.displayAccountInfo();
            System.out.println("-------------------------");
        }

        System.out.println("测试结束");
    }
}
