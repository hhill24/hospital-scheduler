import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

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
    
    public boolean isDiaryEmpty()
    {
        return (root == null);
    }
    
    /**
     * deletes a single Appointment node from Diary tree
     */
    public Appointment deleteAppointment(Appointment newNode) {
        currentNode = newNode;
        
        if (findInDiary(newNode.getID()) == null)
        {
            return null;
        }
        
        if (newNode.getLeft() == null && newNode.getRight() == null && newNode == root)
        {
            root = null;
        }
        else if (newNode.getLeft() == null && newNode.getRight() == null)
        {
            if (previousNode.getLeft() == newNode)
            {
                previousNode.setLeft(null);
            }
            else if (previousNode.getRight() == newNode)
            {
                previousNode.setRight(null);
            }
        }
        else if (newNode.getLeft() != null && newNode.getRight() == null)
        {
            if (previousNode.getLeft() == newNode)
            {
                previousNode.setLeft(newNode.getLeft());
            }
            else if (previousNode.getRight() == newNode)
            {
                previousNode.setRight(newNode.getLeft());
            }
        }
        else if (newNode.getLeft() == null && newNode.getRight() != null)
        {
            if (previousNode.getLeft() == newNode)
            {
                previousNode.setLeft(newNode.getRight());
            }
            else if (previousNode.getRight() == newNode)
            {
                previousNode.setRight(newNode.getRight());
            }
        }
        else
        {
            currentNode = newNode.getLeft();
            
            while (currentNode.getRight() != null)
            {
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
        
        if (isDiaryEmpty())
        {
            root = a;
        }
        else
        {
            if (findInDiary(a.getID()) == null)
            {
                currentNode = root;
                previousNode = null;
                found = false;
            }
            while (!found && currentNode != null)
            {
                previousNode = currentNode;
                if (a.getID() > currentNode.getID())
                {
                    currentNode = currentNode.getRight();
                }
                else
                {
                    currentNode = currentNode.getLeft();
                }
            }
            if (!found)
            {
                if (a.getID() > previousNode.getID())
                {
                    previousNode.setRight(a);
                }
                else
                {
                    previousNode.setLeft(a);
                }
            }
        }
    }
    
    /**
     * edits fields of a single Appointment node in Diary tree
     */
    public void editAppointment() {
        Scanner s = new Scanner(System.in);
        Appointment foundAppointment;
        int searchID;
        
        System.out.println("Enter the ID of the appointment you want to edit: ");
        searchID = s.nextInt();
        
        if (findInDiary(searchID) == null)
        {
            System.out.println("The ID you entered could not be found.");
        }
        else
        {
            System.out.println("Enter new date: ");
            findInDiary(searchID).setTreatment(s.next());
            
            System.out.println("Enter new location: ");
            findInDiary(searchID).setTreatment(s.nextLine());
            
            System.out.println("Enter new patient: ");
            findInDiary(searchID).setTreatment(s.nextLine());
            
            System.out.println("Enter new treatment: ");
            findInDiary(searchID).setTreatment(s.nextLine());
        }
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

