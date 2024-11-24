package visualLogic;

import roleActions.AdminAction; // Ensure this import is correct
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageDriversFrame extends JFrame {
    private AdminAction adminAction;
    // Input fields for driver management
    private JTextField driverIdField;
    private JTextField busRouteField;

    public ManageDriversFrame(AdminAction adminAction) {
        this.adminAction = adminAction; // Store the AdminAction instance
        setupUI();
    }

    private void setupUI() {
        setTitle("Manage Drivers");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        // Create input fields for driver management
        driverIdField = new JTextField();
        busRouteField = new JTextField();

        // Add components to the frame
        add(new JLabel("Driver ID:"));
        add(driverIdField);
        add(new JLabel("Bus Route:"));
        add(busRouteField);

        // Button to assign driver to bus route
        JButton assignButton = new JButton("Assign Driver");
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignDriverToRoute();
            }
        });

        // Button o view driver info
        JButton viewInfoButton = new JButton("View Driver Info");
        viewInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDriverInfo();
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
        add(assignButton);
        add(viewInfoButton);
        add(exitButton);
    }

    private void assignDriverToRoute() {
        String driverId = driverIdField.getText();
        String busRoute = busRouteField.getText();

        // Logic to assign the driver to the bus route
        // This would typically involve calling a method in adminAction
        adminAction.assignDriverToRoute(driverId, busRoute);
        JOptionPane.showMessageDialog(this, "Driver assigned to route successfully!");
    }



    private void viewDriverInfo() {
        String driverId = driverIdField.getText();

        // Logic to retrieve and display driver information
        String driverInfo = adminAction.getDriverInfo(driverId);
        JOptionPane.showMessageDialog(this, driverInfo);
    }
}