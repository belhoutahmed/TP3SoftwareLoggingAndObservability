package MAIN;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.parser.JsonLogEventParser;
import org.apache.logging.log4j.core.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class LogParser {
	public static Map<String, UserProfile> profiles = new HashMap<>();
    private static final String LOG_FILE = "logs/user_activity.log";

    public void createProfiles() throws ParseException {
       

        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse the log entry
                JsonLogEventParser parser = new JsonLogEventParser();
                LogEvent event = parser.parseFrom(line.getBytes());

                // Extract user and action details from the log message
                String message = event.getMessage().getFormattedMessage();
                String userId = extractUserId(message); // Implement logic to extract user ID
                String action = extractAction(message); // Implement logic to extract action

                // Record the action in the user profile
                profiles.computeIfAbsent(userId, id -> new UserProfile(id))
                        .recordAction(action);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort profiles by number of reads and writes
        saveProfilesToJson(profiles, "readsprofile.json", 0); // Save by most reads
        saveProfilesToJson(profiles, "writesprofile.json", 1); // Save by most writes
        saveProfilesToJson(profiles, "expensiveprofile.json", 2); 
    }

    private String extractAction(String message) {
        if (message.contains("viewed") || message.contains("fetched")) {
            return "read";
        } else if (message.contains("added") || message.contains("deleted") || message.contains("updated")) {
            return "write";
        }
        return "unknown"; // Default if no action found
    }

    private String extractUserId(String message) {
        Pattern pattern = Pattern.compile("User: (\\S+)");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "unknown"; // Default if no user found
    }

    private void saveProfilesToJson(Map<String, UserProfile> profiles, String fileName, int sortedby) {
        // Sort the profiles based on read or write counts
        List<UserProfile> sortedProfiles = profiles.values().stream()
                .sorted((profile1, profile2) -> {
                    if (sortedby==0) {
                        return Integer.compare(profile2.getReadCount(), profile1.getReadCount()); // Sort by reads
                    } else {
                    	if(sortedby==1)
                        return Integer.compare(profile2.getWriteCount(), profile1.getWriteCount()); // Sort by writes
                    	else return Integer.compare(profile2.getExpensiveProductSearches(), profile1.getExpensiveProductSearches()); // Sort by writes;
                    }
                })
                .collect(Collectors.toList());

        // Save sorted profiles to JSON
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            // Debugging: Check if profiles are not empty
            if (sortedProfiles.isEmpty()) {
                System.out.println("No profiles to save.");
            } else {
                System.out.println("Saving profiles to " + fileName + "...");
            }

            // Write the profiles to a JSON file
            File outputFile = new File(fileName);
            mapper.writeValue(outputFile, sortedProfiles);

            // Debugging: Check if file was created
            if (outputFile.exists()) {
                System.out.println("File " + fileName + " created successfully.");
            } else {
                System.out.println("Failed to create " + fileName + ".");
            }

        } catch (IOException e) {
            System.out.println("Error while saving profiles to JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
