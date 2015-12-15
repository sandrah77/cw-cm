/**
 * JUnit4 tests for the ContactManager interface
 *
 * @author ocouls01
 */

import org.junit.*;
import static org.junit.Assert.*;

public class ContactManagerTest {
	
	private ContactManager testCM;
	
	@Before
	public void setUp() {
		testCM = new ContactManagerImpl();
	}
	
	@Test
	public void testContstruction() {
		assertNotNull(testCM);
		assertTrue(((ContactManagerImpl) testCM).getAllContacts().isEmpty());
	}
	
	@Test
	public void testAddNewContact() {
		testCM.addNewContact("TestName", "TestNotes");
		
		assertEquals(1, ((ContactManagerImpl) testCM).getAllContacts().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNewContactWithEmptyNameParam() {
		testCM.addNewContact("", "TestNotes");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNewContactWithEmptyNotesParam() {
		testCM.addNewContact("TestName", "");
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNewContactWithNullNameParam() {
		String name = null;
		testCM.addNewContact(name, "TestNotes");
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNewContactWithNullNotesParam() {
		String notes = null;
		testCM.addNewContact("TestName", notes);
	}
	
}