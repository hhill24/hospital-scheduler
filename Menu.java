
import java.util.Date;
import java.util.HashSet;



import java.util.Scanner;
import java.util.Set;

public class Menu {
	
	Scanner s = new Scanner(System.in);
	Set<Diary> diaries;

	
	public Menu() {
		diaries=null;
	}
	
	public void initialise() {
		diaries =new HashSet<>();
	}
	
	public int displayMenu() {
		System.out.println("---------- MENU ----------");
		System.out.println("0) Exit  ");
		System.out.println("1) ");
		System.out.println("2) ");
		System.out.println("3) ");
		System.out.println("4) ");
		System.out.println("5) ");

		System.out.println("Enter Menu Choice (0-5)");
		
		return s.nextInt();
	}
	
	public void process() {
		int choice;
		do {
			choice = displayMenu();
			switch (choice) {

			case 0:
				System.exit(0);
				break;
			case 1:
				
				System.out.println();
				break;
			case 2:
				
				System.out.println();
				break;
			case 3:
			
				System.out.println();
				break;
			case 4:
				
				System.out.println();
				break;
			case 5:
				
				System.out.println();
				break;
			
			default:
				System.out.println("Invalid Option");
			}
		} while (choice != 0);
	}
	
	/**
	 * searches for an appointment.
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
	 * schedules an appointment based on the diaries of the health professionals
	 */
	public void scheduleAppointment() {
		
		int[] startInt = {0,0,0};
		int[] endInt = {0,0,0};

		System.out.println("Enter List of Health Professionals involved, separated by commas:");
		String input = s.next();
		String[] professionals = input.split(",");
		
		System.out.println("Enter start date in format YYYY/MM/DD");
		String start = s.next();
		String[] startDateString = start.split("/");
		System.out.println("Enter end date in format YYYY/MM/DD");
		String end = s.next();
		String[] endDateString= end.split("/");
		
		
		for (int i=0; i<3; i++) {
			startInt[i]=Integer.parseInt(startDateString[i]);
			endInt[i]=Integer.parseInt(endDateString[i]);

		}

		Date startDate = new Date(startInt[0], startInt[1], startInt[2]);
		Date endDate = new Date(endInt[0], endInt[1], endInt[2]);
		
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
		//add appointment with this date to all health professionals in neededHPs
		

	}
	
	
	/**
	 * returns how long the program took to complete a scheduling
	 */
	public void timeTaken() {
		
	}
	
}
