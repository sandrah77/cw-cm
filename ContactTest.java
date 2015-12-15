/**
 * JUnit4 tests for the Contact interface
 *
 * @author ocouls01
 */

import org.junit.*;
import static org.junit.Assert.*;

public class ContactTest {
	/* private Contact testContact;
	
	@Before
	public void setUp() {
		testContact = new ContactImpl(0, "TestName", "TestNotes");
	} */
	
	@Test
	public void testContstructionWithAllParams() {
		Contact myContact = new ContactImpl(0, "Sam Wilson", "Eyes like a hawk");
		assertNotNull(myContact);
		assertEquals(0, myContact.getId());
		assertEquals("Sam Wilson", myContact.getName());
		assertEquals("Eyes like a hawk", myContact.getNotes());
	}
	
	@Test
	public void testConstructionWithIdAndName() {
		Contact myContact = new ContactImpl(0, "Steve Rogers");
		assertNotNull(myContact);
		assertEquals(0, myContact.getId());
		assertEquals("Steve Rogers", myContact.getName());
		
	}
	
	
}
