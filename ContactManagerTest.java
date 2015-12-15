/**
 * JUnit4 tests for the ContactManager interface
 *
 * @author ocouls01
 */
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

public class ContactManagerTest {
	
	private ContactManager emptyCM;
	private ContactManager cManagerWithContacts;
	
	
	@Before
	public void setUp() {
		emptyCM = new ContactManagerImpl();
		cManagerWithContacts = new ContactManagerImpl();
		
		cManagerWithContacts.addNewContact("Sam Wilson", "Eyes like a hawk");
		cManagerWithContacts.addNewContact("Steve Rogers", "Claims to have fought in WW2");
		cManagerWithContacts.addNewContact("Bruce Wayne", "Tragic childhood, dubious night-time activities");
		cManagerWithContacts.addNewContact("Clark Kent", "Those glasses aren't fooling anyone");
		cManagerWithContacts.addNewContact("Peter Parker", "Mouthy but agile");
		cManagerWithContacts.addNewContact("Wade Wilson", "Knows he is not real");
		cManagerWithContacts.addNewContact("Wilson", "Volleyball");
		cManagerWithContacts.addNewContact("Wilson Fisk", "Pretty fast for a fat guy");
		cManagerWithContacts.addNewContact("Slade Wilson", "Not to be confused with a T-800");
		cManagerWithContacts.addNewContact("Bruce Banner", "Do NOT make him angry");
		
	}
	
	@Test
	public void testContstruction() {
		assertNotNull(emptyCM);
		assertTrue(((ContactManagerImpl) emptyCM).getAllContacts().isEmpty());
	}
	
	@Test
	public void testAddNewContact() {
		emptyCM.addNewContact("TestName", "TestNotes");
		
		assertEquals(1, ((ContactManagerImpl) emptyCM).getAllContacts().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNewContactWithEmptyNameParam() {
		emptyCM.addNewContact("", "TestNotes");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNewContactWithEmptyNotesParam() {
		emptyCM.addNewContact("TestName", "");
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNewContactWithNullNameParam() {
		String name = null;
		emptyCM.addNewContact(name, "TestNotes");
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNewContactWithNullNotesParam() {
		String notes = null;
		emptyCM.addNewContact("TestName", notes);
	}
	
	@Test
	public void testGetContactsWithEmptyStringParam() {
		Set<Contact> allContacts = cManagerWithContacts.getContacts("");
		assertEquals(((ContactManagerImpl) cManagerWithContacts).getAllContacts().size(), allContacts.size());
	}
	
	@Test
	public void testGetContactsWithStringParam() {
		String input = "Wilson";
		Set<Contact> wilsonContacts = cManagerWithContacts.getContacts(input);
		
		assertEquals(5,wilsonContacts.size());
	}
	
	@Test
	public void testGetContactsWithStringWhichDoesntMatch(){
		String input = "xxx";
		
		assertEquals(0, cManagerWithContacts.getContacts(input).size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetContactsWithNullParam() {
		String input = null;
		cManagerWithContacts.getContacts(input);
	}
}