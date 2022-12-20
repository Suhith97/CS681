package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

public class ConcurrentSingleton{
	
	private int key;

	protected ConcurrentSingleton() {
		
	};

	private static ConcurrentSingleton instance = null;
	
	private static ReentrantLock lock = new ReentrantLock();

// Factory method to create or return the singleton instance
	public static ConcurrentSingleton getInstance() {
	
		lock.lock();
		try{
			System.out.println("Thread entered to get the instance and locked access\n");	
			if(instance==null){ instance = new ConcurrentSingleton(); }
			return instance;
		}
		finally {
			lock.unlock();
		}
        }


	public static void main() {
	
		
	
	}
}
