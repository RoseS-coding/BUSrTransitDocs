package visualLogic;

import roleActions.AdminAction;
import routeLogic.*;
import userLogic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class AdminActionFrame extends JFrame {
	private AdminAction adminAction;
	private BusRouteManager busRouteManager;
    public AdminActionFrame(AdminAction adminAction, BusRouteManager busRouteManager) {
        this.adminAction = adminAction; // Store the AdminAction instance
        this.busRouteManager = busRouteManager;
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
        JButton messageButton = new JButton("Messages");
        
        // Add buttons to the frame
        routeManagementButton.setBounds(190, 300, 200, 60);
        userManagementButton.setBounds(440, 300,200,60);
        driverManagementButton.setBounds(690, 300, 200, 60);
        messageButton.setBounds(315, 400, 200, 60);
        exitButton.setBounds(565, 400, 200, 60);
        
        contentPane.add(routeManagementButton);
        contentPane.add(userManagementButton);
        contentPane.add(driverManagementButton);
        contentPane.add(messageButton);
        contentPane.add(exitButton);

    }
    
    private void openRouteManagementFrame() {
        // Create and display the RouteManagementFrame with adminAction
        RouteManagementFrame routeManagementFrame = new RouteManagementFrame(adminAction);
        routeManagementFrame.setVisible(true);
    }

    private void openUserManagementFrame() {
        // Create and display the ManageUsersFrame with adminAction
        ManageUsersFrame userManagementFrame = new ManageUsersFrame(adminAction);
        userManagementFrame.setVisible(true);
    }
    
    private void openManageDriversFrame() {
        // Create and display the ManageDriversFrame with adminAction
        ManageDriversFrame manageDriversFrame = new ManageDriversFrame(adminAction);
        manageDriversFrame.setVisible(true);
    }

}
