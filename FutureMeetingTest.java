/**
 * JUnit4 tests for the FutureMeeting interface
 *
 * @author ocouls01
 */

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class FutureMeetingTest {
	private FutureMeeting testFutureMeeting;
	private Calendar testFutureDate;
	private Set<Contact> testSet;
	
	@Before
	public void setUp() {
		testSet = new HashSet<Contact>();
		testSet.add(new ContactImpl(1, "Albert Einstein"));
		testSet.add(new ContactImpl(2, "Alan Turing"));
		testSet.add(new ContactImpl(3, "Richard Feynman"));
		
		testFutureDate = new GregorianCalendar(2020, 5, 13, 13, 0);
		testFutureMeeting = new FutureMeetingImpl(29, testFutureDate, testSet);
		
	}
	
	@After
	public void breakDown() {
		testFutureDate = null;
		testSet = null;
		testFutureMeeting = null;
		
	}
	
	@Test
	public void testConstruction() {
		FutureMeeting output = new FutureMeetingImpl(1, testFutureDate, testSet);
		assertNotNull(output);
		
		assertEquals(1, output.getId());
		assertEquals(testFutureDate, output.getDate());
		assertEquals(testSet, output.getContacts());
		
	}
}