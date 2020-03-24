import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * SAME AS BINARYTREE CLASS
 * USE DATE AS ID
 *
 */
public class Diary {
	HealthProfessional owner;
	Appointment root;
	Appointment currentNode;
	Appointment lastEditedNode;
	Appointment previousNode;
	Set<Date> busy;
	
	public Diary() {
		root = null;
		currentNode = root;
		lastEditedNode = null;
		previousNode = null;
		owner = null;
		busy = new HashSet<>();
	}
	
	
	public HealthProfessional getOwner() {
		return owner;
	}
	/**
	 * deletes a single Appointment node from Diary tree
	 */
	public void deleteAppointment() {
		
	}
	
	/**
	 * adds a single Appointment node to Diary tree
	 */
	public void addAppointment() {
		
	}
	
	/**
	 * edits fields of a single Appointment node in Diary tree
	 */
	public void editAppointment() {
		
	}
	
	/**
	 * Traverses Diary comparing appointment IDs to wanted id until found
	 *  Returns null if not found
	 * 
	 * @param wantedID
	 * @return found node or null
	 */
	public Appointment findInDiary(int wantedID) {
		Appointment upto = root;
		previousNode = null;
		boolean found = false;
		while (upto != null && !found) {
			if (upto.getID() == wantedID) {
				found = true;
			} else {
				if (wantedID < upto.getID()) {
					previousNode = upto;
					upto = upto.getLeft();
				} else {
					previousNode = upto;
					upto = upto.getRight();
				}
			}
		}
		if (found) {
			return upto;
		} else {
			return null;
		}
	}
	
	
	public Set<Date> createBusy(Appointment node) {

		if (node != null) {
			currentNode = node.getLeft();
			createBusy(currentNode);
			busy.add(node.getDate());
			currentNode = node.getRight();
			createBusy(currentNode);
		}

		return busy;
	}
	
	/**
	 * reverses the last action (edit, add or delete)
	 */
	public void undo() {
		
	}
	
	/**
	 * saves the Diary to an external file
	 */
	public void save() {
		
	}
	
	/**
	 * loads a Diary from an external file
	 */
	public void load() {
		
	}
	
	}
