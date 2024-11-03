package logic;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		UserService userService = new UserService();
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		
		
		User user = logincheck(username, password, userService);
		while (user == null) {
			System.out.print("Enter username: ");
			username = scanner.nextLine();
			System.out.print("Enter password: ");
			password = scanner.nextLine();
			
			
			user = logincheck(username, password, userService);
		}
		
		scanner.close();
	}
	public static User logincheck(String username, String password, UserService userService) {
		User user = userService.validateUser(username, password);
		if (user != null) {
			System.out.println("User found: " + user);
			return user;
		} else {
			System.out.println("User not found.");
			return null;
		}
	}
}
