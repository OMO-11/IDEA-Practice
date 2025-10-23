package src.Test_1;

import src.Test_1.exception.*;
import src.Test_1.model.*;
import src.Test_1.service.InventoryManagementSystem;
import src.Test_1.util.IdGenerator;

import java.math.BigDecimal;
import java.util.List;

public class TestInventoryManagement {
    public static void main(String[] args) {
        try {
            InventoryManagementSystem system = new InventoryManagementSystem();

            // 1. 创建分类
            System.out.println("=== 创建分类 ===");
            Category electronics = new Category(
                    IdGenerator.generateCategoryId(), "电子产品", null, "电子设备分类");
            Category phones = new Category(
                    IdGenerator.generateCategoryId(), "手机", electronics, "手机分类");
            Category laptops = new Category(
                    IdGenerator.generateCategoryId(), "笔记本电脑", electronics, "笔记本分类");

            system.addCategory(electronics);
            system.addCategory(phones);
            system.addCategory(laptops);

            // 2. 创建商品
            System.out.println("\n=== 创建商品 ===");
            Product iphone = new Product(
                    IdGenerator.generateProductId(), "iPhone 15", "最新款苹果手机",
                    new BigDecimal("5999"), 100, phones);
            Product samsung = new Product(
                    IdGenerator.generateProductId(), "Samsung Galaxy", "三星旗舰手机",
                    new BigDecimal("4999"), 50, phones);
            Product macbook = new Product(
                    IdGenerator.generateProductId(), "MacBook Pro", "苹果笔记本电脑",
                    new BigDecimal("12999"), 30, laptops);

            system.addProduct(iphone);
            system.addProduct(samsung);
            system.addProduct(macbook);

            // 3. 显示所有商品
            System.out.println("\n=== 所有商品 ===");
            system.getAllProducts().forEach(System.out::println);

            // 4. 库存操作测试
            System.out.println("\n=== 库存操作测试 ===");
            system.restock(iphone.getProductId(), 50);
            System.out.println("iPhone补货后: " + system.getProduct(iphone.getProductId()));

            // 5. 搜索功能测试
            System.out.println("\n=== 搜索功能测试 ===");
            System.out.println("搜索'手机':");
            system.searchProducts("手机").forEach(System.out::println);

            // 6. 购物车和订单测试
            System.out.println("\n=== 购物车和订单测试 ===");
            ShoppingCart cart = system.createShoppingCart();
            cart.addItem(iphone.getProductId(), 2);
            cart.addItem(macbook.getProductId(), 1);
            System.out.println(cart);

            Order order = system.createOrder(cart.getCartItems(), "张三");
            System.out.println("创建订单: " + order);

            // 7. 统计功能测试
            System.out.println("\n=== 统计功能测试 ===");
            System.out.println("库存总价值: ¥" + system.getTotalInventoryValue());
            System.out.println("分类商品数量统计:");
            system.getCategoryProductCount().forEach((category, count) ->
                    System.out.println("  " + category.getCategoryName() + ": " + count));

            System.out.println("商品状态统计:");
            system.getProductStatusStatistics().forEach((status, count) ->
                    System.out.println("  " + status.getDescription() + ": " + count));

            // 8. 低库存预警
            System.out.println("\n=== 低库存预警 ===");
            List<Product> lowStockProducts = system.getLowStockProducts();
            if (lowStockProducts.isEmpty()) {
                System.out.println("暂无低库存商品");
            } else {
                System.out.println("低库存商品:");
                lowStockProducts.forEach(System.out::println);
            }

            // 9. 异常处理测试
            System.out.println("\n=== 异常处理测试 ===");
            try {
                system.sellProduct("不存在的ID", 1);
            } catch (ProductNotFoundException e) {
                System.out.println("异常捕获: " + e.getMessage());
            }

            try {
                system.sellProduct(iphone.getProductId(), 1000);
            } catch (InsufficientStockException e) {
                System.out.println("异常捕获: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("程序异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
}