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
		assertTrue(testCM.getAllContacts().isEmpty());
	}
	
	
}