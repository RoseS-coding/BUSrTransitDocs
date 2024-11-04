package roleActions;

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
	
	@Override
	public void performAction() {
		System.out.println("Admin action performed");
	}
	
	//Custom admin screen will be opened if user is admin
	
	/** ADMIN actions
	 *  Create and change routes
	 *  Create and delete drivers
	 *  Search all user accounts and create/delete
	 *  Update maintenance status of buses
	 *  Manage driver schedules
	 */
	
	
}
