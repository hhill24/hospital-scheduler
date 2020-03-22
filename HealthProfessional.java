
public class HealthProfessional {
	String name;
	String profession;
	String location;
	Diary appointments;
	
	public HealthProfessional() {
		name = "";
		profession = "";
		location = "";
		appointments = null;
	}
	
	public String getName() {
		return name;
	}
	
	public String getProfession() {
		return profession;
	}
	
	public String getLocation() {
		return location;
	}
	
	public Diary getAppointments() {
		return appointments;
	}

}
