package src.Test_1.service;

import src.Test_1.exception.*;
import src.Test_1.model.*;
import src.Test_1.util.IdGenerator;
import src.Test_1.util.PriceValidator;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryManagementSystem {
    private final Map<String, Product> products;
    private final Map<String, Category> categories;
    private final List<Order> orders;
    private final int lowStockThreshold = 10;

    public InventoryManagementSystem() {
        products = new HashMap<>();
        categories = new HashMap<>();
        orders = new ArrayList<>();
    }

    // 分类管理
    public void addCategory(Category category) throws CategoryExistsException {
        if (categories.containsKey(category.getCategoryId())) {
            throw new CategoryExistsException("分类已存在: " + category.getCategoryId());
        }
        categories.put(category.getCategoryId(), category);
    }

    public Category getCategory(String categoryId) throws CategoryNotFoundException {
        Category category = categories.get(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException("分类不存在: " + categoryId);
        }
        return category;
    }

    public List<Category> getSubcategories(String parentCategoryId) throws CategoryNotFoundException {
        Category parent = getCategory(parentCategoryId);
        return categories.values().stream()
                .filter(c -> parent.equals(c.getParentCategory()))
                .collect(Collectors.toList());
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    // 商品管理
    public void addProduct(Product product) throws ProductExistsException {
        if (products.containsKey(product.getProductId())) {
            throw new ProductExistsException("商品已存在: " + product.getProductId());
        }
        products.put(product.getProductId(), product);
    }

    public Product getProduct(String productId) throws ProductNotFoundException {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("商品不存在: " + productId);
        }
        return product;
    }

    public void updateProductPrice(String productId, BigDecimal newPrice)
            throws ProductNotFoundException, InvalidPriceException {
        PriceValidator.validatePrice(newPrice);
        Product product = getProduct(productId);
        product.setPrice(newPrice);
    }

    public void discontinueProduct(String productId) throws ProductNotFoundException {
        Product product = getProduct(productId);
        product.setStatus(ProductStatus.DISCONTINUED);
    }

    // 库存操作
    public void restock(String productId, int quantity) throws ProductNotFoundException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("入库数量必须大于0");
        }
        Product product = getProduct(productId);
        product.setStockQuantity(product.getStockQuantity() + quantity);
    }

    public void sellProduct(String productId, int quantity)
            throws ProductNotFoundException, InsufficientStockException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("销售数量必须大于0");
        }

        Product product = getProduct(productId);
        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException(
                    String.format("商品%s库存不足，当前库存：%d，需求数量：%d",
                            product.getProductName(), product.getStockQuantity(), quantity));
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
    }

    // 订单管理
    public Order createOrder(List<CartItem> cartItems, String customerInfo)
            throws ProductNotFoundException, InsufficientStockException {
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("购物车不能为空");
        }

        // 检查库存并扣减
        for (CartItem item : cartItems) {
            sellProduct(item.getProduct().getProductId(), item.getQuantity());
        }

        // 创建订单项
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(item.getProduct(), item.getQuantity(), item.getUnitPrice()))
                .collect(Collectors.toList());  //将cartItem类的数据转换为OrderItem类集合

        // 创建订单
        String orderId = IdGenerator.generateOrderId();  //生成订单id
        Order order = new Order(orderId, orderItems, customerInfo);
        orders.add(order);

        return order;
    }

    public void updateOrderStatus(String orderId, OrderStatus newStatus) throws OrderNotFoundException {
        Order order = getOrder(orderId);
        order.setStatus(newStatus);
    }

    private Order getOrder(String orderId) throws OrderNotFoundException {
        return orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException("订单不存在: " + orderId));
    }

    // 查询和搜索
    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllProducts();
        }

        String lowerKeyword = keyword.toLowerCase();
        return products.values().stream()
                .filter(p -> p.getProductName().toLowerCase().contains(lowerKeyword) ||
                        p.getDescription().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByCategory(String categoryId) throws CategoryNotFoundException {
        Category category = getCategory(categoryId);
        return products.values().stream()
                .filter(p -> category.equals(p.getCategory()))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return products.values().stream()
                .filter(p -> p.getPrice().compareTo(minPrice) >= 0 &&
                        p.getPrice().compareTo(maxPrice) <= 0)
                .collect(Collectors.toList());
    }

    public List<Product> getLowStockProducts() {
        return products.values().stream()
                .filter(p -> p.getStockQuantity() <= lowStockThreshold &&
                        p.getStatus() != ProductStatus.DISCONTINUED)
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    // 统计功能
    public Map<Category, Integer> getCategoryProductCount() {
        Map<Category, Integer> countMap = new HashMap<>();
        for (Product product : products.values()) {
            Category category = product.getCategory();
            countMap.put(category, countMap.getOrDefault(category, 0) + 1);
        }
        return countMap;
    }

    public Map<ProductStatus, Long> getProductStatusStatistics() {
        return products.values().stream()
                .collect(Collectors.groupingBy(Product::getStatus, Collectors.counting()));
    }

    public BigDecimal getTotalInventoryValue() {
        return products.values().stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getStockQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Product> getTopSellingProducts(int limit) {
        // 简化实现：按库存变化量排序（实际应该按销售记录）
        return products.values().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getStockQuantity(), p1.getStockQuantity()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    // 购物车功能
    public ShoppingCart createShoppingCart() {
        return new ShoppingCart(this);
    }
}
