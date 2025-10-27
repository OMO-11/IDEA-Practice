package SchoolSystem.SystemException;

//文件操作异常
public class FileOperationException extends SchoolException {
    //FileOperationException异常传入的数据分别为：操作名称，文件名以及异常原因
    public FileOperationException(String operation, String fileName, String reason){
        super(1005, operation + " 文件 '" + fileName + "' 失败 " + reason);
    }
}
