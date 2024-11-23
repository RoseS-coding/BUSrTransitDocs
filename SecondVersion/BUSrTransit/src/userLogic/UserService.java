package userLogic;

import java.util.HashMap;
import java.util.Map;

public class UserService {
	private Map<String, User> userDatabase;
	
	public UserService() {
		userDatabase = new HashMap<>();
		initializeDatabase();
	}
	
	private void initializeDatabase() {
		userDatabase.put("1", new User("1", "alice", "password123", "Alice", "aliceemail", UserType.ADMIN));
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
}


