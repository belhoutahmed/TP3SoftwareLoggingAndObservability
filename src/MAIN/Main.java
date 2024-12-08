package MAIN;

import java.time.LocalDate;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.parser.ParseException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        UserService userService = new UserService();
        LogParser logParser = new LogParser();  // Create LogParser instance
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Product Management");
            System.out.println("2. User Management");
            System.out.println("3. Parse logs and generate user profiles");  // New option
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    manageProducts(productService, scanner);
                    break;
                case "2":
                    manageUsers(userService, scanner);
                    break;
                case "3":
                    // Call LogParser to create user profiles
                    try {
                        logParser.createProfiles();
                        System.out.println("User profiles created from logs.");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageProducts(ProductService productService, Scanner scanner) {
    	 System.out.print("Enter your user ID for product management: ");
    	    String userId = scanner.nextLine(); // Get the user ID for this session
        String command;
        while (true) {
            System.out.println("\nProduct Management:");
            System.out.println("1. Display all products");
            System.out.println("2. Add new product");
            System.out.println("3. Fetch product by ID");
            System.out.println("4. Delete product by ID");
            System.out.println("5. Update product");
            System.out.println("6. Back to main menu");
            System.out.print("Enter your choice: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    productService.displayAllProducts(userId);
                    break;
                case "2":
                    System.out.print("Enter product ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter expiration date (yyyy-mm-dd): ");
                    LocalDate expirationDate = LocalDate.parse(scanner.nextLine());
                    Product product = new Product(id, name, price, expirationDate);
                    productService.addProduct(product,userId);
                    break;
                case "3":
                    System.out.print("Enter product ID: ");
                    id = scanner.nextLine();
                    productService.fetchProduct(id,userId);
                    break;
                case "4":
                    System.out.print("Enter product ID: ");
                    id = scanner.nextLine();
                    productService.deleteProduct(id,userId);
                    break;
                case "5":
                    System.out.print("Enter product ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter new product name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new product price: ");
                    price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter new expiration date (yyyy-mm-dd): ");
                    expirationDate = LocalDate.parse(scanner.nextLine());
                    Product updatedProduct = new Product(id, name, price, expirationDate);
                    productService.updateProduct(updatedProduct,userId);
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageUsers(UserService userService, Scanner scanner) {
        String command;
        while (true) {
            System.out.println("\nUser Management:");
            System.out.println("1. Display all users");
            System.out.println("2. Add new user");
            System.out.println("3. Fetch user by ID");
            System.out.println("4. Delete user by ID");
            System.out.println("5. Update user");
            System.out.println("6. Back to main menu");
            System.out.print("Enter your choice: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    userService.displayAllUsers();
                    break;
                case "2":
                    System.out.print("Enter user ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter user email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter user password: ");
                    String password = scanner.nextLine();
                    User user = new User(id, name, age, email, password);
                    userService.addUser(user);
                    break;
                case "3":
                    System.out.print("Enter user ID: ");
                    id = scanner.nextLine();
                    userService.fetchUser(id);
                    break;
                case "4":
                    System.out.print("Enter user ID: ");
                    id = scanner.nextLine();
                    userService.deleteUser(id);
                    break;
                case "5":
                    System.out.print("Enter user ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter new user name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new user age: ");
                    age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new user email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter new user password: ");
                    password = scanner.nextLine();
                    User updatedUser = new User(id, name, age, email, password);
                    userService.updateUser(updatedUser);
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
