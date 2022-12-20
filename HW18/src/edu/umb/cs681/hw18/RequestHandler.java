package edu.umb.cs681.hw18;
import java.nio.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.lang.Math;
public class RequestHandler implements Runnable{
	

	private static ReentrantLock lock = new ReentrantLock();
	private static ReentrantLock  aclock = new ReentrantLock();
	private volatile boolean done = false;
	public void setDone() {
        
		lock.lock();
        
		try {
            
			done = true;
        
		} finally {
            
			lock.unlock(); 
		}		
		
	

	}
//	public RunnableConcurrentSingleton(int a) {};
	public AccessCounter ac = AccessCounter.getInstance();
	public void run(){ 
		//ConcurrentSingleton p = getInstance();
	while(true){
		try {	
			if(done)
				break;

			double r = Math.random();
			r = r % 3;
			//AccessCounter ac = AccessCounter.getInstance();
			if(r == 1) {
				aclock.lock();
				ac.increment(Paths.get("a.html"));
				aclock.unlock();

			}
			if(r == 2) {
				aclock.lock();
				ac.increment(Paths.get("b.html"));
				aclock.unlock();

			}
			if(r == 0) {
				aclock.lock();
				ac.increment(Paths.get("c.html"));
				aclock.unlock();

			}
		}
		finally {
			
			try {
				Thread.sleep(3000);
			}
			catch(InterruptedException e) { 
                    System.out.println("An interrupt from the main thread has woken me :), So I will gracefully exit in the next iteration\n");
                    	continue;
                	}
				
			System.out.println(AccessCounter.getInstance());
		}
	}
	
	}	
//	private static ReentrantLock lock = new ReentrantLock();

// Factory method to create or return the singleton instance


	public static void main(String ... args) {
	
		RequestHandler o = new RequestHandler();
/*
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
*/
		Thread myThreads[] = new Thread[10];
		for (int j = 0; j < 10; j++) {
		    myThreads[j] = new Thread(o);
		    myThreads[j].start();
		}
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		o.setDone();
		for(int j = 0 ; j < 10 ; ++j) {
		
			
			myThreads[j].interrupt();
		}
			
		for (int j = 0; j < 10; j++) {
		try {
		    myThreads[j].join(); //todo add catch exception
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		}
		
		System.out.println("Done zz\n");
	}
}
