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
import java.util.Collections;

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
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws NullPointerException,
																			IllegalArgumentException {
		//Check that all contacts are known
					
		if ((date == null) || (contacts == null)) {
			throw new NullPointerException("Provide a set of contacts and a Calendar date");
		// if date.compareTo(presentDate) is negative, then date is before presentDate
		} else if (date.compareTo(presentDate) < 0) {
			throw new IllegalArgumentException("Date provided is in the past, must be a future date");
		} else if (contacts.size() == 0) {
			throw new IllegalArgumentException("Must provide at least 1 contact");
		} else if (getNumberOfValidContacts(contacts) != contacts.size()) {
			throw new IllegalArgumentException("One or more of specified contacts is unknown");
		} else if (meetings.isEmpty()){
			
				
				meetings.add(new FutureMeetingImpl(1, date, contacts));
				return 1;
		} else {
			int newID = generateNewMeetingID();
			meetings.add(new FutureMeetingImpl(newID, date, contacts));
			return newID;
		}		
	}
		
	/**
	 * Returns the PAST meeting with the requested ID, or null if it there is none.
	 *
	 * The meeting must have happened at a past date.
	 *
	 * @param id the ID for the meeting
	 * @return the meeting with the requested ID, or null if it there is none.
	 * @throws IllegalStateException if there is a meeting with that ID happening
	 * in the future
	 */
	public PastMeeting getPastMeeting(int id) throws IllegalStateException{
				
		for (Meeting m : meetings) {
			if (m.getId() == id) {
				if (m instanceof FutureMeeting) {
					throw new IllegalStateException("Specified ID refers to a FutureMeeting");
				} else {
					return (PastMeeting) m;
				}
			}  
		}
		return null;
	}
	
	/**
	 * Returns the FUTURE meeting with the requested ID, or null if there is none.
	 *
	 * @param id the ID for the meeting
	 * @return the meeting with the requested ID, or null if it there is none.
	 * @throws IllegalArgumentException if there is a meeting with that ID happening
	 * in the past
	 */
	public FutureMeeting getFutureMeeting(int id) throws IllegalArgumentException {
		
		for (Meeting m : meetings) {
			if (m.getId() == id) {
				if (m instanceof PastMeeting) {
					throw new IllegalArgumentException("Meeting with specified ID is a past meeting");
				} else {
					return (FutureMeeting) m;
				}
				
			}
		}
		return null;
	
	}
	
	/**
	 * Returns the meeting with the requested ID, or null if it there is none.
	 *
	 * @param id the ID for the meeting
	 * @return the meeting with the requested ID, or null if it there is none.
	 */
	public Meeting getMeeting(int id) {
		for (Meeting m : meetings) {
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Returns the list of future meetings scheduled with this contact.
	 *
	 * If there are none, the returned list will be empty. Otherwise,
	 * the list will be chronologically sorted and will not contain any
	 * duplicates.
	 *
	 * @param contact one of the users contacts
	 * @return the list of future meeting(s) scheduled with this contact (maybe empty).
	 * @throws IllegalArgumentException if the contact does not exist
	 * @throws NullPointerException if the contact is null
	 */
	public List<Meeting> getFutureMeetingList(Contact contact) throws NullPointerException, 
																	IllegalArgumentException { 
		boolean validContact = false;
		for (Contact c : contacts) {
			if (c.equals(contact)) {
				validContact = true;
			}
		}
		
		if (contact == null) {
			throw new NullPointerException("Must provide a contact to search for");
		} else if (!validContact) {
			throw new IllegalArgumentException("Contact provided does not exist in this manager");
		} else {
			List<Meeting> sortedOutput = new ArrayList<Meeting>();
		
			//Make the unsorted list a set to avoid duplicates
			Set<Meeting> unsortedOutput = new HashSet<Meeting>();
			for (Meeting m : meetings) {
				if ((m instanceof FutureMeeting) && (m.getContacts().contains(contact))) {
					unsortedOutput.add(m);
				}
			}
			//Move them to a list
			for (Meeting m : unsortedOutput){
				sortedOutput.add(m);
			}
			
			//Use a lambda expression to compare the elements in the sort function.
			
			Collections.sort(sortedOutput, (a,b) -> a.getDate().compareTo(b.getDate()));
			return sortedOutput;
		}
	}
	
	/**
	 * Returns the list of meetings that are scheduled for, or that took
	 * place on, the specified date (i.e. day)
	 *
	 * If there are none, the returned list will be empty. Otherwise,
	 * the list will be chronologically sorted and will not contain any
	 * duplicates.
	 *
	 * @param date the date
	 * @return the list of meetings
	 * @throws NullPointerException if the date are null
	 */
	public List<Meeting> getMeetingListOn(Calendar date) throws NullPointerException {
		if (date == null) {
			throw new NullPointerException("Must provide a date to search for");
		}
		
		List<Meeting> sortedOutput = new ArrayList<Meeting>();
		Set<Meeting> unsortedOutput = new HashSet<Meeting>();
		for (Meeting m : meetings) {
			//May be some issues here
			if (date.get(Calendar.ERA) == m.getDate().get(Calendar.ERA) &&
                date.get(Calendar.YEAR) == m.getDate().get(Calendar.YEAR) &&
                date.get(Calendar.DAY_OF_YEAR) == m.getDate().get(Calendar.DAY_OF_YEAR)) {
				unsortedOutput.add(m);
			}
		}
		for (Meeting m : unsortedOutput) {
			sortedOutput.add(m);
		}
		if (date.compareTo(presentDate) < 0) {
			Collections.sort(sortedOutput, (a,b) -> b.getDate().compareTo(a.getDate()));
		} else {
			Collections.sort(sortedOutput, (a,b) -> a.getDate().compareTo(b.getDate()));
		}
		
		return sortedOutput;
	}

	/**
	 * Returns the list of past meetings in which this contact has participated.
	 *
	 * If there are none, the returned list will be empty. Otherwise,
	 * the list will be chronologically sorted and will not contain any
	 * duplicates.
	 *
	 * @param contact one of the users contacts
	 * @return the list of future meeting(s) scheduled with this contact (maybe empty).
	 * @throws IllegalArgumentException if the contact does not exist
	 * @throws NullPointerException if the contact is null
	 */
	List<PastMeeting> getPastMeetingListFor(Contact contact);
	
	/**
 	 * Create a new record for a meeting that took place in the past.
	 * 
	 * @param contacts a list of participants
	 * @param date the date on which the meeting took place
	 * @param text messages to be added about the meeting, can be an empty String
	 * @throws IllegalArgumentException if the list of contacts is
	 * empty, or any of the contacts does not exist, or if the date is in the future.
	 *
	 * @throws NullPointerException if any of the arguments is null
	 */
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) throws IllegalArgumentException,
																							NullPointerException{
			
		//Will need to change Mock objects for real ones
		
		if ((date == null) || (contacts == null) || (text == null)) {
			throw new NullPointerException("Provide a set of contacts, a Calendar date" +
																" and notes about the meeting");
		// if date.compareTo(presentDate) is negative, then date is before presentDate
		} else if (date.compareTo(presentDate) > 0) {
			throw new IllegalArgumentException("Date provided is in the future, must be a past date");
		} else if (contacts.size() == 0) {
			throw new IllegalArgumentException("Must provide at least 1 contact");
		} else if (getNumberOfValidContacts(contacts) != contacts.size()) {
			throw new IllegalArgumentException("One or more of specified contacts is unknown");
		} else if (meetings.isEmpty()){
				meetings.add(new PastMeetingImpl(1, date, contacts, text));
				
		} else {
			int newID = generateNewMeetingID();
			
			meetings.add(new PastMeetingImpl(newID, date, contacts, text));
			
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
	 * Returns all PastMeetings currently in the List
	 * for testing
	 *
	 * @return the List of all PastMeeting
	 */
	
	public List<PastMeeting> getAllPastMeetings() {
		List<PastMeeting> output = new ArrayList<PastMeeting>();
		
		for (Meeting m : meetings) {
			if (m instanceof PastMeeting) {
				output.add((PastMeeting) m);
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
	
	
	

	//Get the highest ID value currently in the set and add 1 for the newID
	
	private int generateNewMeetingID() {
		
		int newID = 0;
		for (Meeting m : meetings) {
			if (m.getId() > newID) {
				newID = m.getId();
			}
		}
		newID++;
		return newID;
	}
	
	//Compare each contact given to the set of contacts in this Contact Manager
	
	private int getNumberOfValidContacts(Set<Contact> contacts) {
		int validContacts = 0;
		for (Contact c : this.contacts) {
			for (Contact c2 : contacts) {
				if ((c.getId() == c2.getId()) && (c.getName().equals(c2.getName()))) {
					validContacts ++;
				}
			}	
		}
		return validContacts;
	}
}