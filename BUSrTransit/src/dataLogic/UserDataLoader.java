package dataLogic;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import userLogic.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import userLogic.*;
import dataLogic.*;

public class UserDataLoader {	
	private String csvFilePath;
	
	public UserDataLoader(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}
	// Method to retrieve the next user ID
	public int getNextUserId(Map<String, User> userDatabase) {
	    int maxId = 0;
	
	    for (User user : userDatabase.values()) {
	        int currentId = Integer.parseInt(user.getUserID());
	        if (currentId > maxId) {
	            maxId = currentId;
	        }
	    }
	    return maxId + 1; // Return the next available ID
	}
	
	// Method to save a user to the CSV file
	public void saveUserToCSV(User user) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
	        String newUserEntry = String.join(",", user.getUserID(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getUserType().toString());
	        writer.write("\n");
	        writer.write(newUserEntry);
	        writer.newLine();
	    } catch (IOException e) {
	        System.err.println("Error saving user: " + e.getMessage());
	    }
	}
	// Method to load users from the CSV file into a provided Map<String, User>
	public void loadUsers(Map<String, User> userDatabase) {
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] userFields = line.split(",");
	            if (userFields.length == 6) { // Ensure there are enough fields
	                String userId = userFields[0].trim();
	                String username = userFields[1].trim();
	                String password = userFields[2].trim();
	                String name = userFields[3].trim();
	                String email = userFields[4].trim();
	                UserType userType = UserType.valueOf(userFields[5].trim().toUpperCase()); // Assuming UserType is an enum
	
	                User user = new User(userId, username, password, name, email, userType);
	                userDatabase.put(userId, user); // Add user to the provided database
	            } else {
	                System.err.println("Invalid user data format: " + line);
	            }
	        }
	    } catch (IOException e) {
	        System.err.println("Error loading users from CSV file: " + e.getMessage());
	    } catch (IllegalArgumentException e) {
	        System.err.println("Invalid user type in CSV: " + e.getMessage());
	    }
	}
}
