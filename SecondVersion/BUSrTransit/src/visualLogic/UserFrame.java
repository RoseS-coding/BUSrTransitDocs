package visualLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import routeLogic.*;

public class UserFrame extends JFrame {
    private BusRouteManager busRouteManager;
    private SignUpRoutesFrame signUpRoutesFrame;

    public UserFrame(BusRouteManager busRouteManager) {
        this.busRouteManager = busRouteManager;

        // Set up the frame
        setTitle("User  Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Bus Route Management System", SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        // Create buttons for user actions
        JButton viewRoutesButton = new JButton("View Available Routes");
        JButton signUpButton = new JButton("Sign Up for a Route");
        JButton exitButton = new JButton("Exit");
        
        JButton searchRoutesButton = new JButton("Search Routes");

        // Add action listener for the search routes button
        searchRoutesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchRoutesFrame searchRoutesFrame = new SearchRoutesFrame(busRouteManager);
                searchRoutesFrame.setVisible(true);
            }
        });

        // Add action listener for the view routes button
        viewRoutesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAvailableRoutes();
            }
        });

        // Add action listener for the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignUpRoutesFrame();
            }
        });

        // Add action listener for the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
            }
        });

        // Create a panel for buttons and add them
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewRoutesButton);
        buttonPanel.add(signUpButton);
        buttonPanel.add(searchRoutesButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void viewAvailableRoutes() {
        // Create a dialog to display available routes
        StringBuilder routesInfo = new StringBuilder("Available Routes:\n");
        for (Route route : busRouteManager.getRoutes()) {
            routesInfo.append(route.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, routesInfo.toString(), "Available Routes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void openSignUpRoutesFrame() {
        if (signUpRoutesFrame == null || !signUpRoutesFrame.isDisplayable()) {
            signUpRoutesFrame = new SignUpRoutesFrame(busRouteManager);
            signUpRoutesFrame.setVisible(true);
        } else {
            signUpRoutesFrame.toFront(); // Bring the existing frame to the front
        }
    }

    /**
    public static void main(String[] args) {
        // Create an instance of BusRouteManager and add some routes for testing
        BusRouteManager busRouteManager = new BusRouteManager();
        busRouteManager.addRoute(new Route("R001", "B001", "08:00 AM", "09:00 AM", "50", "Driver A", "City Center", "Airport"));
        busRouteManager.addRoute(new Route("R002", "B002", "09:30 AM", "10:30 AM", "40", "Driver B", "Downtown", "Mall"));
        busRouteManager.addRoute(new Route("R003", "B003", "11:00 AM", "12:00 PM", "30", "Driver C", "University", "Train Station"));
        busRouteManager.addRoute(new Route("R004", "B004", "01:00 PM", "02:00 PM", "60", "Driver D", "Park", "Museum"));

        // Create and display the user frame
        UserFrame userFrame = new UserFrame(busRouteManager);
        userFrame.setVisible(true);
    }
    **/
}
