package src.Test_1.model;

import src.Test_1.exception.InsufficientStockException;
import src.Test_1.exception.ProductNotFoundException;
import src.Test_1.service.InventoryManagementSystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, CartItem> items;
    private final InventoryManagementSystem inventorySystem;

    public ShoppingCart(InventoryManagementSystem system) {
        items = new HashMap<>();
        inventorySystem = system;
    }

    public void addItem(String productId, int quantity)
            throws ProductNotFoundException, InsufficientStockException {
        // 检查商品是否存在且有足够库存
        Product product = inventorySystem.getProduct(productId);
        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException(
                    String.format("商品%s库存不足，当前库存：%d，需求数量：%d",
                            product.getProductName(), product.getStockQuantity(), quantity));
        }

        // 添加或更新购物车项
        CartItem existingItem = items.get(productId);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            items.put(productId, new CartItem(product, quantity));
        }
    }

    public void updateQuantity(String productId, int newQuantity)
            throws ProductNotFoundException, InsufficientStockException {
        if (!items.containsKey(productId)) {
            throw new ProductNotFoundException("购物车中不存在该商品: " + productId);
        }

        Product product = inventorySystem.getProduct(productId);
        if (product.getStockQuantity() < newQuantity) {
            throw new InsufficientStockException(
                    String.format("商品%s库存不足，当前库存：%d，需求数量：%d",
                            product.getProductName(), product.getStockQuantity(), newQuantity));
        }

        items.get(productId).setQuantity(newQuantity);
    }

    public void removeItem(String productId) {
        items.remove(productId);
    }

    public BigDecimal getTotalPrice() {
        return items.values().stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(items.values());
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getItemCount() {
        return items.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("购物车内容:\n");
        for (CartItem item : items.values()) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append(String.format("总计: ¥%.2f", getTotalPrice()));
        return sb.toString();
    }
}
