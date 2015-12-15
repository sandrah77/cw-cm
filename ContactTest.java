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
		testContact = new ContactImpl(, "TestName", "TestNotes");
	} */
	
	@Test
	public void testContstructionWithAllParams() {
		Contact myContact = new ContactImpl(1, "Sam Wilson", "Eyes like a hawk");
		assertNotNull(myContact);
	}
	
	@Test
	public void testConstructionWithIdAndName() {
		Contact myContact = new ContactImpl(1, "Steve Rogers");
		assertNotNull(myContact);
			
	}
	
	@Test
	public void testGetId() {
		//3 parameter constructor
		Contact myContact = new ContactImpl(1, "Sam Wilson", "Eyes like a hawk");
		assertEquals(1, myContact.getId());
		
		//2 parameter constructor
		Contact myContact2 = new ContactImpl(1, "Steve Rogers");
		assertEquals(1, myContact2.getId());
		
	}
	
	@Test
	public void testGetName() {
		//3 parameter constructor
		Contact myContact = new ContactImpl(1, "Sam Wilson", "Eyes like a hawk");
		assertEquals("Sam Wilson", myContact.getName());
		
		//2 parameter constructor
		Contact myContact2 = new ContactImpl(1, "Steve Rogers");
		assertEquals("Sam Wilson", myContact2.getName());
	}
	
	@Test
	public void testGetNotes() {
		Contact myContact = new ContactImpl(1, "Sam Wilson", "Eyes like a hawk");
		
		assertEquals("Eyes like a hawk", myContact.getNotes());
	}
		
		
		
}
