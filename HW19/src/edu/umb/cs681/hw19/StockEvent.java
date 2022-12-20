package edu.umb.cs681.hw19;

public class StockEvent implements Observer<Pair>{
	public void update(Observable<Pair> sender, Pair event) {
		System.out.println("Event: " + event.first + event.second + ", Sender: " + sender);
	}
}
