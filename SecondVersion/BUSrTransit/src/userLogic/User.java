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
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	@Override 
	public String toString() {
		return "User: " + userID + ", " + name + ", " + email + ", " + userType;
	}
}
