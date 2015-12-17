import java.util.Calendar;
import java.util.Set;
/**
 * A class to represent meetings
 *
 * Meetings have unique IDs, scheduled date and a list of participating contacts
 * @author ocouls01
 */
public abstract class MeetingImpl implements Meeting {
	private int ID;
	private Calendar date;
	private Set<Contact> contacts;

	public MeetingImpl(int ID, Calendar date, Set<Contact> contacts) {
		this.ID = ID;
		this.date = date;
		this.contacts = contacts;
	}	
	/**
	 * Returns the id of the meeting.
	 *
	 * @return the id of the meeting.
 	 */
	public int getId() {
		return ID;
	}
	/**
	 * Return the date of the meeting.
	 *
	 * @return the date of the meeting.
	 */
	public Calendar getDate() {
		return date;
	}
	
	/**
	 * Return the details of people that attended the meeting.
	 *
	 * The list contains a minimum of one contact (if there were
	 * just two people: the user and the contact) and may contain an
	 * arbitrary number of them.
	 *
	 * @return the details of people that attended the meeting.
	 */
	public Set<Contact> getContacts() {
		return contacts;
	}
}