/**
 * Class implementing ContactManager.
 *
 * @author ocouls01
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ContactManagerImpl implements ContactManager{
	private Set<Contact> contacts;
	private List<Meeting> meetings;
	private Calendar presentDate;
	
	public ContactManagerImpl() {
		//This set of contacts will need to be imported from a file
		contacts = new HashSet<Contact>();
		
		//This list will need to be read from file
		meetings = new ArrayList<Meeting>();
		
		presentDate = new GregorianCalendar();
	}
	
	/**
	 * Add a new meeting to be held in the future.
	 *
	 * An ID is returned when the meeting is put into the system. This
	 * ID must be positive and non-zero.
	 *
	 * @param contacts a list of contacts that will participate in the meeting
	 * @param date the date on which the meeting will take place
	 * @return the ID for the meeting
	 * @throws IllegalArgumentException if the meeting is set for a time
	 * in the past, of if any contact is unknown / non-existent.
	 * @throws NullPointerException if the meeting or the date are null
	 */
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws NullPointerException {
		//Check that all contacts are known
		int validContacts = 0;
		for (Contact c : this.contacts) {
			for (Contact c2 : contacts) {
				if ((c.getId() == c2.getId()) && (c.getName().equals(c2.getName()))) {
					validContacts ++;
				}
			}	
		}
			
		if ((date == null) || (contacts == null)) {
			throw new NullPointerException("Provide a set of contacts and a Calendar date");
		// if date.compareTo(presentDate) is negative, then date is before presentDate
		} else if (date.compareTo(presentDate) < 0) {
			throw new IllegalArgumentException("Date provided is in the past, must be a future date");
		} else if (validContacts != contacts.size()) {
			throw new IllegalArgumentException("One or more of specified contacts is unknown");
		} else if (meetings.isEmpty()){
			
				//Will need to change Mock objects to real objects
				meetings.add(new MockFutureMeeting(1, date, contacts));
				return 1;
		} else {
			//Get the highest ID value currently in the set and add 1 for the newID
			
			//put in private method - repeated code
			int newID = 0;
			for (Meeting m : meetings) {
				if (m.getId() > newID) {
					newID = m.getId();
				}
			}
			newID++;
			meetings.add(new MockFutureMeeting(newID, date, contacts));
			return newID;
		}		
	}
	
	/**
	 * Returns the FUTURE meeting with the requested ID, or null if there is none.
	 *
	 * @param id the ID for the meeting
	 * @return the meeting with the requested ID, or null if it there is none.
	 * @throws IllegalArgumentException if there is a meeting with that ID happening
	 * in the past
	 */
	public FutureMeeting getFutureMeeting(int id) {
		return null;
	}
	
	/**
 	 * Create a new record for a meeting that took place in the past.
	 *
	 * @param contacts a list of participants
	 * @param date the date on which the meeting took place
	 * @param text messages to be added about the meeting.
	 * @throws IllegalArgumentException if the list of contacts is
	 * empty, or any of the contacts does not exist
	 *
	 * @throws NullPointerException if any of the arguments is null
	 */
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		
		//Will need to change Mock objects for real ones
		
		//Will need to add checks for exceptions
		
		if (meetings.isEmpty()){
				meetings.add(new MockPastMeeting(1, date, contacts, text));
				return 1;
		} else {
			//Get the highest ID value currently in the set and add 1 for the newID
			
			//put in private method - repeated code
			int newID = 0;
			for (Meeting m : meetings) {
				if (m.getId() > newID) {
					newID = m.getId();
				}
			}
			newID++;
			meetings.add(new MockPastMeeting(newID, date, contacts, notes));
			return newID;
		}
	}
	
	/**
	 * Create a new contact with the specified name and notes.
	 * Generates an ID which is returned, based on the IDs of all existing
	 * contacts or 1 if the set of contacts is empty.
	 *
	 * @param name the name of the contact.
	 * @param notes notes to be added about the contact.
	 * @return the ID for the new contact
	 * @throws IllegalArgumentException if the name or the notes are empty strings
	 * @throws NullPointerException if the name or the notes are null
	 */
	public int addNewContact(String name, String notes) throws IllegalArgumentException, 
																	NullPointerException{
		int newID;
		
		if ((name.length() == 0) || (notes.length() == 0)) {
			
			throw new IllegalArgumentException("Must provide a valid contact name and notes");
			
		} else if ((name == null) || (notes == null)) {
			
			throw new NullPointerException("Must provide a valid contact name and notes");
			
		} else if (contacts.isEmpty()) {
			
			newID = 1;
			contacts.add(new ContactImpl(newID, name, notes));
			
		} else {
			//Get the highest ID value currently in the set and add 1 for the newID
			newID = 0;
			for (Contact c : contacts) {
				if (c.getId() > newID) {
					newID = c.getId();
				}
			}
			newID++; 
			contacts.add(new ContactImpl(newID, name, notes));
			
		}
				
		return newID;
	}
	
	/**
	 * Returns a list with the contacts whose name contains that string.
	 *
	 * If the string is the empty string, this methods returns the set
	 * that contains all current contacts.
	 *
	 * @param name the string to search for
	 * @return a list with the contacts whose name contains that string.
	 * @throws NullPointerException if the parameter is null
	 */
	public Set<Contact> getContacts(String name) throws NullPointerException{
		Set<Contact> output = new HashSet<Contact>();
		
		if (name == null) {
			throw new NullPointerException("Must provide a name to search for, or an empty String");
		
		} else if (name.equals("")) {
			return contacts;
		
		} else {
			for(Contact c : contacts) {
				if (c.getName().contains(name)) {
					output.add(c);
				}
			}
			return output;
		}
	}
	
	/**
	 * Returns a list containing the contacts that correspond to the IDs.
	 * Note that this method can be used to retrieve just one contact by passing only one ID.
	 *
	 * @param ids an arbitrary number of contact IDs
	 * @return a list containing the contacts that correspond to the IDs.
	 * @throws IllegalArgumentException if no IDs are provided or if
	 * any of the provided IDs does not correspond to a real contact
	 */
	public Set<Contact> getContacts(int... ids) throws IllegalArgumentException {
		if (ids == null || ids.length == 0) {
			throw new IllegalArgumentException("Must provide at least 1 ID to search for");			
		}
		
		//Check for duplicates in the ids array
		for (int i = 0; i < ids.length; i++) {
			for (int j = i+1; j < ids.length; j++)	{
				if (i!=j && (ids[i] == ids[j])) {
					throw new IllegalArgumentException("Duplicate Ids provided");
				}
			}		
		}
		
		//Check that the contacts with the provided ids exist in the set
		int numberOfValidIds = 0;
		for (Contact c : getContacts("")) {
			for (int i : ids) {
				if (c.getId() == i) {
					numberOfValidIds++;
				}
			}
		}
		if (numberOfValidIds != ids.length) {
			throw new IllegalArgumentException("One or more of provided IDs does" + 
												" not correspond to a real contact");
		}
		
		//Create an output set and populate it for return
		Set <Contact> output = new HashSet<Contact>();
		
		for (Contact c : getContacts("")) {
			for (int i : ids) {
				if (c.getId() == i) {
					output.add(c);
				}
			}
		}
		return output;
			
		
	}
	
	/**
	 * Returns all contacts currently in the contacts Set
	 * For testing
	 *
	 * @return the Set of all contacts
	 */
	
	public Set<Contact> getAllContacts() {
		return contacts;
	}
	
	/**
	 * Returns all FutureMeetings currently in the List
	 * for testing
	 *
	 * @return the List of all FutureMeetings
	 */
	
	public List<FutureMeeting> getAllFutureMeetings() {
		List<FutureMeeting> output = new ArrayList<FutureMeeting>();
		
		for (Meeting m : meetings) {
			if (m instanceof FutureMeeting) {
				output.add((FutureMeeting) m);
			} 
		}
		return output;
	}
	
	
	/**
	 * Returns all Meetings currently in the List
	 * for testing
	 *
	 * @return the List of all Meetings
	 */
	
	public List<Meeting> getAllMeetings() {
		return meetings;
	}
	
}