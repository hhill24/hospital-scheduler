 import java.util.Date;

/**
 * 
 * Each instance of Appointment class is a node in the binary search tree Diary class
 *
 */
public class Appointment {
    Date date;
    String location;
    Patient patient;
    String treatment;
    int ID=0;
    private Appointment leftNode;
    private Appointment rightNode;
    
    /**
     * Default constructor that creates a blank instance of Appointment
     */
    public Appointment() {
        date=null;
        location="";
        patient=null;
        treatment="";
        ID ++;
        leftNode=null;
        rightNode=null;
        
    }
    
    /**
     * Alternative constructor that sets values to given parameters
     * @param theDate
     * @param theLoc
     * @param patient
     * @param theTreatment
     */
    public Appointment(Date theDate, String theLoc, Patient patient, String theTreatment) {
        date=null;
        location="";
        patient=null;
        treatment="";
        ID ++;
        leftNode=null;
        rightNode=null; 
    }

    /**
     * Gets the Appointment node to the left
     * @return Appointment leftNode 
     */
    public Appointment getLeft() {
        return leftNode;
    }
    
    /**
     * Gets the Appointment node to the right
     * @return Appointment rightNode 
     */
    public Appointment getRight() {
        return rightNode;
    }
    
    /**
     * Gets the date of the appointment (including start time)
     * @return Date 
     */
    public Date getDate() {
        
        return date;
    }
    
    /**
     * Gets the unique Appointment Id
     * @return integer ID
     */
    public int getID() {
        return ID;
    }
    
    /**
     * Gets the Appointment location
     * @return String location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Gets the Patient instance associated with the appointment
     * @return Patient patient
     */
    public Patient getPatient() {
        return patient;
    }
    
    
    /**
     * Gets the Appointment treatment
     * @return String treatment 
     */
    public String getTreatment() {
        return treatment;
    }
    
    /**
     * Sets the Id of the Appointment
     * @param Date new date
     */
    public void setID(int newID) {
        ID = newID;
    }
    
   
    /**
     * Sets the date of the Appointment
     * @param Date new date
     */
    public void setDate(Date newDate) {
        date = newDate;
    }
    
    /**
     * Sets the Patient instance associated the Appointment
     * @param Patient new patient
     */
    public void setPatient(Patient newPatient) {
        patient = newPatient;
    }
    
    /**
     * Sets the location of the Appointment
     * @param String new location
     */
    public void setLocation(String newLocation) {
        location = newLocation;
    }
    
    /**
     * Sets the treatment type of the Appointment
     * @param String new treatment
     */
    public void setTreatment(String newTreatment) {
        treatment = newTreatment;
    }
    
    /**
     * Sets the Appointment left of this Appointment
     * @param Appointment new left node
     */
    public void setLeft(Appointment newLeftNode) {
        leftNode = newLeftNode;
    }
    
    /**
     * Sets the Appointment right of this Appointment
     * @param Appointment new right node
     */
    public void setRight(Appointment newRightNode) {
        rightNode = newRightNode;
    }
}
