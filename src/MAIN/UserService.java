package MAIN;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class UserService {
    private final MongoCollection<Document> collection;

    public UserService() {
        // Connect to MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb+srv://ahmed:13tdBBSD7FJraZC0@cluster0.q0t3l.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"); // Update with your connection string
        MongoDatabase database = mongoClient.getDatabase("userDB"); // Replace with your database name
        this.collection = database.getCollection("users"); // Replace with your collection name
    }

    public void displayAllUsers() {
        collection.find().forEach(doc -> System.out.println(doc.toJson()));
    }

    public void addUser(User user) {
        try {
            Document doc = new Document("id", user.getId())
                    .append("name", user.getName())
                    .append("age", user.getAge())
                    .append("email", user.getEmail())
                    .append("password", user.getPassword());
            collection.insertOne(doc);
            System.out.println("User added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public void fetchUser(String id) {
        try {
            Document query = new Document("id", id);
            Document user = collection.find(query).first();
            if (user != null) {
                System.out.println(user.toJson());
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            System.out.println("Error fetching user: " + e.getMessage());
        }
    }

    public void deleteUser(String id) {
        try {
            Document query = new Document("id", id);
            collection.deleteOne(query);
            System.out.println("User deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    public void updateUser(User user) {
        try {
            Document query = new Document("id", user.getId());
            Document update = new Document("$set", new Document("name", user.getName())
                    .append("age", user.getAge())
                    .append("email", user.getEmail())
                    .append("password", user.getPassword()));
            collection.updateOne(query, update);
            System.out.println("User updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }
}
