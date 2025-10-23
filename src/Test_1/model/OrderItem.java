package src.Test_1.model;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {
    private final Product product;
    private final int quantity;
    private final BigDecimal unitPrice;

    public OrderItem(Product product, int quantity, BigDecimal unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Getter方法
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(product, orderItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    @Override
    public String toString() {
        return String.format("订单项[商品:%s, 数量:%d, 单价:¥%.2f, 小计:¥%.2f]",
                product.getProductName(), quantity, unitPrice, getSubtotal());
    }
}
