package edu.umb.cs681.hw08;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer{

    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone(){
        lock.lock();
        try {
            done = true;
        }finally{
            lock.unlock(); }
    }


    public void generatePrimeFactors() {
        long divisor = from;

        while( dividend != 1 && divisor <= to ){

            lock.lock();
            try {
	            if (done) {
	                break;
	            }
	            if( divisor > 2 && isEven(divisor)) {
	                divisor++;
	                continue;
	            }
	            if(dividend % divisor == 0) {
	                factors.add(divisor);
	                dividend /= divisor;
	            }else {
	                if(divisor==2){ 
	                	divisor++; 
	                	}
	                else{
	                	divisor += 2; 
	                	}
	                }
            }
            finally { 
            	lock.unlock();
            	}
            }
        }


    public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(3000,2, 24);
        Thread thread = new Thread(gen);
        thread.start();
        
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        gen.setDone();
        gen.getPrimeFactors().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + gen.getPrimeFactors().size() + " prime numbers are found.");
        
        
    }
}



