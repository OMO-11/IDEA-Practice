package src.Test_1.model;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {
    private final Product product;
    private int quantity;
    private final BigDecimal unitPrice;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice(); // 记录加入购物车时的价格
    }

    // Getter和Setter方法
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    @Override
    public String toString() {
        return String.format("购物车项[商品:%s, 数量:%d, 单价:¥%.2f, 小计:¥%.2f]",
                product.getProductName(), quantity, unitPrice, getTotalPrice());
    }
}
