import java.util.HashMap;
import java.util.Scanner;

public class Menu {
	HashMap <HealthProfessional, Diary> HPDiaries;
	HashMap <Patient, Diary> patientDiaries;
	Scanner s = new Scanner(System.in);

	public Menu() {
		HPDiaries = null;
		patientDiaries = null;
	}
	
	public void initialise() {
		HPDiaries = new HashMap<HealthProfessional, Diary>();
		patientDiaries = new HashMap<Patient, Diary>();

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
	public void search(){
		
	}
	
	/**
	 * schedules an apppointment based on the diaries of the health professionals
	 */
	public void scheduleAppointment() {
		
	}
	
	/**
	 * returns how long the program took to complete a scheduling
	 */
	public void timeTaken() {
		
	}
	
}
