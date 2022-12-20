package edu.umb.cs681.hw17;

import java.util.*;

public class DataHandler implements Runnable {

	private static StockQuoteObservable sq = new StockQuoteObservable();

	public void run() {
		
		long t = Thread.currentThread().getId();
			
		sq.changeQuote("floweru",(double)t);
	}
	
	public static void main(String ... args) {
	
                DataHandler dh = new DataHandler();
		sq.addObserver( new StockEvent() );
		
		Thread myThreads[] = new Thread[20];
		for (int j = 0; j < 20; j++) {
		    myThreads[j] = new Thread(dh);
		    myThreads[j].start();
		}
	
	
	}
}
