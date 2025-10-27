package SchoolSystem.File;

import SchoolSystem.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class reloadFile {
    private static final String INFORMATION_PATH = "W:\\Machine\\IDEA\\JavaBox\\untitled\\SchoolSystem\\StudentInformation\\";


    public Student reFile(String name) throws IOException, ClassNotFoundException {
        Student student;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(INFORMATION_PATH + name + ".txt"))){
            student = (Student) in.readObject();
        }
        return student;
    }
}
