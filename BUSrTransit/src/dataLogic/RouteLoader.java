package dataLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import routeLogic.*;

public class RouteLoader {
    private BusRouteManager busRouteManager;

    public RouteLoader(BusRouteManager busRouteManager) {
        this.busRouteManager = busRouteManager;
    }
    public void loadRoutesFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(","); // Split the line into columns

                // Check if the row has the correct number of columns
                if (row.length != 8) {
                    System.out.println("Skipping invalid row: " + line);
                    continue; // Skip rows that don't have the correct number of columns
                }
                // Assuming the CSV columns are in the following order:
                // routeID, busID, departTime, arriveTime, capacity, driver, departLocation, arriveLocation
                String routeID = row[0];
                String busID = row[1];
                String departTime = row[2];
                String arriveTime = row[3];
                String capacity = row[4];
                String driver = row[5];
                String departLocation = row[6];
                String arriveLocation = row[7];

                // Create a new Route object
                Route route = new Route(routeID, busID, departTime, arriveTime, capacity, driver, departLocation, arriveLocation);
                
                // Add the route to the BusRouteManager
                busRouteManager.addRoute(route);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}