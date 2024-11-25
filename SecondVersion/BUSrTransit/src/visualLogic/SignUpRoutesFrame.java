package visualLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import routeLogic.*;


public class SignUpRoutesFrame extends JFrame {
    private JList<Route> routesList;
    private DefaultListModel<Route> routesListModel;
    private BusRouteManager busRouteManager;

    public SignUpRoutesFrame(BusRouteManager busRouteManager) {
        this.busRouteManager = busRouteManager; // Initialize the BusRouteManager
        // Set up the frame
        setTitle("Sign Up for Routes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a list model and JList for displaying routes
        routesListModel = new DefaultListModel<>();
        routesList = new JList<>(routesListModel);
        JScrollPane scrollPane = new JScrollPane(routesList);
        
        // Load available routes from BusRouteManager
        loadAvailableRoutes();

        // Create buttons
        JButton signUpButton = new JButton("Sign Up");
        JButton cancelButton = new JButton("Cancel");

        // Add action listener for the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Route selectedRoute = routesList.getSelectedValue();
                if (selectedRoute != null) {
                    // Logic to sign up for the selected route
                    // For example: routeService.signUpForRoute(selectedRoute);
                    
                    // Show a confirmation message
                    JOptionPane.showMessageDialog(SignUpRoutesFrame.this, "Signed up for route: " + selectedRoute.getRouteID());
                    dispose(); // Close the frame
                } else {
                    JOptionPane.showMessageDialog(SignUpRoutesFrame.this, "Please select a route to sign up for.");
                }
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame without signing up
            }
        });

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(signUpButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadAvailableRoutes() {
        // Fetch available routes from the BusRouteManager
        List<Route> availableRoutes = busRouteManager.getRoutes();

        // Add routes to the list model
        for (Route route : availableRoutes) {
            routesListModel.addElement(route);
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

        // Create and display the sign-up routes frame
        SignUpRoutesFrame signUpFrame = new SignUpRoutesFrame(busRouteManager);
        signUpFrame.setVisible(true);
    }
    **/
}
