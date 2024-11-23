package roleActions;

//DONT FUCK WITH THE ORDER OF THE FUNCTION PARAMETERS. THEY ARE VERY FRAGILE!

import routeLogic.Route;
import routeLogic.BusRouteManager;
import java.util.Scanner;

public class AdminAction implements PersonAction {
	
	private BusRouteManager busRouteManager;

    public AdminAction() {
        this.busRouteManager = new BusRouteManager();
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
	
	/** ADMIN actions
	 *  Create and change routes
	 *  Create and delete drivers
	 *  Search all user accounts and create/delete
	 *  Update maintenance status of buses
	 *  Manage driver schedules
	 */
	
	
}
