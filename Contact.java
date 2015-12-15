/**
 * Interface for Contact
 * A contact is a person we are doing business with or may do in the future.
 *
 * Contacts have an ID (unique, a non-zero positive integer),
 * a name (not necessarily unique), and notes that the user
 * may want to save about them.
 *
 * @author ocouls01
 */

public interface Contact {
	/**
	 * Returns the ID of the contact.
	 *
	 * @return the ID of the contact.
	 */
	int getId();
}