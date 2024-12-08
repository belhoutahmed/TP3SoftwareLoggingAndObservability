package MAIN;

import java.util.Random;
import java.time.LocalDate;

public class ScenarioExecutor {
  
    private static final Random random = new Random();

    public static void main(String[] args) {
        // Create ProductService instance
        ProductService productService = new ProductService();

        // Loop to simulate actions for 10 users
        for (int userId = 1; userId <= 10; userId++) {
            executeScenario(productService, userId);
        }
    }

    private static void executeScenario(ProductService productService, int userId) {
        System.out.println("\nExecuting scenario for User " + userId + ":");

        // Simulate actions for each user
        switch (userId) {
            case 1:
                scenario1(productService, userId);
                break;
            case 2:
                scenario2(productService, userId);
                break;
            case 3:
                scenario3(productService, userId);
                break;
            case 4:
                scenario4(productService, userId);
                break;
            case 5:
                scenario5(productService, userId);
                break;
            case 6:
                scenario6(productService, userId);
                break;
            case 7:
                scenario7(productService, userId);
                break;
            case 8:
                scenario8(productService, userId);
                break;
            case 9:
                scenario9(productService, userId);
                break;
            case 10:
                scenario10(productService, userId);
                break;
            default:
                System.out.println("Invalid user ID");
        }
    }

    private static void scenario1(ProductService productService, int userId) {
        productService.displayAllProducts(String.valueOf(userId));
        productService.addProduct(new Product("P101", "Laptop", 1200.0, LocalDate.parse("2025-12-31")), String.valueOf(userId));
        productService.deleteProduct("P101", String.valueOf(userId));
        productService.updateProduct(new Product("P101", "Laptop Pro", 1300.0, LocalDate.parse("2026-12-31")), String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
    }

    private static void scenario2(ProductService productService, int userId) {
        productService.fetchProduct("P101", String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.deleteProduct("P101", String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.addProduct(new Product("P102", "Smartphone", 1300.0, LocalDate.parse("2024-11-30")), String.valueOf(userId));
    }

    private static void scenario3(ProductService productService, int userId) {
        productService.displayAllProducts(String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.addProduct(new Product("P103", "Headphones", 150.0, LocalDate.parse("2025-06-30")), String.valueOf(userId));
        productService.fetchProduct("P103", String.valueOf(userId));
        productService.updateProduct(new Product("P103", "Headphones Pro", 200.0, LocalDate.parse("2025-06-30")), String.valueOf(userId));
    }

    private static void scenario4(ProductService productService, int userId) {
        productService.deleteProduct("P101", String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.addProduct(new Product("P104", "Keyboard", 50.0, LocalDate.parse("2026-01-01")), String.valueOf(userId));
        productService.updateProduct(new Product("P104", "Mechanical Keyboard", 100.0, LocalDate.parse("2026-01-01")), String.valueOf(userId));
    }

    private static void scenario5(ProductService productService, int userId) {
        productService.addProduct(new Product("P105", "Mouse", 20.0, LocalDate.parse("2025-05-31")), String.valueOf(userId));
        productService.fetchProduct("P105", String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.deleteProduct("P105", String.valueOf(userId));
    }

    private static void scenario6(ProductService productService, int userId) {
        productService.fetchProduct("P106", String.valueOf(userId));
        productService.updateProduct(new Product("P106", "Monitor", 350.0, LocalDate.parse("2025-09-30")), String.valueOf(userId));
        productService.deleteProduct("P106", String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
    }

    private static void scenario7(ProductService productService, int userId) {
        productService.displayAllProducts(String.valueOf(userId));
        productService.addProduct(new Product("P107", "Tablet", 400.0, LocalDate.parse("2024-08-01")), String.valueOf(userId));
        productService.deleteProduct("P107", String.valueOf(userId));
        productService.updateProduct(new Product("P107", "Tablet Pro", 500.0, LocalDate.parse("2025-08-01")), String.valueOf(userId));
    }

    private static void scenario8(ProductService productService, int userId) {
        productService.updateProduct(new Product("P108", "Speaker", 90.0, LocalDate.parse("2025-04-01")), String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.addProduct(new Product("P108", "Bluetooth Speaker", 120.0, LocalDate.parse("2026-04-01")), String.valueOf(userId));
        productService.deleteProduct("P108", String.valueOf(userId));
    }

    private static void scenario9(ProductService productService, int userId) {
        productService.displayAllProducts(String.valueOf(userId));
        productService.fetchProduct("P109", String.valueOf(userId));
        productService.deleteProduct("P109", String.valueOf(userId));
        productService.updateProduct(new Product("P109", "Printer", 200.0, LocalDate.parse("2025-07-15")), String.valueOf(userId));
    }

    private static void scenario10(ProductService productService, int userId) {
        productService.fetchProduct("P110", String.valueOf(userId));
        productService.displayAllProducts(String.valueOf(userId));
        productService.addProduct(new Product("P111", "Camera", 500.0, LocalDate.parse("2026-02-28")), String.valueOf(userId));
        productService.deleteProduct("P111", String.valueOf(userId));
        productService.addProduct(new Product("P112", "Camera", 500.0, LocalDate.parse("2026-02-28")), String.valueOf(userId));
        productService.addProduct(new Product("P113", "Cam2", 1000.0, LocalDate.parse("2026-02-28")), String.valueOf(userId));
        productService.addProduct(new Product("P114", "Cam3", 3000.0, LocalDate.parse("2026-02-28")), String.valueOf(userId));
    }
}