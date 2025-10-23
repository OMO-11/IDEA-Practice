package src.Test_1.util;

import src.Test_1.exception.InvalidPriceException;
import java.math.BigDecimal;

public final class PriceValidator {
    private PriceValidator() {
        // 私有构造方法，防止实例化
    }

    public static void validatePrice(BigDecimal price) throws InvalidPriceException {
        if (price == null) {
            throw new InvalidPriceException("价格不能为null");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPriceException("价格不能为负数: " + price);
        }
    }
}
