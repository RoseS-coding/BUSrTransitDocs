package userLogic;

public class User {
	private String userID;
	private String username;
	private String password; //TODO change this to password hash and calculate password hash for check
	private String name;
	private String email;
	private UserType userType;
	
	public User(String i, String username, String password, String name, String email, UserType userType) {
		this.userID = i;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.userType = userType;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public UserType getUserType() {
		return userType;
	}
	
	@Override 
	public String toString() {
		return "User: " + userID + ", " + name + ", " + email + ", " + userType;
	}
}
