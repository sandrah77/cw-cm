/**
 * JUnit4 tests for the PastMeeting interface
 *
 * @author ocouls01
 */

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class PastMeetingTest {
	
	private Calendar testPastDate;
	private Set<Contact> testSet;
	
	@Before
	public void setUp() {
		testSet = new HashSet<Contact>();
		testSet.add(new ContactImpl(1, "Albert Einstein"));
		testSet.add(new ContactImpl(2, "Alan Turing"));
		testSet.add(new ContactImpl(3, "Richard Feynman"));
		
		testPastDate = new GregorianCalendar(2012, 5, 13, 13, 0);

	}
	
	@After
	public void breakDown() {
		testPastDate = null;
		testSet = null;
	}
	
	@Test
	public void testConstruction() {
		PastMeeting output = new PastMeetingImpl(1, testPastDate, testSet, "Test notes");
		assertNotNull(output);
		
		assertEquals(1, output.getId());
		assertEquals(testPastDate, output.getDate());
		assertEquals(testSet, output.getContacts());
		
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testConstructionWithEmptySetOfContacts() {
		Set<Contact> emptySet = new HashSet<Contact>();
		
		PastMeeting output = new PastMeetingImpl(1, testPastDate, emptySet, "Test notes");
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testConstructionWithIdOfZero() {
				
		PastMeeting output = new PastMeetingImpl(0, testPastDate, testSet, "Test notes");
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testConstructionWithNegativeId() {
				
		PastMeeting output = new PastMeetingImpl(-2, testPastDate, testSet, "Test notes");
	}
	
		
	@Test (expected = NullPointerException.class) 
	public void testContstructionWithNullDate() {
		PastMeeting output = new PastMeetingImpl(1, null, testSet, "Test notes");
	}
	
	@Test (expected = NullPointerException.class) 
	public void testContstructionWithNullContacts() {
		PastMeeting output = new PastMeetingImpl(1, testPastDate, null, "Test notes");
	}
	
	@Test (expected = NullPointerException.class) 
	public void testContstructionWithNullNotes() {
		PastMeeting output = new PastMeetingImpl(1, testPastDate, testSet, null);
	}
}