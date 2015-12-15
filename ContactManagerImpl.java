/**
 * Class implementing ContactManager.
 *
 * @author ocouls01
 */

import java.util.Set;
import java.util.HashSet;

public class ContactManagerImpl implements ContactManager{
	private Set<MockContact> contacts;
	
	public ContactManagerImpl() {
		contacts = new HashSet<MockContact>();
	}
	
	/**
	 * Create a new contact with the specified name and notes.
	 *
	 * @param name the name of the contact.
	 * @param notes notes to be added about the contact.
	 * @return the ID for the new contact
	 * @throws IllegalArgumentException if the name or the notes are empty strings
	 * @throws NullPointerException if the name or the notes are null
	 */
	public int addNewContact(String name, String notes) {
		contacts.add(new MockContact(1, name, notes));
		return 1;
	}
	
	/**
	 * Returns all contacts currently in the contacts Set
	 *
	 * @return the Set of all contacts
	 */
	
	public Set<MockContact> getAllContacts() {
		return contacts;
	}
	
}