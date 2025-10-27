package SchoolSystem.File;

import SchoolSystem.Student;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class uploadFile {
    private static final String INFORMATION_PATH = "W:\\Machine\\IDEA\\JavaBox\\untitled\\SchoolSystem\\StudentInformation\\";

    public void upFile(Student student) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(INFORMATION_PATH + student.getName() + ".txt"))){
            out.writeObject(student);
        }
    }
}
