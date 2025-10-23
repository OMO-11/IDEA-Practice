package src.Test_1.exception;

// 分类不存在异常
public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
