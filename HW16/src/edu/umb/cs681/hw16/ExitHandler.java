package edu.umb.cs681.hw16;

import java.util.*;


class ExitHandler implements Runnable {
	
	private AdmissionMonitor monitor = new AdmissionMonitor();
	
	public void run(){
		 monitor.exit();
	}
	
}
