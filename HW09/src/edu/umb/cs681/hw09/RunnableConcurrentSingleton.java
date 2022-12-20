package edu.umb.cs681.hw09;


import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

public class RunnableConcurrentSingleton extends ConcurrentSingleton implements Runnable{
	
//	public RunnableConcurrentSingleton(int a) {};

	public void run(){ 
		//ConcurrentSingleton p = getInstance();
		System.out.println(getInstance());
	}	
//	private static ReentrantLock lock = new ReentrantLock();

// Factory method to create or return the singleton instance


	public static void main(String ... args) {
	
		RunnableConcurrentSingleton o = new RunnableConcurrentSingleton();
		Thread t1 = new Thread(o);
		t1.start();	
		Thread t2 = new Thread(o);
		t2.start();	
		Thread t3 = new Thread(o);
		t3.start();	
		
		try{
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
            		
			e.printStackTrace();
        	}
	
	}
}
