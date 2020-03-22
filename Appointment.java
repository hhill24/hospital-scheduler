import java.util.Date;

/**
 * 
 * SAME AS TREE NODE CLASS
 *
 */
public class Appointment {
	Date date;
	Date endTime;
	String location;
	Patient patient;
	String treatment;
	
	public Appointment() {
		date=null;
		endTime=null;
		location="";
		patient=null;
		treatment="";
	}
	

	public Date getDate() {
		//comment test
		return date;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public String getLocation() {
		return location;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public String getTreatment() {
		return treatment;
	}
	
}
