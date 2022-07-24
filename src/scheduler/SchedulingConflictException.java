package scheduler;

import javax.swing.JOptionPane;

public class SchedulingConflictException extends Exception {
	public void popup() {
		System.out.println("You have made an event at the same time at another event. Try again later.");;
	}
}
