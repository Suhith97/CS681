package edu.umb.cs681.hw19;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.*;
public abstract class Observable<T> {
	private static ReentrantLock lock1 = new ReentrantLock();
	private ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();
	
	public void addObserver(Observer<T> o) {
		
		observers.add(o);
	}

	public void clearObservers() {
		observers.clear();
		
	}
	public ConcurrentLinkedQueue<Observer<T>> getObservers(){
		return observers;
	}
	
	public int countObservers() {
		return observers.size();
		
	}
	public void removeObserver(Observer<T> o) {
		observers.remove(o);
	}

	public void notifyObservers(T event) {
		T k;
		k = event;

		observers.forEach( (observer)->{observer.update(this, k);} );
	}
	
}
