package MAIN;

import java.util.HashMap;
import java.util.Map;

public class UserProfile {
    private String userId;
    private int readCount;
    private int writeCount;
    private int expensiveProductSearches;

    public UserProfile(String userId) {
        this.userId = userId;
        this.readCount = 0;
        this.writeCount = 0;
        this.expensiveProductSearches = 0; 
    }

    public String getUserId() {
        return userId;
    }

    public int getReadCount() {
        return readCount;
    }

    public int getWriteCount() {
        return writeCount;
    }
    public int getExpensiveProductSearches() {
        return expensiveProductSearches;
    }

    public void recordAction(String action) {
        // Increment counters based on the action
        if (action != null) {
            if (action.toLowerCase().contains("read")) {
                readCount++;
            } else if (action.toLowerCase().contains("write") || action.toLowerCase().contains("create") || action.toLowerCase().contains("update")) {
                writeCount++;
            }
        }
    }
    // Method to increment expensive product searches
    public void incrementExpensiveProductSearches() {
        expensiveProductSearches++;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId='" + userId + '\'' +
                ", readCount=" + readCount +
                ", writeCount=" + writeCount +
                ", expensiveProductSearches=" + expensiveProductSearches +
                '}'; 
    }


	
}
