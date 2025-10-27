package SchoolSystem;

import SchoolSystem.File.uploadFile;
import SchoolSystem.File.reloadFile;
import SchoolSystem.SystemException.DataValidationException;
import SchoolSystem.SystemException.DuplicateDataException;
import SchoolSystem.SystemException.FileOperationException;
import SchoolSystem.SystemException.NofoundFile;
import SchoolSystem.Util.ExceptionHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class function {
    private static final String INFORMATION_PATH = "W:\\Machine\\IDEA\\JavaBox\\untitled\\SchoolSystem\\StudentInformation\\";
    //文件操作，包括文件读取、文件上传以及删除文件，并且含有异常的检测
    public void uploadStudent(Student stu) throws IOException, FileOperationException {
        try{
            uploadFile file = new uploadFile();
            file.upFile(stu);
        } catch(IOException e){
            throw new FileOperationException("保存", stu.getName() + ".txt", e.getMessage());
        }
    }

    public Student reloadStudent(String name) throws IOException, ClassNotFoundException, NofoundFile {
        String filePath = name + ".txt";
        ExceptionHelper.throwIfFileNotExists(filePath, "读取学生信息");

        reloadFile loader = new reloadFile();
        return loader.reFile(name);
    }

    public void deleteStudent(String name) throws NofoundFile, FileOperationException{
        File file = new File(INFORMATION_PATH + name + ".txt");
        if(!file.exists()){
            throw new NofoundFile(name);
        }

        //文件删除操作
        if(!file.delete()){
            throw new FileOperationException("删除", name + ".txt", "文件可能被占用或无权限");
        }
        System.out.println("学生" + name + "信息已删除！");
    }

    //学生信息操作
    public Student addInformation(int id, String name, int age) throws DataValidationException, DuplicateDataException, IOException, ClassNotFoundException {
        //首先进行数据验证
        ExceptionHelper.validateStudentData(id, name, age);
        //然后检查文件是否重复
        ExceptionHelper.checkDuplicateStudent(id, name, age);

        Student stu = new Student();
        stu.setId(id);
        stu.setName(name);
        stu.setAge(age);
        return stu;
    }

    public void displayAllStudent() throws IOException, ClassNotFoundException {
        Student student = new Student();
        List<Student> stuArray = new ArrayList<>();

        //利用File[]数组来储存该路径内的所有文件名
        File file = new File(INFORMATION_PATH);
        File[] files = file.listFiles();

        if (files != null) {
            //遍历File[]内的文件并将信息存入stuArray
            for (File f : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                    student = (Student) ois.readObject();
                    stuArray.add(student);
                }
            }
            //改进：利用ArrayList的sort循环排序学号
            stuArray.sort(Comparator.comparingInt(Student::getId));
            for (Student stu : stuArray) {
                System.out.println(stu.toString());
            }
//双重循环效率底下，并且如果学号未连续则会输出空信息
//                  int num = 1;
//                  for(int i = 0; i < stuArray.size(); i++){
//                       Student stu = new Student();
//                  for(int j = 0; j < stuArray.size(); j++){
//                       if(stuArray.get(j).getId() == num ){
//                          stu = stuArray.get(j);
//                         }
//                       }
//                     System.out.println(stu.toString());
//                     num++;
//                  }
        }
    }
}
