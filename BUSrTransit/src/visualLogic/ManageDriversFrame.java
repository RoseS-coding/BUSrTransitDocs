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
        
        JButton viewAllDriversButton = new JButton("View All Drivers");
        viewAllDriversButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllDrivers();
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
        add(viewAllDriversButton);
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
    
    private void viewAllDrivers() {
    	String allDriversInfo = adminAction.getAllDriversInfo(); // Assuming this method exists in AdminAction
        // Create a JTextArea to display the driver information
        JTextArea textArea = new JTextArea(20, 30);
        textArea.setText(allDriversInfo);
        textArea.setEditable(false); // Make it read-only
        // Wrap the JTextArea in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Show the scroll pane in a dialog
        JOptionPane.showMessageDialog(this, scrollPane, "All Drivers", JOptionPane.INFORMATION_MESSAGE);
    }
}
