package edu.umb.cs681.hw01;

public class StockObservable extends Observable<Double>{
	private String ticker; 
	private double quote;
	
	public void changeQuote(String t, double q) {
		ticker = t;
		quote = q;
		notifyObservers(quote);
	}

}
