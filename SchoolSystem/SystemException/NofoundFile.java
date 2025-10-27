package SchoolSystem.SystemException;

public class NofoundFile extends SchoolException {
    public NofoundFile(String fileName){
        super(1001, "文件未找到：" + fileName);
    }

    public NofoundFile(String fileName, String operation){
        super(1001, operation + "操作失败：文件" + fileName + "'不存在'");
    }

    //静态工厂方法
    public static NofoundFile forStudent(String studentName){
        return new NofoundFile(studentName + ".txt", "读取学生信息！");
    }
}
