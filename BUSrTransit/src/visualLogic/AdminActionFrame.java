package visualLogic;

import roleActions.AdminAction;
import routeLogic.*;
import userLogic.*;
import commLogic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AdminActionFrame extends JFrame {
	private AdminAction adminAction;
	private BusRouteManager busRouteManager;
	private ArrayList<Message> messages;
	private MessageStorage messageStorage;
	private UserService userService;
	private User adminUser;
	
    public AdminActionFrame(User adminUser, AdminAction adminAction, BusRouteManager busRouteManager, MessageStorage messageStorage, ArrayList<Message> messages, UserService userService) {
        this.adminAction = adminAction; // Store the AdminAction instance
        this.busRouteManager = busRouteManager;
        this.messages = messages;
        this.userService = userService;
        this.messageStorage = messageStorage;
        this.adminUser = adminUser;
        setupUI();
    }

    private void setupUI() {
        setTitle("Admin Action");
        setSize(1080, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Transferring to background and loose layout
        final BufferedImage backgroundImage;
        try {
        	backgroundImage = ImageIO.read(getClass().getResource("/Images/Admin Background.png"));
        } catch (IOException e) {
        	System.err.println("Error loading background image: " + e.getMessage());
        	System.exit(1);
        	return;
        }
        
        JPanel contentPane = new JPanel() {
        	@Override
        	protected void paintComponent(Graphics g) {
        		super.paintComponent(g);
        		g.drawImage(backgroundImage, 0, 0, null);
        	}
        };
        
        contentPane.setLayout(null);
        setupComponents(contentPane);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
    }

    private void setupComponents(JPanel contentPane) {
    	JButton routeManagementButton = new JButton("Manage Routes");
        routeManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRouteManagementFrame();
            }
        });

        // Button to manage users
        JButton userManagementButton = new JButton("Manage Users");
        userManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserManagementFrame();
            }
        });
        
     // Button to manage drivers
        JButton driverManagementButton = new JButton("Manage Drivers");
        driverManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openManageDriversFrame();
            }
        });

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
            }
        });

        //Need to add message viewing functionality
        JButton messageButton = new JButton("View Your Messages");
        messageButton.addActionListener(e -> viewMessages());
        
        JButton sendMessageButton = new JButton("Send a messsage");
        sendMessageButton.addActionListener(e -> sendMessageToDriver());
        
        
        // Add buttons to the frame
        routeManagementButton.setBounds(190, 300, 200, 60);
        userManagementButton.setBounds(440, 300,200,60);
        driverManagementButton.setBounds(690, 300, 200, 60);
        messageButton.setBounds(190, 400, 200, 60);
        sendMessageButton.setBounds(440, 400, 200, 60);
        exitButton.setBounds(690, 400, 200, 60);
        
        contentPane.add(routeManagementButton);
        contentPane.add(userManagementButton);
        contentPane.add(driverManagementButton);
        contentPane.add(messageButton);
        contentPane.add(sendMessageButton);
        contentPane.add(exitButton);

    }
    
    private void openRouteManagementFrame() {
        // Create and display the RouteManagementFrame with adminAction
        RouteManagementFrame routeManagementFrame = new RouteManagementFrame(adminAction);
        routeManagementFrame.setVisible(true);
    }

    private void openUserManagementFrame() {
        // Create and display the ManageUsersFrame with adminAction
        ManageUsersFrame userManagementFrame = new ManageUsersFrame(adminAction, userService);
        userManagementFrame.setVisible(true);
    }
    
    private void openManageDriversFrame() {
        // Create and display the ManageDriversFrame with adminAction
        ManageDriversFrame manageDriversFrame = new ManageDriversFrame(adminAction);
        manageDriversFrame.setVisible(true);
    }
    
    private void viewMessages() {
        if (messages.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No messages from drivers.");
        } else {
            StringBuilder messageList = new StringBuilder("Messages from Drivers:\n");
            for (Message message : messages) {
                messageList.append(message.toString()).append("\n"); // Includes category in the output
            }

            JTextArea textArea = new JTextArea(messageList.toString(), 10, 20); // 10 rows, 20 columns
            textArea.setEditable(false); // Make the text area read-only
            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(this, scrollPane, "Driver Messages", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void sendMessageToDriver() {
        String driverName = JOptionPane.showInputDialog(this, "Enter the driver's name:");
        if (driverName != null && !driverName.trim().isEmpty()) {
            String messageContent = JOptionPane.showInputDialog(this, "Enter your message to the driver:");
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
                    Message message = new Message(adminUser.getName(), driverName, messageContent, selectedCategory);
                    messages.add(message); // Store the message
                    JOptionPane.showMessageDialog(this, "Message sent to " + driverName + "!");
                    messageStorage.saveMessages(messages);
                } else {
                    JOptionPane.showMessageDialog(this, "Message category selection was cancelled.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Message cannot be empty.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Driver name cannot be empty.");
        }
    }

}
