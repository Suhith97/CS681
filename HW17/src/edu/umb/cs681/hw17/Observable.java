package edu.umb.cs681.hw17;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
public abstract class Observable<T> {
	private static ReentrantLock lock1 = new ReentrantLock();
	private LinkedList<Observer<T>> observers = new LinkedList<>();
	
	public void addObserver(Observer<T> o) {
		
		lock1.lock();
		observers.add(o);
		lock1.unlock();
	}

	public void clearObservers() {
		lock1.lock();
		observers.clear();
		lock1.unlock();
		
	}
	public List<Observer<T>> getObservers(){
		return observers;
	}
	
	public int countObservers() {
		return observers.size();
		
	}
	public void removeObserver(Observer<T> o) {
		lock1.lock();
		observers.remove(o);
		lock1.unlock();
	}

	public void notifyObservers(T event) {
		T k;
		lock1.lock();
		k = event;
		lock1.unlock();

		observers.forEach( (observer)->{observer.update(this, k);} );
	}
	
}
