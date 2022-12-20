package edu.umb.cs681.hw19;

import java.util.*;
//import javafx.util.*;
import java.util.concurrent.locks.ReentrantLock;
public class StockQuoteObservable extends Observable<Pair> {

		private static ReentrantLock lock = new ReentrantLock();

		HashMap<String, Double> map = new HashMap<>();
	
		public void changeQuote(String a, Double b) {
			
			lock.lock();
			map.put(a,b);
			lock.unlock();

			Pair p = new Pair(a,b);
			notifyObservers(p);	
		
		}		
		
}
