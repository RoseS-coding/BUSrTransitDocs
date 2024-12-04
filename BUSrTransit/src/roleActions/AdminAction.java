package roleActions;

//DONT FUCK WITH THE ORDER OF THE FUNCTION PARAMETERS. THEY ARE VERY FRAGILE!

import routeLogic.Route;
import routeLogic.BusRouteManager;
import java.util.Scanner;
import java.util.List;
import userLogic.*;
import java.util.ArrayList;
import java.util.*;
import userLogic.*;

public class AdminAction implements PersonAction {
	
	private BusRouteManager busRouteManager;
	private List<User> users;
	private Map<String, Driver> drivers;
	private Map<String, String> driverAssignments;
	private UserService userService;
	
    public AdminAction(UserService userService) {
        this.busRouteManager = new BusRouteManager();
        this.users = new ArrayList<>();
        this.drivers = new HashMap<>();
        this.driverAssignments = new HashMap<>();
        this.userService = userService;
    }

    // Method to add a route
    public void addRoute(Route newRoute) {
        busRouteManager.addRoute(newRoute);
    }

    // Method to update a route
    public void updateRoute(String routeId, String busID, String startLocation, String endLocation, 
                            String departTime, String arriveTime, String capacity, String driver) {
        busRouteManager.updateRoute(routeId, busID, startLocation, endLocation, departTime, arriveTime, capacity, driver);
    }

    // Method to delete a route
    public void deleteRoute(String routeId) {
        busRouteManager.deleteRoute(routeId);
    }

    // Method to get the BusRouteManager (for viewing routes)
    public BusRouteManager getBusRouteManager() {
        return busRouteManager;
    }

    // This method can be removed as we are not using console input anymore
    @Override
    public void performAction() {
        // This method is no longer needed since actions are performed through the GUI
        throw new UnsupportedOperationException("This method is not used in the GUI version.");
    }

    public void viewRoutes() {
        System.out.println("Current Bus Routes:");
        for (Route route : busRouteManager.getRoutes()) {
            System.out.println(route);
        }
    }
    
    public List<Route> getAllRoutes() {
        return busRouteManager.getRoutes();
    }
    
    public void addUser (User user) {
        users.add(user);
        System.out.println("User  added: " + user.toString());
        System.out.println("Current user count: " + users.size());
    }

    // Method to update a user
    public void updateUser (String userId, String username, String password, String name, String email, UserType userType) {
        for (User  user : users) {
            if (user.getUserID().equals(userId)) { // Assuming User class has a getUser Id method
                user.setUsername(username); // Assuming User class has a setUsername method
                user.setPassword(password); // Assuming User class has a setPassword method
                user.setName(name); // Assuming User class has a setName method
                user.setEmail(email); // Assuming User class has a setEmail method
                user.setUserType(userType); // Assuming User class has a setUser Type method
                break;
            }
        }
    }

    // Method to delete a user
    public void deleteUser (String userId) {
        users.removeIf(user -> user.getUserID().equals(userId)); // Remove user by userId
    }

    // Method to get all users
    public List<User> getUsers() {
        return userService.getAllUsers(); // Return a copy of the user list
   }
     // Method to add a driver
    public void addDriver(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    // Method to assign a driver to a bus route
    public void assignDriverToRoute(String driverId, String busRoute) {
        if (drivers.containsKey(driverId)) {
            driverAssignments.put(driverId, busRoute);
            System.out.println("Driver " + driverId + " assigned to route " + busRoute);
        } else {
            System.out.println("Driver ID not found: " + driverId);
        }
    }
    
    public Map<String, Driver> getDriverDatabase() {
    	return userService.getDriverDatabase();
    }
    
    // Method to get driver information
    public String getDriverInfo(String driverId) {
        Driver driver = drivers.get(driverId);
        if (driver != null) {
            return "Driver ID: " + driver.getId() + "\n" +
                   "Name: " + driver.getName() + "\n" +
                   "Assigned Route: " + driverAssignments.getOrDefault(driverId, "Not Assigned");
        } else {
            return "Driver ID not found: " + driverId;
        }
    }

    // Method to get all drivers
    public List<Driver> getAllDrivers() {
        return new ArrayList<>(drivers.values());
    }

    // Method to remove a driver
    public void removeDriver(String driverId) {
        if (drivers.containsKey(driverId)) {
            drivers.remove(driverId);
            driverAssignments.remove(driverId); // Remove any assignments for the driver
            System.out.println("Driver " + driverId + " has been removed.");
        } else {
            System.out.println("Driver ID not found: " + driverId);
        }
    }
    
    public String getAllDriversInfo() {
        StringBuilder driverInfo = new StringBuilder();
        Map<String, Driver> drivers = getDriverDatabase(); // Assuming you have a method to get the driver database
        for (Driver driver : drivers.values()) {
            driverInfo.append("ID: ").append(driver.getId())
                       .append(", Name: ").append(driver.getName())
                       .append("\n");
        }

        return driverInfo.length() > 0 ? driverInfo.toString() : "No drivers available.";
    }
	
	/** ADMIN actions
	 *  Create and change routes
	 *  Create and delete drivers
	 *  Search all user accounts and create/delete
	 *  Update maintenance status of buses
	 *  Manage driver schedules
	 */
	
	
}
