package SchoolSystem;

import SchoolSystem.SystemException.DataValidationException;
import SchoolSystem.SystemException.FileOperationException;
import SchoolSystem.SystemException.NofoundFile;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NofoundFile, DataValidationException, FileOperationException {
        Menu menu = new Menu();
        int i = 1;
        Scanner sc = new Scanner(System.in);
        while(i != 5){
            try{
                menu.menu();
                i = sc.nextInt();
                sc.nextLine();
                switch(i){
                    case 1:
                        menu.addStudent();
                        break;
                    case 2:
                        menu.deleteStudent();
                        break;
                    case 3:
                        menu.updateStudent();
                        break;
                    case 4:
                        menu.displayStudent();
                        break;
                    case 5:
                        System.out.println("谢谢使用喵，用的开心请给五星好评喵！");
                        break;
                    default:
                        System.out.println("请输入1~5的数字！");
                }
            } catch(Exception e){
                System.out.println("发生未知错误：" + e.getMessage());
                e.printStackTrace(); // 打印异常的调用栈信息，开发阶段可以保留，生产环境应该移除
                sc.nextLine(); // 清除错误的输入
            }
        }
        sc.close();
    }
}
