package scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/*
 * Objective: Create a weekly scheduling application.
 * 
 * You may create any additional enums, classes, methods or variables needed
 * to accomplish the requirements below:
 * 
 * - You should use an array filled with enums for the days of the week and each
 *   enum should contain a LinkedList of events that includes a time and what is 
 *   happening at the event.
 * 
 * - The user should be able to to interact with your application through the
 *   console and have the option to add events, view events or remove events by
 *   day.
 *   
 * - Each day's events should be sorted by chronological order.
 *  
 * - If the user tries to add an event on the same day and time as another event
 *   throw a SchedulingConflictException(created by you) that tells the user
 *   they tried to double book a time slot.
 *   
 * - Make sure any enums or classes you create have properly encapsulated member
 *   variables.
 */
public class Scheduler {
	
	static Days[] days = Days.values();

    public static void main(String[] args) throws ParseException, SchedulingConflictException {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	SimpleDateFormat format;
    	
    	boolean keepGoing = true;
    	
    	while(keepGoing) {
	    	System.out.println("Choose a day of the week to explore.");
	    	String dayChosen = scanner.nextLine();
	    	
	    	System.out.println("What would you like to do?");
	    	System.out.println("View Events ('view'), Add Events ('add'), Remove Events ('remove')");
	    	String action = scanner.nextLine();
	    	
	    	if(action.equalsIgnoreCase("view")) {
	    		Days day = getDay(dayChosen);
	    		LinkedList<Pair> events = day.events;
	     		Node<Pair> next = events.getHead();
	     		
	     		while(next != null) {
	     			System.out.println(next.getValue().getString());
	     			next = next.getNext();
	     		}
	    	}
	    	else if(action.equalsIgnoreCase("add")) {
	    		System.out.println("What time will be your event? Format like this (h:mm aa)");
	    		String time = scanner.nextLine();
	    		format = new SimpleDateFormat("h:mm aa");
	    		Date date = format.parse(time);
	    		int minutes = (int) ((date.getTime() / (1000 * 60))-480);
	    		String timeString = (minutes/60) + ":" + (minutes%60);
	    		
	    		System.out.println("Give a brief description of your event: ");
	    		String description = scanner.nextLine();
	    		
	    		Days day = getDay(dayChosen);
	    		LinkedList<Pair> events = day.events;
	    		Pair pair = new Pair(minutes, timeString + " - " + description);
	    		SchedulingConflictException sce = new SchedulingConflictException();
	    		try {
		    		if(search(pair.getDate(), events)) {
		    			throw new SchedulingConflictException();
		    		}
		    		else {
		    			events.add(pair);
		    			System.out.println(pair.getString());
			    		events = sort(events);
		    		}
	    		}
	    		catch (Exception SchedulingConflictException) {
	    			sce.popup();
	    		}
	    		
	    		
	    	}
	    	
	    	else if(action.equalsIgnoreCase("remove")) {
	    		Days day = getDay(dayChosen);
	    		LinkedList<Pair> events = day.events;
	    		System.out.println("What index (0-indexed) event do you want to remove? Here are your current events for reference.");
	     		Node<Pair> next = events.getHead();
	     		
	     		while(next != null) {
	     			System.out.println(next.getValue().getString());
	     			next = next.getNext();
	     		}
	    		String index = scanner.nextLine();
	    				
	    		events.remove(Integer.parseInt(index));
	    	}
	    	
	    	else {
	    		System.out.println("Sorry, there was an error processing your request. Please try again.");
	    	}
	    	
	    	System.out.println("Would you like to keep using the scheduler? (y/n)");
	    	String answer = scanner.nextLine();
	    	if(answer.equalsIgnoreCase("n")) {
	    		keepGoing = false;
	    	}
    	}
    	
    	scanner.close();
    }
    
    
    enum Days {
    	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    	
    	
    	public LinkedList<Pair> events;
    	
    	private Days() {
    		this.events = new LinkedList<Pair>();
    	}
    }
    
    static Days getDay(String dayChosen) {
    	for(Days day : days) {
    		if(dayChosen.equalsIgnoreCase(day.name())) {
    			return day;
    		}
    	}
    	return days[0];
    }
    
    static LinkedList sort(LinkedList<Pair> ll) {
    	Node<Pair> current = ll.getHead();
    	
    	
    	while(current != null) {
    		Node<Pair> index = current.getNext();
    		
    		while(index != null) { 
    			if(current.getValue().getDate() > index.getValue().getDate()) {

    				Node<Pair> temp = index;
    				index.setValue(new Pair(current.getValue().getDate(), current.getValue().getString()));
    				current.setValue(new Pair(temp.getValue().getDate(), temp.getValue().getString()));
    			}
    			
    			index = index.getNext();
    		}
    		
    		current = current.getNext();
    	}
    	
    	return ll;
    }
    
    static boolean search(int time, LinkedList<Pair> events) {
    	Node<Pair> current = events.getHead();
    	while(current != null) {
    		if(current.getValue().getDate() == time) {
    			return true;
    		}
    		current = current.getNext();
    	}
    	return false;
    }
}

