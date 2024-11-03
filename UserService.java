package logic;

import java.util.HashMap;
import java.util.Map;

public class UserService {
	private Map<String, User> userDatabase;
	
	public UserService() {
		userDatabase = new HashMap<>();
		initializeDatabase();
	}
	
	private void initializeDatabase() {
		userDatabase.put("1", new User("1", "Alice", "aliceemail", UserType.USER));
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public User getUserByID(String userID) {
		return userDatabase.get(userID);
	}
}


