package userLogic;

import java.util.Scanner;
import roleActions.*;
import visualLogic.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
public class Main {
	public static void main(String args[]) {
		UserService userService = new UserService();
		Scanner scanner = new Scanner(System.in);
		
		//TOOD reformat inputs to be app based
		
		
		/**
		//Initial asking for credentials
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		User user = logincheck(username, password, userService);
		
		//Repeat asking for login info until matching credentials found
		while (user == null) {
			System.out.print("Enter username: ");
			username = scanner.nextLine();
			System.out.print("Enter password: ");
			password = scanner.nextLine();
			
			
			user = logincheck(username, password, userService);
		}
		**/
		SwingUtilities.invokeLater(() -> {

            LoginFrame loginFrame = new LoginFrame(userService);

            loginFrame.setVisible(true);

        });
		
		//executeUserAction(user);
		
		scanner.close();
	}
	
	
	
	
	
	
	//method to validate user information
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
	
	public static void executeUserAction(User user) {
		if (user == null) {

            System.out.println("User  is null. Cannot execute action.");

            return;

        }


        switch (user.getUserType()) {
            case ADMIN:
                AdminAction adminAction = new AdminAction();
                AdminActionFrame adminActionFrame = new AdminActionFrame(adminAction);
                adminActionFrame.setVisible(true);
                break;
            case USER:
                UserAction userAction = new UserAction();
                // Open user interface or perform user actions here
                break;
            case DRIVER:
                DriverAction driverAction = new DriverAction();
                // Open driver interface or perform driver actions here
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + user.getUserType());

        }
	}
}
