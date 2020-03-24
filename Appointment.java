 import java.util.Date;

/**
 * 
 * SAME AS TREE NODE CLASS
 *
 */
public class Appointment {
	Date date;
	//Date endTime;
	String location;
	Patient patient;
	String treatment;
	int ID=0;
	private Appointment leftNode;
	private Appointment rightNode;
	
	public Appointment() {
		date=null;
		//endTime=null;
		location="";
		patient=null;
		treatment="";
		ID ++;
		leftNode=null;
		rightNode=null;
		
	}
	

	public Appointment(Date theDate, String theLoc, Patient patient, String theTreatment) {
		date=null;
		//endTime=null;
		location="";
		patient=null;
		treatment="";
		ID ++;
		leftNode=null;
		rightNode=null; 
	}

	public Appointment getLeft() {
		return leftNode;
	}
	
	public Appointment getRight() {
		return rightNode;
	}
	
	public Date getDate() {
		//comment test
		return date;
	}
	
	public int getID() {
		return ID;
	}
	
//	public Date getEndTime() {
//		return endTime;
//	}
	
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
