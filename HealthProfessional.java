/**
 * 
 * Stores details about individual hospital employees
 *
 */
public class HealthProfessional {
	int id=0;
	String name;
	String profession;
	String location;
	Diary appointments;
	
	/**
	 * Default constructor that creates a blank instance of Health{rofessional
	 */
	public HealthProfessional() {
		id++;
		name = "";
		profession = "";
		location = "";
		appointments = null;
	}
	
	/**
	 * Gets the name of the HealthProfessional 
	 * @return String name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the profession of the HealthProfessional 
	 * @return String profession
	 */
	public String getProfession() {
		return profession;
	}
	
	/**
	 * Gets the location within hospital of the HealthProfessional 
	 * @return String location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * Gets the Diary tree of the HealthProfessional 
	 * @return Diary appointments
	 */
	public Diary getAppointments() {
		return appointments;
	}

}
