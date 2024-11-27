package routeLogic;

import java.util.ArrayList;
import java.util.List;

public class BusRouteManager {
	private List<Route> routes;
	
	public BusRouteManager() {
		this.routes = new ArrayList<>();
	}
	
	public void addRoute(Route route) {
		routes.add(route);
		System.out.println("Route added: " + route);
	}
	
	public void updateRoute(String routeID, String busID, String departLocation, String arriveLocation, String departTime, String arriveTime, String capacity, String driver) {
		for (Route route: routes) {
			if (route.getRouteID().equals(routeID)) {
				route.setBusID(busID);
				route.setDepartLocation(departLocation);
				route.setArriveLocation(arriveLocation);
				route.setDepartTime(departTime);
				route.setArriveTime(arriveTime);
				route.setCapacity(capacity);
				route.setDriver(driver);
				//pass in parameters, then set here
				System.out.println("Route updated: " + route);
				return;
			}
		}
		System.out.println("Route not found with ID: " + routeID);
	}
	
	public void deleteRoute(String routeID) {
		routes.removeIf(route -> route.getRouteID().equals(routeID));
		System.out.println("Route deleted with ID: " + routeID);
	}
	
	public List<Route> getRoutes() {
		return routes;
	}
	
}
