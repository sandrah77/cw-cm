import java.util.Calendar;
import java.util.Set;

/**
 * A meeting to be held in the future
 *
 * @author ocouls01
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting{
	// No methods here, this is just a naming interface
	// (i.e. only necessary for type checking and/or downcasting)
	
	public FutureMeetingImpl(int ID, Calendar date, Set<Contact> contacts) {
		super(ID, date, contacts);
	}
}