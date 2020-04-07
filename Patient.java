
/**
 * 
 * stores details of each individual patient of the hospital
 *
 */
public class Patient {
	String name;
	Diary appointments;
	
	/**
	 * Default constructor to create new blank instance of patient
	 */
	public Patient() {
		name = "";
		appointments = null;
	}
	
	/**
	 * Gets the Patient name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the Diary tree of the Patient 
	 * @return Diary appointments
	 */
	public Diary getAppointments() {
		return appointments;
	}
	
}
