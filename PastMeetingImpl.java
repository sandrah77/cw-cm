import java.util.Set;
import java.util.Calendar;
/**
 * A meeting that was held in the past.
 *
 * It includes your notes about what happened and what was agreed.
 * @author ocouls01
 */

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
	
	public PastMeetingImpl(int ID, Calendar date, Set<Contact> contacts, String notes) {
		super(ID, date, contacts);
	}
	/**
	 * Returns the notes from the meeting.
	 *
	 * If there are no notes, the empty string is returned.
	 *
	 * @return the notes from the meeting.
	 */
	public String getNotes() {
		return null;
	}
}