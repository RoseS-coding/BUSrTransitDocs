package routeLogic;

public class Route {
	private String busID;
	private String routeID;
	private String departLocation;
	private String arriveLocation;
	private String departTime;
	private String arriveTime; //TODO change this to password hash and calculate password hash for check
	private String capacity;
	//driver will be passed in
	private String driver;
	
	public Route(String routeID, String i, String departTime, String arriveTime, String capacity, String driver, String departLocation, String arriveLocation) {
		this.routeID = routeID;
		this.busID = i;
		this.departTime = departTime;
		this.arriveTime = arriveTime;
		this.capacity = capacity;
		this.driver = driver;
		this.departLocation = departLocation;
		this.arriveLocation = arriveLocation;
	}
	
	public String getRouteID() {
		return routeID;
	}
	

	public void setRouteID(String routeID) {
		this.routeID = routeID;
	}
	
	
	
	public String getBusID() {
		return busID;
	}
	public void setBusID(String busID) {
		this.busID = busID;
	}
	
	public String getDepartLocation() {
		return departLocation;
	}
	public void setDepartLocation(String departLocation) {
		this.departLocation = departLocation;
	}
	
	public String getArriveLocation() {
		return arriveLocation;
	}
	public void setArriveLocation(String arriveLocation) {
		this.arriveLocation = arriveLocation;
	}
	
	public String getDepartTime() {
		return departTime;
	}
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}
	
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	//TODO define toString method
	@Override
	public String toString() {
		return "Route Id:" + routeID + " Bus ID:" + busID + " departs from:" + departLocation + " arrives at:" + arriveLocation + " departs at:" + departTime + " arrives at:" + arriveTime + " capacity:" + capacity + " driver:" + driver;
	}
}
