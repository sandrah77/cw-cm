/**
 * Implementation of Contact interface
 * A contact is a person we are doing business with or may do in the future.
 *
 * Contacts have an ID (unique, a non-zero positive integer),
 * a name (not necessarily unique), and notes that the user
 * may want to save about them.
 *
 * @author ocouls01
 */

public class ContactImpl implements Contact{

	private int ID;
	private String name;
	private String notes;
	
	public ContactImpl(int ID, String name, String notes) {
		
	}
	public ContactImpl(int ID, String name) {
		
	}
	/**
	 * Returns the ID of the contact.
	 *
	 * @return the ID of the contact.
	 */
	public int getId() {
		return 0;
	}
}