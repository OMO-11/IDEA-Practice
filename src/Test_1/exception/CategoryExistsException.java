package src.Test_1.exception;

// 分类已存在异常
public class CategoryExistsException extends Exception {
    public CategoryExistsException(String message) {
        super(message);
    }
}
