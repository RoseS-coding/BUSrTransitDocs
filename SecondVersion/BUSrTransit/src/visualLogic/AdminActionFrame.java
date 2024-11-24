package visualLogic;

import roleActions.AdminAction;
import routeLogic.Route;
import userLogic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminActionFrame extends JFrame {
	private AdminAction adminAction;

    public AdminActionFrame(AdminAction adminAction) {
        this.adminAction = adminAction; // Store the AdminAction instance
        setupUI();
    }

    private void setupUI() {
        setTitle("Admin Action");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        // Button to manage routes
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

        // Add buttons to the frame
        add(routeManagementButton);
        add(userManagementButton);
        add(driverManagementButton);
        add(exitButton);
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
