
public class Patient {
	String name;
	Diary appointments;
	
	public Patient() {
		name = "";
		appointments = null;
	}
	
	public String getName() {
		return name;
	}
	
	public Diary getAppointments() {
		return appointments;
	}
	
}
