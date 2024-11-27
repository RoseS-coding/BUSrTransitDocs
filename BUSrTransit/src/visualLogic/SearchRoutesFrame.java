package visualLogic;

import routeLogic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class SearchRoutesFrame extends JFrame {
    private BusRouteManager busRouteManager;
    private JTextField departLocationField;
    private JTextField arriveLocationField;
    private JTextArea resultsArea;

    public SearchRoutesFrame(BusRouteManager busRouteManager) {
        this.busRouteManager = busRouteManager;

        // Set up the frame
        setTitle("Search Routes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create input fields for departure and arrival locations
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Departure Location:"));
        departLocationField = new JTextField();
        inputPanel.add(departLocationField);

        inputPanel.add(new JLabel("Arrival Location:"));
        arriveLocationField = new JTextField();
        inputPanel.add(arriveLocationField);

        JButton searchButton = new JButton("Search");
        inputPanel.add(searchButton);

        JButton cancelButton = new JButton("Cancel");
        inputPanel.add(cancelButton);

        // Create a text area to display results
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRoutes();
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
            }
        });
    }

    private void searchRoutes() {
        String departLocation = departLocationField.getText().trim();
        String arriveLocation = arriveLocationField.getText().trim();

        // Filter routes based on departure and arrival locations
        List<Route> matchingRoutes = busRouteManager.getRoutes().stream()
                .filter(route -> route.getDepartLocation().equalsIgnoreCase(departLocation) &&
                                 route.getArriveLocation().equalsIgnoreCase(arriveLocation))
                .collect(Collectors.toList());

        // Display results
        if (matchingRoutes.isEmpty()) {
            resultsArea.setText("No routes found for the specified locations.");
        } else {
            StringBuilder results = new StringBuilder("Matching Routes:\n");
            for (Route route : matchingRoutes) {
                results.append(route.toString()).append("\n");
            }
            resultsArea.setText(results.toString());
        }
    }
}
