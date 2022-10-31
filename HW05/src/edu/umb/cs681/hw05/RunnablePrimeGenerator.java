package edu.umb.cs681.hw05;

import java.io.IOException;
import java.util.LinkedList;

class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	  public RunnablePrimeGenerator(long from, long to) {
	    super(from, to);
	  }

	  public static void main(String[] args) throws InterruptedException, IOException {
	    System.out.println("Threads, Time in ms");

	    // Using a for loop to generate multiple threads
	    for (int i = 1; i <= 16; i *= 2) {
	      LinkedList<Thread> threads = new LinkedList<Thread>();
	      LinkedList<RunnablePrimeGenerator> primeGenerators = new LinkedList<RunnablePrimeGenerator>();
	      // start time measuring
	      long startTime = System.currentTimeMillis();
	      // Creating a new runnableprime object
	      for (int j = 0; j < i; j++) {
	        RunnablePrimeGenerator g = new RunnablePrimeGenerator(
	                1L, 2000000L);
	        Thread t = new Thread(g);
	        t.start();
	        threads.add(t);
	        primeGenerators.add(g);
	      }
	      // Calling t.join based on the linked list threads
	      for (Thread t : threads) {
	        t.join();
	      }
	      long estimatedTime = System.currentTimeMillis() - startTime;
	      System.out.println(i + "," + estimatedTime + "\n");

	      for (RunnablePrimeGenerator g: primeGenerators) {
	        //System.out.println("Dummy print because 2000000 Primes are very noisy");
	        System.out.println(g.getPrimes());
	      }

	    }
	  }
	  public void run() {
	    super.generatePrimes();
	  }
	}

