package SchoolSystem;

import SchoolSystem.SystemException.*;

import java.io.*;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Menu{
    //统一的异常处理方法，用来处理下面的try-catch语句，可以只用SchoolException来完成，而不用设置又臭又长的众多Exception,非常nb，我就说跟着AI学会学到好东西吧！！！！
    private void handleException(Exception e){
        if(e instanceof SchoolException){
            //向下转型，将Exception类的e转换为SchoolException类型
            SchoolException se = (SchoolException)e;
            System.out.println("错误代码" + se.getErrorCode() + "，错误信息" + se.getMessage());
        }
        else{
            System.out.println("系统错误：" + e.getMessage());
        }

        //根据错误类型提供不同的恢复建议
        if(e instanceof NofoundFile){
            System.out.println("请检查学生姓名是否正确，或该学生可能已经被删除");
        }
        else if(e instanceof DataValidationException){
            System.out.println("请按照提示格式输入有效的数据");
        }
        else if(e instanceof DuplicateDataException){
            System.out.println("请再次确认是否正确输入，该学生已存在");
        }
    }

    //前置准备
    Student student = new Student();
    function fun = new function();
    Scanner sc = new Scanner(System.in);

    //1.添加学生
    public void addStudent(){
        try{
            System.out.println("请依次输入学号，姓名和年龄：");
            int id = sc.nextInt();
            sc.nextLine(); // 消耗换行符
            String name = sc.nextLine();
            int age = sc.nextInt();
            sc.nextLine(); // 消耗换行符

            //接收回传入数据的student类
            student = fun.addInformation(id,name,age);
            fun.uploadStudent(student);
            System.out.println("数据已保存！");
        } catch(DataValidationException e){
            handleException(e);
        } catch(DuplicateDataException e){
            handleException(e);
            System.out.println("学生已存在，请检查是否正确");
        } catch(IOException | FileOperationException e){
            handleException(e);
            System.out.println("学生信息保存失败，请重试");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //2.删除学生
    public void deleteStudent(){
        try{
            System.out.println("请输入需要删除的学生姓名：");
            String name = sc.nextLine();
            if(name == null || name.trim().isEmpty()) {
                System.out.println("姓名不能为空！");
                return;
            }
            fun.deleteStudent(name);
        } catch(SchoolException e){
            handleException(e);
        }
    }

    //3.修改学生信息
    public void updateStudent() throws IOException, ClassNotFoundException{
        try{
            System.out.println("请输入需要修改学生的姓名：");
            String name = sc.nextLine();
            student = fun.reloadStudent(name);
            System.out.println("是否修改学号？ Y/N");
            if(sc.next().equals("Y")){
                System.out.println("请输入新学号：");
                student.setId(sc.nextInt());
                sc.nextLine();
            }
            System.out.println("是否修改姓名？ Y/N");
            if(sc.next().equals("Y")){
                sc.nextLine();
                System.out.println("请输入新姓名：");
                student.setName(sc.nextLine());
            }
            System.out.println("是否修改年龄？ Y/N");
            if(sc.next().equals("Y")){
                sc.nextLine();
                System.out.println("请输入新年龄：");
                student.setAge(sc.nextInt());
                sc.nextLine();
            }
            fun.uploadStudent(student);
            System.out.println("信息已修改完毕！新信息为：" + student.toString());
            sc.nextLine();
        } catch(SchoolException e){
            handleException(e);
        }
    }

    //4.展示学生信息
    public void displayStudent() throws IOException, ClassNotFoundException {
        try{
            System.out.println("1. 展示所有学生信息");
            System.out.println("2. 展示特定学生信息");

            String input = sc.nextLine();
            if(input.equals("1")) {
                fun.displayAllStudent();
            }
            else if (input.equals("2")) {
                System.out.println("请给出学生姓名：");
                student = fun.reloadStudent(sc.nextLine());
                System.out.println(student.toString());
            }
            else {
                System.out.println("无效输出！");
            }
        } catch(SchoolException e){
            handleException(e);
        }

    }

    //5. 菜单
    public void menu(){
        System.out.println("欢迎来到学生信息管理系统！");
        System.out.println("1. 添加学生");
        System.out.println("2. 删除学生");
        System.out.println("3. 修改学生信息");
        System.out.println("4. 查看学生信息");
        System.out.println("5. 退出");
    }
}
