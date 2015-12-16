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
	
	@After
	public void breakDown() {
		emptyCM = null;
		cManagerWithContacts = null;
	}
	
	@Test
	public void testContstruction() {
		assertNotNull(emptyCM);
		assertTrue(((ContactManagerImpl) emptyCM).getAllContacts().isEmpty());
	}
	
	//addNewContact() Tests
	
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
	
	//getContacts(String) tests
	
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
	
	// getContacts(int... ids) tests
	
	@Test
	public void testGetContactsWithArrayWhereContactsExist(){
		Set<Contact> output = cManagerWithContacts.getContacts(1,2,3,4,5);
		boolean present = false;
		
		for (Contact c : output) {
			if(c.getName().equals("Sam Wilson")) {
				present = true;
			} else if (c.getName().equals("Steve Rogers")) {
				present = true;
			} else if (c.getName().equals("Bruce Wayne")) {
				present = true;
			} else if (c.getName().equals("Clark Kent")) {
				present = true;
			} else if (c.getName().equals("Peter Parker")) {
				present = true;
			}
				
		}
	
		assertTrue(present);
	}
	
	@Test
	public void testGetContactsWithArrayWhereContactsExistOneContact() {
		
		Set<Contact> outputSet = cManagerWithContacts.getContacts(1);
		assertEquals(1, outputSet.size());
		Contact expected = null;
		for (Contact c : cManagerWithContacts.getContacts("")) {
			if (c.getName().equals("Sam Wilson")) {
				expected = c;
			}
		}
		
		Contact output = null;
		for (Contact c : outputSet) {
			if (c.getId() == 1) {
				output = c;
			}
		}
		
		assertEquals(expected, output);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetContactsWithArrayWhereContactsIdDoesntExist() {
		Set<Contact> output = cManagerWithContacts.getContacts(11,20);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetContactsWithArrayWhereParamIsNull() {
		Set<Contact> output = cManagerWithContacts.getContacts();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetContactsWithArrayWithRepeatedIdValue() {
		Set<Contact> output = cManagerWithContacts.getContacts(1,2,3,1);
	}
}