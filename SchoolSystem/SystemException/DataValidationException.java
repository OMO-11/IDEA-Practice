package SchoolSystem.SystemException;

//数据验证异常
public class DataValidationException extends SchoolException {
    public DataValidationException(String field, String reason) {
        super(1004, field + " 数据无效：" + reason);
    }

    public DataValidationException(String message){
        super(1004, message);
    }
}
