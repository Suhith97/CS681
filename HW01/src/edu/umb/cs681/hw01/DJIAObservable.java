package edu.umb.cs681.hw01;

public class DJIAObservable extends Observable<Double>{
	private double quote;
	
	public void changeQuote(double q) {
		quote = q;
		notifyObservers(quote);
	}
	
	public static void main(String args[]) {
		DJIAObservable observable = new DJIAObservable();
		observable.addObserver( new LineChartObserver() );
		observable.addObserver( new TableObserver() );
		observable.changeQuote(30000.44);
		observable.changeQuote(30050.99);
	}

}
