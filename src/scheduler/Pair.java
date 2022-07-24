package scheduler;

import java.util.Date;

public class Pair {
	int d;
	String s;
	
	public Pair(Integer d, String s) {
		this.d = d;
		this.s = s;
	}
	
	public int getDate() {
		return this.d;
	}
	
	public String getString() {
		return this.s;
	}
}
