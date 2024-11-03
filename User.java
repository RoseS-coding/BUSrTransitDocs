package logic;

public class User {
	private String userID;
	private String name;
	private String email;
	private UserType userType;
	
	public User(String i, String name, String email, UserType userType) {
		this.userID = i;
		this.name = name;
		this.email = email;
		this.userType = userType;
	}
	
	public String getUserID() {
		return userID;
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
