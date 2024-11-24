package visualLogic;

import roleActions.AdminAction;
import routeLogic.Route;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RouteManagementFrame extends JFrame {
	private AdminAction adminAction;
	private JTextField routeIdField;
    private JTextField busIdField;
    private JTextField startLocationField;
    private JTextField endLocationField;
    private JTextField departTimeField;
    private JTextField arriveTimeField;
    private JTextField capacityField;
    private JTextField driverIdField;

    public RouteManagementFrame(AdminAction adminAction) {
        this.adminAction = adminAction;
        setupUI();
    }

    private void setupUI() {
        setTitle("Route Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        // Create input fields
        routeIdField = new JTextField();
        busIdField = new JTextField();
        startLocationField = new JTextField();
        endLocationField = new JTextField();
        departTimeField = new JTextField();
        arriveTimeField = new JTextField();
        capacityField = new JTextField();
        driverIdField = new JTextField();

        // Add components to the frame
        add(new JLabel("Route ID:"));
        add(routeIdField);
        add(new JLabel("Bus ID:"));
        add(busIdField);
        add(new JLabel("Start Location:"));
        add(startLocationField);
        add(new JLabel("End Location:"));
        add(endLocationField);
        add(new JLabel("Depart Time:"));
        add(departTimeField);
        add(new JLabel("Arrival Time:"));
        add(arriveTimeField);
        add(new JLabel("Capacity:"));
        add(capacityField);
        add(new JLabel("Driver ID:"));
        add(driverIdField);

        // Add buttons for actions
        JButton addButton = new JButton("Add Route");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoute();
            }
        });

        JButton updateButton = new JButton("Update Route");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRoute();
            }
        });

        JButton deleteButton = new JButton("Delete Route");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRoute();
            }
        });
        
        JButton viewButton = new JButton("View Routes");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRoutes();
            }
        });

 // Add buttons to the frame
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);
    }

    private void addRoute() {
        String routeId = routeIdField.getText();
        String busId = busIdField.getText();
        String startLocation = startLocationField.getText();
        String endLocation = endLocationField.getText();
        String departTime = departTimeField.getText();
        String arriveTime = arriveTimeField.getText();
        String capacity = capacityField.getText();
        String driverId = driverIdField.getText();

        Route newRoute = new Route(routeId, busId, startLocation, endLocation, departTime, arriveTime, capacity, driverId);
        adminAction.addRoute(newRoute);
        JOptionPane.showMessageDialog(this, "Route added successfully!");
    }

    private void updateRoute() {
        String routeId = routeIdField.getText();
        String busId = busIdField.getText();
        String startLocation = startLocationField.getText();
        String endLocation = endLocationField.getText();
        String departTime = departTimeField.getText();
        String arriveTime = arriveTimeField.getText();
        String capacity = capacityField.getText();
        String driverId = driverIdField.getText();

        adminAction.updateRoute(routeId, busId, startLocation, endLocation, departTime, arriveTime, capacity, driverId);
        JOptionPane.showMessageDialog(this, "Route updated successfully!");
    }

    private void deleteRoute() {
        String routeId = routeIdField.getText();
        adminAction.deleteRoute(routeId);
        JOptionPane.showMessageDialog(this, "Route deleted successfully!");
    }
    
    private void viewRoutes() {
        StringBuilder routesList = new StringBuilder();
        for (Route route : adminAction.getAllRoutes()) {
            routesList.append(route.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, routesList.length() > 0 ? routesList.toString() : "No routes available.");
    }
}
