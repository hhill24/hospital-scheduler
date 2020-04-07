
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * runs the hospital scheduling program
 *
 */
public class Menu {
	
	Scanner s = new Scanner(System.in);
	Set<Diary> diaries; //contains the diary trees of all employees
	Map<Integer,Patient> patients; // maps patient instances to a unique id
	Stack<String> actions; //stack of actions (add, edit or delete) to track most recent change made
	Diary lastEdited; //keeps track of the last employee diary to be edited
	Appointment lastEditedApp; //keeps track of the last appointment to be added/deleted
	Appointment lastEditMade; //keeps track of the last appointment to be changed.

	/**
	 * default constructor sets Set of diaries to null;
	 */
	public Menu() {
		diaries=null;
	}
	
	/**
	 * initialises the data structures and class instances used in the scheduling system
	 */
	public void initialise() {
		diaries =new HashSet<>();
		patients = new HashMap<>();
		actions = new Stack<String>();
		lastEdited = new Diary();
		lastEditedApp = new Appointment();
	}
	
	/**
	 * Displays menu options and gets user input response
	 * Returns -1 if a number is not entered
	 * @return int option chosen
	 */
	public int displayMenu() {
		System.out.println("---------- MENU ----------");
		System.out.println("0) Exit  ");
		System.out.println("1) Add Appointment");
		System.out.println("2) Delete Appointment");
		System.out.println("3) Edit Appointment");
		System.out.println("4) Undo");
		System.out.println("5) Search for Apoointment");
		System.out.println("6) Save Diary");
		System.out.println("7) Load diary from file");

		System.out.println("Enter Menu Choice (0-5)");
		
		try {
			return s.nextInt();
		} catch (Exception e) {
			System.out.println("Menu choice must be an integer from 0-7");
			return -1;
		}
		
	}
	
	
	/**
	 * 
	 */
	public void process() {
		
		int choice;
		do {
			choice = displayMenu();
			switch (choice) {

			case 0:
				System.exit(0);
				break;
			case 1:
				scheduleAppointment();
				actions.push("add");
				System.out.println();
				break;
			case 2:
				deleteAppointment();
				System.out.println();
				actions.push("delete");
				break;
			case 3:
				editAppointment();
				System.out.println();
				actions.push("edit");
				break;
			case 4:
				undo();
				System.out.println();
				break;
			case 5:
				search();
				System.out.println();
				break;
			case 6:
				try {
					saveDiary();
				} catch (IOException e) {
					
					System.out.println("Diary could not be saved");
				}
				System.out.println();
				break;
			case 7:
				try {
					loadDiary();
				} catch (IOException e) {
					
					System.out.println("Diary could not be found");
				}
				System.out.println();
				break;
			default:
				System.out.println("Invalid Option");
			}
		} while (choice != 0);
	}
	
	
	/**
	 * searches for an appointment across all diaries.
	 */
	public Appointment search(){
		System.out.println("Enter Appointment ID");
		int wantedID = s.nextInt();
		Appointment found = null;
	
		for(Diary d: diaries) {
			found = d.findInDiary(wantedID);
		}
		return found;
		
	}
	
	/**
	 * schedules an appointment based on all the diaries of the health professionals employed
	 */
	public void scheduleAppointment() {
		
		int[] startInt = {0,0,0,0};
		int[] endInt = {0,0,0,0};

		System.out.println("Enter List of Health Professionals involved, separated by commas:");
		String input = s.next();
		String[] professionals = input.split(",");
		
		System.out.println("Enter start date in format YYYY/MM/DD/HH");
		String start = s.next();
		String[] startDateString = start.split("/");
		
		System.out.println("Enter end date in format YYYY/MM/DD/HH");
		String end = s.next();
		String[] endDateString= end.split("/");
		
		System.out.println("Enter location");
		String location = s.next();
		
		System.out.println("Enter treatment");
		String treatment = s.next();
		
		System.out.println("Enter patient ID");
		int id = s.nextInt();
		Patient patient = patients.get(id);
		
		
		
		for (int i=0; i<4; i++) {
			startInt[i]=Integer.parseInt(startDateString[i]);
			endInt[i]=Integer.parseInt(endDateString[i]);

		}

		Date startDate = new Date(startInt[0], startInt[1], startInt[2],startInt[3], 0, 0);
		Date endDate = new Date(endInt[0], endInt[1], endInt[2],endInt[3],0,0);
		
		//creates a set of the diaries of the needed health professionals
		Set<Diary> neededHPs = new HashSet<>();
		for (String hp: professionals) {
			for(Diary d: diaries) {
				if (d.getOwner().getName()==hp) {
					neededHPs.add(d);
				}
			}
		}
		
		//creates a set with the dates (between startDate and endDate) that all required professionals are busy
		Set<Date> allBusy = new HashSet<>();
		for(Diary d : neededHPs) {
			Set<Date> b = d.createBusy(d.root);
			for (Date dd : b) {
				
				if(dd.getDate()<endDate.getDate() || dd.getDate()>startDate.getDate()) {
				allBusy.add(dd);
				}
			}
			
		}
		
		//find earliest date between start and end not in allBusy
		boolean found = false;
		Date appointment = startDate;
		while (appointment.getDate() < endDate.getDate() ) {
			if(allBusy.contains(appointment)) {
				appointment.setHours(appointment.getHours()+1);
			}else {
				found = true;
			}
		}
		
		//adds appointment to all necessary diaries if found
		if (found) {
		for (Diary d: neededHPs) {
			Appointment a = new Appointment(appointment,location,patient,treatment);
			d.addAppointment(a);
			lastEdited = d;
			lastEditedApp=a;

		}
		}else {
			System.out.println("No availabile spaces for appointment");
		}
		

	}
	
	/**
	 * finds an appointment within the diary of the chosen health professional and deletes it
	 * 
	 */
	public void deleteAppointment() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Health Professional ID");
		String id = s.next();
		System.out.println("Enter Appointment ID");
		String appId = s.next();

		
		for(Diary d: diaries) {
			if( d.getOwner().id == Integer.parseInt(id)) {
				Appointment newNode = d.findInDiary(Integer.parseInt(appId));
				d.deleteAppointment(newNode);
				lastEdited = d;
				lastEditedApp = newNode;

			}
		}
	}
	
	/**
	 * changes the field values of users chosen health professional
	 */
	public void editAppointment() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Health Professional ID");
		String id = s.next();
		
		for(Diary d: diaries) {
			if( d.getOwner().id == Integer.parseInt(id)) {
				lastEditMade = d.editAppointment();
				lastEdited = d;
				
				
			}
		}
	}
	
	
	/**
	 * pops the last action off the Stack actions and reverses it
	 * 
	 */
	public void undo() {
		if(actions.pop()=="add") {
			lastEdited.deleteAppointment(lastEditedApp);
		}else if(actions.pop()=="delete") {
			lastEdited.addAppointment(lastEditedApp);
		}else if(actions.pop()=="edit") {
			Appointment change = lastEdited.findInDiary(lastEditMade.getID());
			change = lastEditMade;
		}
	}
	
	
	/**
	 * Saves the diary of a chosen health professional to an external file
	 * @throws IOException
	 */
	public void saveDiary() throws IOException {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Health Professional ID");
		String id = s.next();
		
		for(Diary d: diaries) {
			if( d.getOwner().id == Integer.parseInt(id)) {
				d.save(d.root, id);
			}
		}
	}
	
	/**
	 * Loads the diary of a chosen health professional from an external file.
	 * @throws IOException
	 */
	public void loadDiary() throws IOException {
		Scanner s = new Scanner(System.in);
    	System.out.println("Enter Health Professional ID");
    	String id = s.next();
    	for(Diary d: diaries) {
			if( d.getOwner().id == Integer.parseInt(id)) {
				d.load(id);
			}
		}
    	
	}
	
	
	
	
	/**
	 * returns how long the program took to complete a scheduling
	 */
	public void timeTaken() {
		
	}
	
	/**
	 * main method which runs the scheduling program
	 * @param args
	 */
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.process();
		
	}
	

	
}
