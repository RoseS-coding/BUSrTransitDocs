package userLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import dataLogic.*;

public class UserService {
	private Map<String, User> userDatabase;
	private UserDataLoader userDataLoader;
	private Map<String, Driver> driverDatabase;
	
	public UserService(String csvFilePath) {
		this.userDataLoader = new UserDataLoader(csvFilePath);
		this.userDatabase = initializeDatabase();
		this.driverDatabase = createDriverDB();
	}
	
	private Map<String, User> initializeDatabase() {
		Map<String, User> userDatabase = new HashMap<>();
		
		//alice is the set one, she will always work
		userDatabase.put("1", new User("1", "alice", "password123", "Alice", "aliceemail", UserType.ADMIN));
		userDataLoader.loadUsers(userDatabase);
		
		return userDatabase;
	}
	
	private Map<String, Driver> createDriverDB() {
		Map<String, Driver> driverDatabase = new HashMap<>();
		
		for (User user : userDatabase.values()) {
			if (user.getUserType() == UserType.DRIVER) {
				Driver driver = new Driver(user.getUserID(), user.getName());
				driverDatabase.put(driver.getId(), driver);
			}
		}
		
		return driverDatabase;
	}
	
	
	public User getUserByID(String userID) {
		return userDatabase.get(userID);
	}
	
	public User validateUser(String username, String password) {
		for (User user : userDatabase.values()) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public void registerUser (User newUser ) {
        // Check if the user already exists
        if (userDatabase.containsKey(newUser .getUsername())) {
            System.out.println("User  already exists.");
            return; // Or throw an exception
        }

        // Generate a new user ID
        int newUserId = userDataLoader.getNextUserId(userDatabase);
        newUser.setUserID(String.valueOf(newUserId)); // Set the new user ID
        // Add the user to the in-memory database
        userDatabase.put(newUser .getUsername(), newUser );

        // Save the new user to the CSV file
        userDataLoader.saveUserToCSV(newUser);
    }
	
	public Map<String, User> getUserDatabase() {
		return userDatabase;
	}
	
	public List<User> getAllUsers() {
		return new ArrayList<>(userDatabase.values());
	}
	
	public Map<String, Driver> getDriverDatabase() {
		return driverDatabase;
	}
}


