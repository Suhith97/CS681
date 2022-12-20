package edu.umb.cs681.hw11;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.util.function.Supplier;
public class Aircraft {
	
 Position position;
	
	// Shared (non-final) variable
	public Aircraft(Position pos){ this.position = pos; }
	
	public void setPosition(double newLat,	double newLong, double newAlt) {
		
		this.position = this.position.change(newLat, newLong, newAlt);
	}

	public Position getPosition() {
	
		return position; 
	}
	
}
