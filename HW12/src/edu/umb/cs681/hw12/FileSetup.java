package edu.umb.cs681.hw12;



import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileSetup implements Runnable {
		volatile boolean isDone = false;
		
		public void setDone() {
			 isDone = true;
		}
		

		public FileSetup() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			while (true) {
				// 2step interruption
				if (isDone) {
					break;
				}
							
			
				Directory root = new Directory(null, "Root", 0, LocalDateTime.now());
				Directory Apps = new Directory(root, "Application", 0, LocalDateTime.now());
				Directory lib = new Directory(root, "Library", 0, LocalDateTime.now());
				Directory Home = new Directory(root, "Home", 0, LocalDateTime.now());
				Directory code = new Directory(Home, "Code", 0, LocalDateTime.now());
				File A = new File(Apps, "A", 1, LocalDateTime.now());
			    File B = new File(Apps, "B", 2, LocalDateTime.now());
				File C = new File(lib, "C",  3,  LocalDateTime.now());
				File D = new File(Home, "D", 4, LocalDateTime.now());
			    File E = new File(code, "E", 5, LocalDateTime.now());
			    File P = new File(Apps, "P", 25, LocalDateTime.now());
			    File Q = new File(Apps, "Q", 20, LocalDateTime.now());  
				File R = new File(lib, "R",  30, LocalDateTime.now());
			    File S = new File(Home, "S", 35, LocalDateTime.now());
			    File T = new File(code, "T", 25, LocalDateTime.now());
							
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
}
