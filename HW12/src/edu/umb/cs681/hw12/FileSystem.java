package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;

public class FileSystem {
	    
	private static ReentrantLock lock = new ReentrantLock();
	private static FileSystem instance = null;
	private LinkedList<Directory> rootDirs;
	
	private FileSystem() {
		this.rootDirs = new LinkedList<>();
	}
	
	public static FileSystem getFileSystem() {
		lock.lock();
		try {
			if (instance == null) {
				instance = new FileSystem();
			}
		}
		finally {
			
			lock.unlock();
			return instance;
		}
	}
	
	public LinkedList<Directory> getRootDirs(){
		lock.lock();
		try{
			return rootDirs;
		} finally{
			lock.unlock();
		}
	}
	
	public void appendRootDir(Directory root) {
		lock.lock();
		try{
			rootDirs.add(root);
		} finally{
			lock.unlock();
		}
		
	}
}