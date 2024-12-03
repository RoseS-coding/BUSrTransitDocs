package userLogic;

import java.util.Scanner;
import roleActions.*;
import visualLogic.*;
import routeLogic.*;
import commLogic.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.ArrayList;

import dataLogic.RouteLoader;
public class Main {
	public static void main(String args[]) {
		UserService userService = new UserService("src/Data/testuserinput.csv");
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<Message> messages = new ArrayList<>();
		MessageStorage messageStorage = new MessageStorage("src/Data/messages.csv");
		messages.addAll(messageStorage.loadMessages());
		
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

            LoginFrame loginFrame = new LoginFrame(userService, "src/Data/testuserinput.csv", userService.getUserDatabase(), messages, messageStorage);

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
	
	public static void executeUserAction(User user, UserService userService, ArrayList<Message> messages, MessageStorage messageStorage) {
		if (user == null) {

            System.out.println("User  is null. Cannot execute action.");

            return;

        }

		BusRouteManager busRouteManager = new BusRouteManager();
		RouteLoader routeLoader = new RouteLoader(busRouteManager);
		
		String routeFile = "src/Data/routes.csv";
		routeLoader.loadRoutesFromCSV(routeFile);
		
        switch (user.getUserType()) {
            case ADMIN:
                AdminAction adminAction = new AdminAction(userService);
                AdminActionFrame adminActionFrame = new AdminActionFrame(adminAction, busRouteManager, messages);
                adminActionFrame.setVisible(true);
                break;
            case USER:
                UserAction userAction = new UserAction();
                UserFrame userFrame = new UserFrame(busRouteManager);
                userFrame.setVisible(true);
                // Open user interface or perform user actions here
                break;
            case DRIVER:
                DriverAction driverAction = new DriverAction();
                DriverFrame driverFrame = new DriverFrame(user, busRouteManager, messages, messageStorage);
                driverFrame.setVisible(true);
                // Open driver interface or perform driver actions here
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + user.getUserType());

        }
        messageStorage.saveMessages(messages);
	}
}
