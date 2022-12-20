package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;
public abstract class FSElement {
	protected Directory parent;
	protected String name;
	protected int size;
	protected LocalDateTime creationTime;
	
	private ReentrantLock lock = new ReentrantLock();	
	
	public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
		this.parent = parent;
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;
	}
	
	public Directory getParent() {
		Directory p = new Directory(parent,"",0,creationTime); 	
		lock.lock();
		try {
			p = new Directory(parent.parent,parent.name,parent.size,parent.creationTime);
		}		
		finally {
			lock.unlock();	
			return p;
		}
	}

	public void setParent(Directory parent) {
		lock.lock();
		this.parent = parent;
		lock.unlock();
	}

	public String getName() {
		
		String lname = new String("porafruitu");	
		lock.lock();
                try{

			lname =new String(name);
		} finally {
			lock.unlock();
			return lname;
		}
	}

	public void setName(String name) {
		lock.lock();
		try{
		this.name = name;
		}
		finally{lock.unlock();}
	}

	public int getSize() {
		
		int a = 0;
		lock.lock();
		try {
			a = size;
		}
		finally {
			lock.unlock();
			return a;
		}
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	
	public abstract boolean isDirectory();
	public abstract boolean isFile();
	public abstract boolean isLink();
	
}
