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
	 * Generates an ID which is returned, based on the IDs of all existing
	 * contacts. Or 1 if the set of contacts is empty.
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
			contacts.add(new MockContact(newID, name, notes));
			
		} else {
			newID = 0;
			for (MockContact c : contacts) {
				if (c.getId() > newID) {
					newID = c.getId();
				}
			}
			newID++; 
			contacts.add(new MockContact(newID, name, notes));
			
		}
				
		return newID;
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