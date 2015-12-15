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
	
	/**
	 * Returns the name of the contact.
	 *
	 * @return the name of the contact.
	 */
	public String getName() {
		return "";
	}

	/**
	 * Returns our notes about the contact, if any.
	 *
	 * If we have not written anything about the contact, the empty
	 * string is returned.
	 *
	 * @return a string with notes about the contact, maybe empty.
	 */
	public String getNotes() {
		return "";
	}
}