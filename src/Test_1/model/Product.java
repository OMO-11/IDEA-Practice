package src.Test_1.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Product implements Comparable<Product> {
    private final String productId;
    private final String productName;
    private final String description;
    private BigDecimal price;
    private int stockQuantity;
    private final Category category;
    private ProductStatus status;
    private final LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Product(String productId, String productName, String description,
                   BigDecimal price, int stockQuantity, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.status = stockQuantity > 0 ? ProductStatus.AVAILABLE : ProductStatus.OUT_OF_STOCK;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    // Getter和Setter方法
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public Category getCategory() { return category; }
    public ProductStatus getStatus() { return status; }
    public LocalDateTime getCreateTime() { return createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; }

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.updateTime = LocalDateTime.now();
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
        this.updateTime = LocalDateTime.now();
        // 更新状态
        if (stockQuantity > 0) {
            this.status = ProductStatus.AVAILABLE;
        } else {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
        this.updateTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public int compareTo(Product other) {
        return this.price.compareTo(other.price);
    }

    @Override
    public String toString() {
        return String.format("商品[ID:%s, 名称:%s, 价格:¥%.2f, 库存:%d, 状态:%s]",
                productId, productName, price, stockQuantity, status.getDescription());
    }
}
