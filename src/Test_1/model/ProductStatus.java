package src.Test_1.model;

// 商品状态枚举
public enum ProductStatus {
    AVAILABLE("可销售"),
    OUT_OF_STOCK("缺货"),
    DISCONTINUED("已下架");

    private final String description;

    ProductStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
