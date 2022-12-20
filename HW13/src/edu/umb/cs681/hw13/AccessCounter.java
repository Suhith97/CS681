package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.nio.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
public class AccessCounter{
	
	HashMap<java.nio.file.Path, Integer> map = new HashMap<>();
	
	protected AccessCounter() {
		
	};

	private static AccessCounter instance = null;
	
	private static ReentrantLock lock = new ReentrantLock();
	//private  ReentrantLock lock2 = new ReentrantLock();

// Factory method to create or return the singleton instance
	public static AccessCounter getInstance() {
	
		lock.lock();
		try{
			System.out.println("Thread entered to get the instance and locked access\n");	
			if(instance==null){ instance = new AccessCounter(); }
			return instance;
		}
		finally {
			lock.unlock();
		}
        }
	
	public void increment(Path p) {
		
		try {
			lock.lock();
			map.merge(p,1,Integer::sum);
		} 
		finally {
			lock.unlock();
		}
	
	}
	
	public int getCount(Path p) {
		
		lock.lock();
		try {	

			if(map.containsKey(p))	
				return map.get(p);
		}
		finally {
			lock.unlock();
		}
		return 0;
	}

	public static void main() {
		
		AccessCounter ac = getInstance();
		
		ac.map.put(Paths.get("a.html"),0);	
		
	
	}
}
