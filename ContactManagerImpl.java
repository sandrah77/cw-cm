/**
 * Class implementing ContactManager.
 *
 * @author ocouls01
 */

import java.util.Set;
import java.util.HashSet;

public class ContactManagerImpl implements ContactManager{
	private Set<Contact> contacts;
	
	public ContactManagerImpl() {
		contacts = new HashSet<Contact>();
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
			contacts.add(new ContactImpl(newID, name, notes));
			
		} else {
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
	 * Returns all contacts currently in the contacts Set
	 * For testing
	 *
	 * @return the Set of all contacts
	 */
	
	public Set<Contact> getAllContacts() {
		return contacts;
	}
	
	
}