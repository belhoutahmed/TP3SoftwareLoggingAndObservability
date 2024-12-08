package MAIN;

import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.bson.Document;

public class ProductService {
    private final MongoCollection<Document> collection;
    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private double expensiveProduct=1000;

    public ProductService() {
        // Connect to MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb+srv://ahmed:13tdBBSD7FJraZC0@cluster0.q0t3l.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"); // Update with your connection string
        MongoDatabase database = mongoClient.getDatabase("productDB"); // Replace with your database name
        this.collection = database.getCollection("products"); // Replace with your collection name
    }

    public void displayAllProducts(String userId) {
        collection.find().forEach(doc -> System.out.println(doc.toJson()));
        logger.info("User: {} | Action: viewed_all_products", userId);
    }

    public void addProduct(Product product,String userId) {
        try {
            Document doc = new Document("id", product.getId())
                    .append("name", product.getName())
                    .append("price", product.getPrice())
                    .append("expirationDate", product.getExpirationDate());
            collection.insertOne(doc);
            logger.info("User: {} | Action: added a new product: {}", userId,product.getName());
        } catch (Exception e) {
        	logger.error("Error adding product: {}", e.getMessage());
        }
    }

    public void fetchProduct(String id,String userId) {
        try {
            Document query = new Document("id", id);
            Document product = collection.find(query).first();
            if (product != null) {
                System.out.println(product.toJson());
                double price = product.getDouble("price");
                if (price > expensiveProduct) {
                    // If the product is expensive, log the action and increment the expensive product search
                    logger.info("User: {} | Action: fetched product with ID: {} | This Product is expensive", userId, id);
                    
                    // Find the user profile and increment the expensive product searches
                    UserProfile profile = LogParser.profiles.getOrDefault(userId, new UserProfile(userId));
                    profile.incrementExpensiveProductSearches();
                    LogParser.profiles.put(userId, profile); // Update the profile in the map
                    
                } else {
                    logger.info("User: {} | Action: fetched product with ID: {}", userId, id);
                }
        }} catch (Exception e) {
            logger.error("Error fetching product: {}", e.getMessage());
        }
    }

    public void deleteProduct(String id,String userId) {
        try {
            Document query = new Document("id", id);
            collection.deleteOne(query);
            logger.info("User: {} | Action: deleted product with ID: {}", userId,id);
        } catch (Exception e) {
            logger.error("Error deleting product: {}", e.getMessage());
        }
    }

    public void updateProduct(Product product,String userId) {
        try {
            Document query = new Document("id", product.getId());
            Document update = new Document("$set", new Document("name", product.getName())
                    .append("price", product.getPrice())
                    .append("expirationDate", product.getExpirationDate()));
            collection.updateOne(query, update);
            logger.info("User: {} | Action:  updated product: {}", userId,product.getName());
        } catch (Exception e) {
            logger.error("Error updating product: {}", e.getMessage());
        }
    }
}