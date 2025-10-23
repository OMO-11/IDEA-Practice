package src.Test_1.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private final String orderId;
    private final LocalDateTime orderDate;
    private final List<OrderItem> orderItems;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private final String customerInfo;

    public Order(String orderId, List<OrderItem> orderItems, String customerInfo) {
        this.orderId = orderId;
        this.orderDate = LocalDateTime.now();
        this.orderItems = new ArrayList<>(orderItems);
        this.customerInfo = customerInfo;
        this.status = OrderStatus.PENDING;
        calculateTotalAmount();
    }

    // Getter和Setter方法
    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public List<OrderItem> getOrderItems() { return new ArrayList<>(orderItems); }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public OrderStatus getStatus() { return status; }
    public String getCustomerInfo() { return customerInfo; }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    private void calculateTotalAmount() {
        this.totalAmount = orderItems.stream()
                .map(OrderItem::getSubtotal)   //将OrderItem对象转换为BigDecimal金额
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        calculateTotalAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return String.format("订单[ID:%s, 日期:%s, 总金额:¥%.2f, 状态:%s, 客户:%s]",
                orderId, orderDate, totalAmount, status.getDescription(), customerInfo);
    }
}
