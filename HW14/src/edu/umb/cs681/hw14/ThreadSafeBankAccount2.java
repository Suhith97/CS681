package edu.umb.cs681.hw14;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount , Runnable{
	
	public void ThreadSafeBankAccount2() {
	}
	
	ThreadSafeBankAccount2(int flag) {
		
		if(flag == 0) 
			depositor_thread = true;
		else if(flag == 1)
			withdrawl_thread = true;
	}	
	public void run(){
	
		if(depositor_thread == true) {
			deposit(10);
		
		}
		else if(withdrawl_thread == true) {
			withdraw(3);
		
		}
	
	}	
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private ReentrantLock lock2 = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	private volatile boolean done = false;
	public boolean depositor_thread = false;
	public boolean withdrawl_thread = false;	
        public void setDone(){
        lock2.lock();
        try {
            done = true;
        }finally{
            lock2.unlock(); }
        }
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				if (done) {
                                        break;
                                }

				System.out.println(Thread.currentThread().getId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			//exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
			try{
                                Thread.sleep(3000);
                        }
                        catch(InterruptedException e) {
                                //e.printStackTrace();
                        	System.out.println("Woke up from sleep and I'll gracefully exit:)\n");
			}

		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{

			
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
			
				if (done) {
					break;
				}	
				System.out.println(Thread.currentThread().getId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			//exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
			try{
				Thread.sleep(3000);
			}
			catch(InterruptedException e) {
				//e.printStackTrace();
                        	System.out.println("Woke up from sleep and I'll gracefully exit:)\n");
			
			}
		}
	}
	
	public static void main(String[] args){
		ThreadSafeBankAccount2 depositor = new ThreadSafeBankAccount2(0);
		ThreadSafeBankAccount2 withdrawl = new ThreadSafeBankAccount2(1);
		for(int i = 0; i < 5; i++){
			System.out.println("zz");		
			//new Thread( new DepositRunnable(bankAccount) ).start();
			//new Thread( new WithdrawRunnable(bankAccount) ).start();
		}
		Thread myThreads[] = new Thread[10];
		for (int j = 0; j < 10; j++) {
		    if(j < 5)
		    	myThreads[j] = new Thread(depositor);
		    else
		    	myThreads[j] = new Thread(withdrawl);

		    myThreads[j].start();
		}
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		depositor.setDone();
		withdrawl.setDone();
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