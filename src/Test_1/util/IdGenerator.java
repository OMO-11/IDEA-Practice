package src.Test_1.util;

import java.util.concurrent.atomic.AtomicLong;

public final class IdGenerator {
    private static final AtomicLong productCounter = new AtomicLong(1000);
    private static final AtomicLong categoryCounter = new AtomicLong(100);
    private static final AtomicLong orderCounter = new AtomicLong(10000);

    private IdGenerator() {
        // 私有构造方法，防止实例化
    }

    public static String generateProductId() {
        return "P" + productCounter.getAndIncrement();
    }

    public static String generateCategoryId() {
        return "C" + categoryCounter.getAndIncrement();
    }

    public static String generateOrderId() {
        return "O" + orderCounter.getAndIncrement();
    }
}
