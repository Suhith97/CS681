package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.util.function.Supplier;

public final class Position{	private final double latitude = 0;
private final double longitude = 0;
private final double altitude = 0;

    //private static ReentrantLock lock = new ReentrantLock();
    
    
    public Position(double newLat, double newLon, double newAlt) {
	// TODO Auto-generated constructor stub
}

	public List<Double> coordinate(){
    
    	List<Double> list = new ArrayList<Double>();
	list.add(latitude);
	list.add(longitude);
	list.add(altitude);

	return list;
    }
	
    public Position change(double newLat, double newLon, double newAlt) {
    
	    return new Position(newLat, newLon, newAlt);
    }

    public double distanceTo(Position anotherPosition) {
   
	   double lat1 = anotherPosition.latitude;
	  double lat2 = latitude; 
	    double dlat = anotherPosition.latitude - latitude;
	    double dlon = anotherPosition.longitude - longitude;
	    
   		double a = Math.pow(Math.sin(dlat / 2), 2)
				+ Math.cos(lat1) * Math.cos(lat2)
				* Math.pow(Math.sin(dlon / 2),2);
			
		double c = 2 * Math.asin(Math.sqrt(a));

		// Radius of earth in kilometers. Use 3956
		// for miles
		double r = 6371;

		// calculate the result
		return(c * r); 
    }


}