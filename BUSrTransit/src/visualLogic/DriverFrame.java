package visualLogic;

import javax.swing.*;
import java.awt.*;
import userLogic.*;
import routeLogic.*;
import commLogic.*;
import java.util.ArrayList;


public class DriverFrame extends JFrame {
	private BusRouteManager busRouteManager;
	private User loggedInDriver;
	private ArrayList<Message> messages;
	private MessageStorage messageStorage;
	public DriverFrame(User driver, BusRouteManager busRouteManager, ArrayList<Message> messages, MessageStorage messageStorage) {
		setTitle("Driver Dashboard");
		
		this.loggedInDriver = driver;
		this.busRouteManager = busRouteManager;
		this.messages = messages;
		this.messageStorage = messageStorage;
		
		setSize(1080, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel welcomeLabel = new JLabel("Welcome, " + driver.getName() + "!", SwingConstants.CENTER);
		panel.add(welcomeLabel, BorderLayout.NORTH);
		
		JButton performActionButton = new JButton("Perform Driver Action");
		performActionButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(this,  "Driver action performed!");
		});
		panel.add(performActionButton, BorderLayout.SOUTH);
		
		// Button to view assigned routes
        JButton viewRoutesButton = new JButton("View Assigned Routes");
        viewRoutesButton.addActionListener(e -> viewAssignedRoutes());
        panel.add(viewRoutesButton, BorderLayout.WEST);
        
     // Button to send message to admin
        JButton sendMessageButton = new JButton("Send Message to Admin");
        sendMessageButton.addActionListener(e -> sendMessageToAdmin());
        panel.add(sendMessageButton, BorderLayout.EAST);
        
        JButton viewMessagesButton = new JButton("View Messages");
        viewMessagesButton.addActionListener(e -> viewMessages());
        panel.add(viewMessagesButton, BorderLayout.CENTER);
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
            	messageStorage.saveMessages(messages);
                System.exit(0); // Exit the application
            }
        });
        panel.add(exitButton, BorderLayout.SOUTH);

		
		
		add(panel);
	}
	
	private void viewAssignedRoutes() {
        // Logic to retrieve and display assigned routes for the driver
        StringBuilder assignedRoutes = new StringBuilder("Assigned Routes:\n");
        boolean hasRoutes = false;

        for (Route route : busRouteManager.getRoutes()) {
            if (route.getDriver().equals(loggedInDriver.getName())) {
                assignedRoutes.append(route.toString()).append("\n");
                hasRoutes = true;
            }
        }
        if (!hasRoutes) {
            assignedRoutes.append("No assigned routes found.");
        }

        JOptionPane.showMessageDialog(this, assignedRoutes.toString(), "Assigned Routes", JOptionPane.INFORMATION_MESSAGE);
    }
	private void sendMessageToAdmin() {
		String adminName = JOptionPane.showInputDialog(this, "Enter the admin's name:");
        // Check if the admin name is valid
        if (adminName != null && !adminName.trim().isEmpty()) {
            // Prompt for the message content
            String messageContent = JOptionPane.showInputDialog(this, "Enter your message to " + adminName + ":");
            
            if (messageContent != null && !messageContent.trim().isEmpty()) {
                // Display category selection dialog
                MessageCats[] categories = MessageCats.values();
                MessageCats selectedCategory = (MessageCats) JOptionPane.showInputDialog(
                    this,
                    "Select the message category:",
                    "Message Category",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    categories,
                    categories[0] // Default selection
                );

                if (selectedCategory != null) {
                    Message message = new Message(loggedInDriver.getName(), adminName, messageContent, selectedCategory);
                    messages.add(message); // Store the message
                    JOptionPane.showMessageDialog(this, "Message sent to " + adminName + "!");
                } else {
                    JOptionPane.showMessageDialog(this, "Message category selection was cancelled.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Message cannot be empty.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Admin name cannot be empty.");
        }
	}
	
	
	private void viewMessages() {
	    StringBuilder messageList = new StringBuilder("Messages Sent to You:\n");
	    boolean hasMessages = false;

	    for (Message message : messages) {
	        if (message.getRecipient().equals(loggedInDriver.getName())) {
	            messageList.append(message.toString()).append("\n");
	            hasMessages = true;
	        }
	    }
	    
	    if (!hasMessages) {
	        messageList.append("No messages found.");
	    }

	    // Create a JTextArea to display messages
	    JTextArea textArea = new JTextArea(messageList.toString(), 10, 40); // 10 rows, 40 columns
	    textArea.setEditable(false); // Make it read-only
	    JScrollPane scrollPane = new JScrollPane(textArea); // Add scroll functionality

	    // Show the scroll pane in a dialog
	    JOptionPane.showMessageDialog(this, scrollPane, "Your Messages", JOptionPane.INFORMATION_MESSAGE);
	}
}
