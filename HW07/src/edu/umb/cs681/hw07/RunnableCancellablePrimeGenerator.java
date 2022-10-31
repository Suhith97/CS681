package edu.umb.cs681.hw07;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();


    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone(){
    	lock.lock();
        try {
            done = true;
        }finally{
        	lock.unlock();
        }
    }

    public void generatePrimes(){
        for (long n = from; n <= to; n++) {   
        	lock.lock();
            try {
                if (done) {
                 
                    break;
                }
                if (isPrime(n)) {
                    this.primes.add(n);
                }
            } finally {
            	lock.unlock();
            }
        }
    }

    public static void main(String[] args) {	
        RunnableCancellablePrimeGenerator generator = new RunnableCancellablePrimeGenerator(1,100);
        Thread thread = new Thread(generator);

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        generator.setDone();
        generator.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + generator.getPrimes().size() + " prime numbers are found.");
        
        
    }
}
