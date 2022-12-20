package edu.umb.cs681.hw18;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.nio.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.*;
public class AccessCounter{
	
	ConcurrentHashMap<java.nio.file.Path, Integer> map = new ConcurrentHashMap<>();
	
	protected AccessCounter() {
		
	};

	private static AccessCounter instance = null;
	
	private static ReentrantLock lock = new ReentrantLock();
	private  ReentrantLock lock2 = new ReentrantLock();

// Factory method to create or return the singleton instance
	public static AccessCounter getInstance() {
	
			System.out.println("Thread entered to get the instance and locked access\n");	
			if(instance==null){ instance = new AccessCounter(); }
			return instance;
        }
	
	public void increment(Path p) {
		
			//map.merge(p,1,Integer::sum);
			map.computeIfPresent(p, (key,val) -> val++);
			map.computeIfAbsent(p,(key) -> {return 1;});
	
	}
	
	public int getCount(Path p) {
		

//		if(map.containsKey(p))	
//			return map.get(p);
		map.computeIfPresent(p, (key,val) -> {return 1;});	
		return 0;
	}

	public static void main() {
		
		AccessCounter ac = getInstance();
		
		ac.map.put(Paths.get("a.html"),0);	
		
	
	}
}
