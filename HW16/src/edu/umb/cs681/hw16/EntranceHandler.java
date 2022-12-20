package edu.umb.cs681.hw16;

import java.util.*;

class EntranceHandler implements Runnable {

	private AdmissionMonitor monitor = new AdmissionMonitor();

	public void run() {
		System.out.println("Entery Handler thread started and about to enter\n");
		monitor.enter();
	}

}
