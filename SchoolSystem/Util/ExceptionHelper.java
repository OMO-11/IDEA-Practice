package SchoolSystem.Util;

import SchoolSystem.Student;
import SchoolSystem.SystemException.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//异常工具类
public class ExceptionHelper {
    private static final String INFORMATION_PATH = "W:\\Machine\\IDEA\\JavaBox\\untitled\\SchoolSystem\\StudentInformation\\";

    //未存在文件时抛出
    public static void throwIfFileNotExists(String filePath, String operation) throws NofoundFile{
        java.io.File file = new java.io.File(INFORMATION_PATH + filePath);
        if(!file.exists()){
            throw new NofoundFile(filePath, operation);
        }
    }

    //检查输入信息是否为正确格式，格式错误抛出
    public static void validateStudentData(int id, String name, int age) throws DataValidationException{
        if(id <= 0){
            throw new DataValidationException("学号","必须为正整数！");
        }
        else if(name == null || name.trim().isEmpty()){
            throw new DataValidationException("姓名","不能为空！");
        }
        else if(age < 6 || age > 20)
            throw new DataValidationException("年龄","必须在6~20岁之间！");
    }

    //检查学生信息是否重复
    public static void checkDuplicateStudent(int id, String fileName, int age) throws IOException, ClassNotFoundException, DuplicateDataException {
        File file = new File(INFORMATION_PATH);
        File[] files = file.listFiles();
        Student student;

        List<Student> stuArray = new ArrayList<>();
        if (files != null) {
            for(File f : files){
                try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
                    student = (Student)ois.readObject();
                    stuArray.add(student);
                }
            }
        }
        for(Student s : stuArray){
            if(fileName.equals(s.getName())){
                throw new DuplicateDataException("文件名", fileName + ".txt");
            }
            if(id == s.getId()){
                throw new DuplicateDataException("学号", String.valueOf(id));
            }
        }
    }
}
