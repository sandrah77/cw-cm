/**
 * JUnit4 tests for the Contact interface
 *
 * @author ocouls01
 */

import org.junit.*;
import static org.junit.Assert.*;

public class ContactTest {
	private Contact testContact;
	
	@Before
	public void setUp() {
		testContact = new ContactImpl(1, "TestName", "TestNotes");
	}
	
	@After
	public void breakDown() {
		testContact = null;
	}
	
	@Test
	public void testContstructionWithAllParams() {
		Contact myContact = new ContactImpl(1, "Sam Wilson", "Eyes like a hawk");
		assertNotNull(myContact);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void test3ParamConstructorWithIdOfZero() {
		Contact myContact = new ContactImpl(0, "Jon Stewart" , "A little green around the gills");
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void test3ParamConstructorWithNegativeId() {
		Contact myContact = new ContactImpl(-23, "Jon Stewart" , "A little green around the gills");
	}
	
	@Test(expected = NullPointerException.class) 
	public void test3ParamConstructorWithNullNamePointer() {
		String name = null;
		Contact myContact = new ContactImpl(1, name, "A whole lotta nothing");
	}
	
	@Test
	public void testConstructionWithIdAndName() {
		Contact myContact = new ContactImpl(1, "Steve Rogers");
		assertNotNull(myContact);
			
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void test2ParamConstructorWithIdOfZero() {
		Contact myContact = new ContactImpl(0, "Steve Rogers");
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void test2ParamConstructorWithNegativeId() {
		Contact myContact = new ContactImpl(-3, "Steve Rogers");
	}
	
	@Test(expected = NullPointerException.class) 
	public void test2ParamConstructorWithNullNamePointer() {
		String name = null;
		Contact myContact = new ContactImpl(1, name);
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
		assertEquals("Steve Rogers", myContact2.getName());
	}
	
	@Test
	public void testGetNotesForSingleNoteAddition() {
		//3 parameter constructor
		Contact myContact = new ContactImpl(1, "Sam Wilson", "Eyes like a hawk");
		
		assertEquals("Eyes like a hawk", myContact.getNotes());
		
		//2 Parameter constructor
		Contact myContact2 = new ContactImpl(1, "Steve Rogers");
		assertEquals("", myContact2.getNotes());
	}
		
		
	@Test	
	public void testAddNotes() {
		String initialNote = testContact.getNotes();
		
		testContact.addNotes("More notes");
		assertEquals(initialNote + "\n\nMore notes", testContact.getNotes());
	}
	
	@Test
	public void testAddNotesWithNullParam() {
		String initialNote = testContact.getNotes();
		testContact.addNotes(null);
		assertEquals(initialNote, testContact.getNotes());
	}
	
	@Test
	public void testAddNotesWithEmptyStringParam() {
		String initialNote = testContact.getNotes();
		testContact.addNotes("");
		assertEquals(initialNote, testContact.getNotes());
	}
}
