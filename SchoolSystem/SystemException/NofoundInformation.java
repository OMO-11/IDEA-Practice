package SchoolSystem.SystemException;

public class NofoundInformation extends SchoolException {
    public NofoundInformation(String studentInfo) {
        super(1002, "学生信息未找到：" + studentInfo);
    }

    public NofoundInformation(int studentId){
        super(1002, "学号为" + studentId + "的学生不存在！");
    }

    public NofoundInformation(String field, String value){
        super(1002, "根据" + field + "'" + value + "' 未找到学生信息！");
    }
}
