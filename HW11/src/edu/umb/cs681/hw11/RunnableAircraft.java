package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.util.function.Supplier;
public class RunnableAircraft extends Aircraft implements Runnable{

    	private static ReentrantLock lock = new ReentrantLock();

	public RunnableAircraft(Position k) {
		
		super(k);
	}	
	
	public void setPosition(double newLat,	double newLong, double newAlt) {
		
		lock.lock();		
		position = position.change(newLat, newLong, newAlt);
		lock.unlock();
	}

	public Position getPosition() {
		
		return position;
	        	
	}
        
	public void run() {
              
		setPosition(1,2,3);
	  	lock.lock();
		Position k = getPosition();
		lock.unlock();
		System.out.println(k);	
		System.out.println("zz\n");
        }
	
		
    public static void main(String[] args) {
        
	System.out.println( " sadsa");
        Position p = new Position(0,2,0);
        RunnableAircraft a = new RunnableAircraft(p);
	
	Thread thread1 = new Thread(a);
	Thread thread2 = new Thread(a);
	thread1.start();
	thread2.start();
        try {
            thread1.join();
	    thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	System.out.println("Done\n");
	       
        
    }
}
