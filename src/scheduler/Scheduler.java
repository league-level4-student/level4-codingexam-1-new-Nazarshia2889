package scheduler;

import java.time.LocalTime;
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

    public static void main(String[] args) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("What time will be your event? Format like this: (hh:mm aa)");
		String time = scanner.next("[hh:mm]");
		LocalTime lt = LocalTime.parse(time);
		System.out.println(lt);
    	
//    	System.out.println("Choose a day of the week to explore.");
//    	String dayChosen = scanner.nextLine();
//    	
//    	System.out.println("\n");
//    	System.out.println("What would you like to do?");
//    	System.out.println("View Events ('view'), Add Events ('add'), Remove Events ('remove')");
//    	String action = scanner.nextLine();
//    	
//    	if(action.equalsIgnoreCase("view")) {
//    		Days day = getDay(dayChosen);
//    		LinkedList<Pair> events = day.events;
//     		Node<Pair> next = events.getHead();
//     		
//     		while(next != null) {
//     			System.out.println(next.getValue());
//     			next = next.getNext();
//     		}
//    	}
//    	else if(action.equalsIgnoreCase("add")) {
//    		System.out.println("What time will be your event? Format like this: (hh:mm aa");
//    		String time = scanner.next("hh:mm aa");
//    		LocalTime lt = LocalTime.parse(time);
//    	}
//    	
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
}
