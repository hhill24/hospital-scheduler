import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * 
 * Diary Class is a binary search tree with instances of Appointment class as
 * nodes
 * 
 *
 */
public class Diary {

	HealthProfessional owner;
	Appointment root;
	Appointment currentNode;
	Appointment previousNode;
	Set<Date> busy = new HashSet<Date>();

	/**
	 * Default constructor creates a blank instance of Diary
	 */
	public Diary() {
		root = null;
		
	}

	/**
	 * Gets the instance of HealthProfessional associated with this Diary
	 * 
	 * @return HealthProfessional owner
	 */
	public HealthProfessional getOwner() {
		return owner;
	}

	/**
	 * sets the owner of the diary to given parameter
	 * 
	 * @param HealthProfessional hp
	 */
	public void setOwner(HealthProfessional hp) {
		owner = hp;
	}

	/**
	 * Checks if the diary tree has no nodes
	 * 
	 * @return true if empty
	 */
	public boolean isDiaryEmpty() {
		return (root == null);
	}

	/**
	 * deletes a single Appointment node from Diary tree
	 */
	public Appointment deleteAppointment(Appointment newNode) {
		currentNode = newNode;

		if (findInDiary(newNode.getID()) == null) {
			return null;
		}

		if (newNode.getLeft() == null && newNode.getRight() == null && newNode == root) {
			root = null;
		} else if (newNode.getLeft() == null && newNode.getRight() == null) {
			if (previousNode.getLeft() == newNode) {
				previousNode.setLeft(null);
			} else if (previousNode.getRight() == newNode) {
				previousNode.setRight(null);
			}
		} else if (newNode.getLeft() != null && newNode.getRight() == null) {
			if (previousNode.getLeft() == newNode) {
				previousNode.setLeft(newNode.getLeft());
			} else if (previousNode.getRight() == newNode) {
				previousNode.setRight(newNode.getLeft());
			}
		} else if (newNode.getLeft() == null && newNode.getRight() != null) {
			if (previousNode.getLeft() == newNode) {
				previousNode.setLeft(newNode.getRight());
			} else if (previousNode.getRight() == newNode) {
				previousNode.setRight(newNode.getRight());
			}
		} else {
			currentNode = newNode.getLeft();

			while (currentNode.getRight() != null) {
				currentNode = currentNode.getRight();
			}

			Appointment placeholder = new Appointment();

			placeholder.setDate(currentNode.getDate());
			placeholder.setID(currentNode.getID());
			placeholder.setLocation(currentNode.getLocation());
			placeholder.setID(currentNode.getID());
			placeholder.setPatient(currentNode.getPatient());
			placeholder.setTreatment(currentNode.getTreatment());

			deleteAppointment(currentNode);

			newNode.setDate(currentNode.getDate());
			newNode.setID(currentNode.getID());
			newNode.setLocation(currentNode.getLocation());
			newNode.setID(currentNode.getID());
			newNode.setPatient(currentNode.getPatient());
			newNode.setTreatment(currentNode.getTreatment());
		}

		return newNode;
	}

	/**
	 * adds a single Appointment node to Diary tree
	 */
	public void addAppointment(Appointment a) {
		boolean found = false;

		if (isDiaryEmpty()) {
			root = a;
		} else {
			if (findInDiary(a.getID()) == null) {
				currentNode = root;
				previousNode = null;
				found = false;
			}
			while (!found && currentNode != null) {
				previousNode = currentNode;
				if (a.getID() > currentNode.getID()) {
					currentNode = currentNode.getRight();
				} else {
					currentNode = currentNode.getLeft();
				}
			}
			if (!found) {
				if (a.getID() > previousNode.getID()) {
					previousNode.setRight(a);
				} else {
					previousNode.setLeft(a);
				}
			}
		}
	}

	/**
	 * edits fields of a single Appointment node in Diary tree
	 * 
	 * @return the Appointment with unedited fields needed for undo implementation
	 */
	public Appointment editAppointment() {
		Scanner s = new Scanner(System.in);
		Appointment previousValues = null;
		int searchID;

		System.out.println("Enter the ID of the appointment you want to edit: ");
		searchID = s.nextInt();

		if (findInDiary(searchID) == null) {
			System.out.println("The ID you entered could not be found.");
		} else {
			previousValues = findInDiary(searchID);
			System.out.println("Enter new date: ");
			findInDiary(searchID).setTreatment(s.next());

			System.out.println("Enter new location: ");
			findInDiary(searchID).setTreatment(s.next());

			System.out.println("Enter new patient: ");
			findInDiary(searchID).setTreatment(s.next());

			System.out.println("Enter new treatment: ");
			findInDiary(searchID).setTreatment(s.next());
		}
		return previousValues;
	}

	/**
	 * Traverses Diary comparing appointment IDs to wanted id until found Returns
	 * null if not found
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

	/**
	 * Creates a set of the times when an employee has appointments booked
	 * 
	 * @param node
	 * @return
	 */
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
	 * Prints all appointment nodes in the diary tree
	 * 
	 * @param node
	 * @return
	 */
	public void printTree(Appointment node) {

		if (node != null) {
			currentNode = node.getLeft();
			printTree(currentNode);
			System.out.println(node.getID() + "," + node.getLocation() + "," + node.getTreatment() + "," + node.getDate() + ","+ node.getPatient().getName());
			currentNode = node.getRight();
			printTree(currentNode);
		}

		
	}


	/**
	 * saves the Diary to an external file
	 * Recursive function
	 * @throws IOException
	 */
	public void save(Appointment a, String id) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(id + "_diary.txt", true));

		if (isDiaryEmpty()) {
			System.out.println("Empty Diary");
		}
		if (a != null) {
			currentNode = a.getLeft();
			save(currentNode, id);

			bw.write(a.getID() + "," + a.getLocation() + "," + a.getTreatment() + "," + a.getDate() + ","+ a.getPatient().getName());
			bw.flush();
			bw.newLine();

			currentNode = a.getRight();
			save(currentNode, id);
		}

		bw.close();
	}

	/**
	 * loads a Diary from an external file
	 * 
	 * @throws IOException
	 */
	public void load(String id) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(id + "_diary.txt"));

		String record;

		System.out.println("  ID\tLocation\tTreatment\tDate\tPatient Name\t\t\t");
		System.out.println(" ------------------------------------------------------------- ");

		while ((record = br.readLine()) != null) {

			StringTokenizer st = new StringTokenizer(record, ",");

			System.out.println("  " + st.nextToken() + "\t" + st.nextToken() + "\t" + st.nextToken() + "\t"
					+ st.nextToken() + "\t" + st.nextToken() + "\t\t\t");
		}

		System.out.println(" ------------------------------------------------------------- ");
		br.close();
	}

}
