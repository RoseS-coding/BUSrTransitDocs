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
		
		
		User user = userService.validateUser(username, password);
		if (user != null) {
			System.out.println("User found: " + user);
		} else {
			System.out.println("User not found.");
		}
		
		scanner.close();
	}
}
