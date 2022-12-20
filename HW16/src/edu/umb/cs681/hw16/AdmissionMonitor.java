package edu.umb.cs681.hw16;

import java.util.concurrent.locks.*;
import java.util.concurrent.locks.Condition;

import java.util.*;

public class AdmissionMonitor{


	private static ReentrantReadWriteLock lock2 = new ReentrantReadWriteLock();
	private static Lock lock = lock2.writeLock();
	private static Lock rl = lock2.readLock();
 	private static Condition condition = lock.newCondition();


	private static int currentVisitors = 0;

	public void enter() {
		
		lock.lock();
		try{


			while(currentVisitors >= 10) {
		
				System.out.println("Max visitors, releasing lock and going to wait state\n");
				condition.await();
	
			}
			
			currentVisitors++;

		}
		catch(Exception e) {
			
			System.out.println(e);
		}

		finally{
			lock.unlock();
			try {
				Thread.sleep(3000);
			}
                    
		    	catch(InterruptedException e) {
                                //e.printStackTrace();
                                System.out.println("Woke up from sleep and I'll gracefully exit:)\n");
                        }


		}
	}
	
	public void exit() {
		
		lock.lock();
		currentVisitors--;
		condition.signalAll();
		lock.unlock();


		
		try {
			Thread.sleep(3000);
		}

		catch(InterruptedException e) {
			//e.printStackTrace();
			System.out.println("Woke up from sleep and I'll gracefully exit:)\n");
		}
	
	}
	
	public int countCurrentVisitors(){
		
		int ret = 0;
		rl.lock();	
			ret = currentVisitors;
		rl.unlock();
		 try {
                        Thread.sleep(3000);
                }

                catch(InterruptedException e) {
                        //e.printStackTrace();
                        System.out.println("Woke up from sleep and I'll gracefully exit:)\n");
			return ret;
                }
		finally{
			return ret;
		}


	}


	public static void main(String ... args) {
		
		EntranceHandler e = new EntranceHandler();
		ExitHandler ex = new ExitHandler();
		StatsHandler st = new StatsHandler();


		Thread t1 = new Thread(e);	
		Thread t2 = new Thread(ex);
		Thread t3 = new Thread(st);


		t1.start();
		t2.start();
		t3.start();
		
		try{
		
			Thread.sleep(3000);
		}
		catch(Exception e1){}
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
	}

}

