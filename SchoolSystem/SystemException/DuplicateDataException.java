package SchoolSystem.SystemException;

import SchoolSystem.SystemException.SchoolException;

//重复数据异常
public class DuplicateDataException extends SchoolException {
    //DuplicateDataException异常传入数据分别为重复信息类型和重复信息值
    public DuplicateDataException(String dataType, String value) {
        super(1003, dataType + " '" + value + "' 已存在，不能重复添加！");
    }
}
