package edu.umb.cs681.hw16;

import java.util.*;

class StatsHandler implements Runnable {

	private AdmissionMonitor monitor = new AdmissionMonitor();

	public void run(){
		int a =	monitor.countCurrentVisitors();
                System.out.println("StatsThread : Current number of visitors are "+a);

	}
}