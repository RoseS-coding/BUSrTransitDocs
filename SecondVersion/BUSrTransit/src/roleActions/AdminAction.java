package roleActions;

//DONT FUCK WITH THE ORDER OF THE FUNCTION PARAMETERS. THEY ARE VERY FRAGILE!

import routeLogic.Route;
import routeLogic.BusRouteManager;
import java.util.Scanner;

public class AdminAction implements PersonAction {
	
	private BusRouteManager busRouteManager;
	private Scanner scanner;
	
	public AdminAction() {
		this.busRouteManager = new BusRouteManager();
		this.scanner = new Scanner(System.in);
	}
	
	
	//performAction needs to be called each time you want to do an action
	@Override
    public void performAction() {
        System.out.println("Admin actions:");
        System.out.println("1. Add Route");
        System.out.println("2. Update Route");
        System.out.println("3. Delete Route");
        System.out.println("4. View Routes");
        System.out.print("Select an action: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addRoute();
                break;
            case 2:
                updateRoute();
                break;
            case 3:
                deleteRoute();
                break;
            case 4:
                viewRoutes();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void addRoute() {
    	System.out.print("Enter Route ID: ");
        String routeId = scanner.nextLine();
        
        System.out.print("Enter Bus ID: ");
        String busID = scanner.nextLine();
        
        System.out.print("Enter Start Location: ");
        String startLocation = scanner.nextLine();
        
        System.out.print("Enter End Location: ");
        String endLocation = scanner.nextLine();
        
        System.out.println("Enter Depart Time: ");
        String departTime = scanner.nextLine();
        
        System.out.println("Enter Arrival Time: ");
        String arriveTime = scanner.nextLine();
        
        System.out.println("Enter Capacity: ");
        String capacity = scanner.nextLine();
        
        System.out.println("Enter Driver ID: ");
        String driver = scanner.nextLine();
        
        Route newRoute = new Route(routeId, busID, departTime, arriveTime, capacity, driver, startLocation, endLocation);
        busRouteManager.addRoute(newRoute);
    }

    public void updateRoute() {
        System.out.print("Enter Route ID to update: ");
        String routeID = scanner.nextLine();
        
        System.out.print("Enter Bus ID: ");
        String busID = scanner.nextLine();
        
        System.out.print("Enter Start Location: ");
        String startLocation = scanner.nextLine();
        
        System.out.print("Enter End Location: ");
        String endLocation = scanner.nextLine();
        
        System.out.println("Enter Depart Time: ");
        String departTime = scanner.nextLine();
        
        System.out.println("Enter Arrival Time: ");
        String arriveTime = scanner.nextLine();
        
        System.out.println("Enter Capacity: ");
        String capacity = scanner.nextLine();
        
        System.out.println("Enter Driver ID: ");
        String driver = scanner.nextLine();
        busRouteManager.updateRoute(routeID, busID, startLocation, endLocation, departTime, arriveTime, capacity, driver);
    }

    public void deleteRoute() {
        System.out.print("Enter Route ID to delete: ");
        String routeId = scanner.nextLine();
        busRouteManager.deleteRoute(routeId);
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
