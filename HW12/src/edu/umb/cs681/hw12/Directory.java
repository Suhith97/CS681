package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{
	private LinkedList<FSElement> children;
    
    	private ReentrantLock lock = new ReentrantLock();	

	public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
		super(parent, name, size, creationTime);
		this.children = new LinkedList<>();
	}
	
	public boolean isDirectory() {
		return true;
	}
	
	public boolean isFile() {
		return false;
	}

	public boolean isLink() {
		return false;
	}
	
	public LinkedList<FSElement> getChildren(){
		return this.children;
	}
	
	public void appendChild(FSElement child) {
		lock.lock();
		try {
		this.children.add(child);
		child.setParent(this); }
		finally {
			lock.unlock();
		}

	}
	
	public int countChildren() {
		return this.children.size();
	}
	
	public LinkedList<Directory> getSubDirectories() {
	
		LinkedList<Directory> subDirs = new LinkedList<>();
		lock.lock();
		try{

			for (FSElement fsElement : children) {
				if (fsElement.isDirectory()) {
					Directory subDir = (Directory)fsElement;
					subDirs.add(subDir);
				}
			}
		}
	        finally {
                        lock.unlock();
			return subDirs;
                }


	}
	
	public LinkedList<File> getFiles(){
		
		LinkedList<File> files = new LinkedList<>();
		lock.lock();		
		try{

			for (FSElement fsElement : children) {
				if (fsElement.isFile()) {
					File file = (File)fsElement;
					files.add(file);
				}
			}
		}
		finally{
			lock.unlock();
			return files;
		}
	
	}

	public LinkedList<Link> getLinks(){
		LinkedList<Link> links = new LinkedList<>();
		lock.lock();
		try {
			for (FSElement fsElement : children) {
				if (fsElement.isLink()) {
					Link link = (Link)fsElement;
					links.add(link);
				}
			}
		}
		finally {
			
			lock.unlock();
			return links;
		}
	}
	
	public int getTotalSize() {
		int totalSize = 0;
		lock.lock();
		try {
			for (FSElement fsElement : children) {
				if (fsElement.isDirectory()) {
					Directory subDir = (Directory)fsElement;
					totalSize += subDir.getTotalSize();
				}else {
					totalSize += fsElement.getSize();
				}
			}
		}
		finally {	
			
			lock.unlock();
			return totalSize;
		}
	}

}