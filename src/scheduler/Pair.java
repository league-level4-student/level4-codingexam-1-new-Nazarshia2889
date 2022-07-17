package scheduler;

import java.util.Date;

public class Pair {
	Date d;
	String s;
	
	public Pair(Date d, String s) {
		this.d = d;
		this.s = s;
	}
	
	public Date getDate() {
		return this.d;
	}
	
	public String getString() {
		return this.s;
	}
}
