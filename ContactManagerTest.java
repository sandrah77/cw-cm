/**
 * JUnit4 tests for the ContactManager interface
 *
 * @author ocouls01
 */

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.io.File;

public class ContactManagerTest {
	
	private ContactManager emptyCM;
	private ContactManager cManagerWithContacts;
	private Set<Contact> testSet;
	private Set<Contact> testSet2;
	private Set<Contact> testSet3;
	private Calendar testFutureDate;
	private Calendar testPastDate;
	
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
		cManagerWithContacts.addNewContact("Matt Murdoch", "That dude is NOT blind");
		
		testSet = cManagerWithContacts.getContacts("Wilson");
		testSet2 = cManagerWithContacts.getContacts("Bruce");
		testSet3 = cManagerWithContacts.getContacts(1,4,5);
		
		
		testFutureDate = new GregorianCalendar(2020, 7, 29, 15, 0);
		testPastDate = new GregorianCalendar(2014, 10, 21);
		
				
	}
	
	
	@After
	public void breakDown() {
		emptyCM = null;
		cManagerWithContacts = null;
		testSet = null;
		testSet2 = null;
		testSet3 = null;
		testFutureDate = null;
		testPastDate = null;
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
	
	// Have implmemented it that null param returns illegal argument exception,
	// but this could be used instead.
	/* @Test (expected = NullPointerException.class)
	public void testGetContactsWithArrayWithNullArray() {
		int[] ids = null;
		Set<Contact> output = cManagerWithContacts.getContacts(ids);
	} */
	
	
	
	// addFutureMeeting tests
	@Test
	public void testAddFutureMeetingWithNoExistingMeetingsAndExistingContacts() {
		 
		//Set of contacts to be used is TestSet, date to be used is testFutureDate
		int output = cManagerWithContacts.addFutureMeeting(testSet, testFutureDate);
		assertEquals(1,output);
		assertEquals(1,((ContactManagerImpl) cManagerWithContacts).getAllFutureMeetings().size());
	}
	
	@Test
	public void testAddFutureMeetingsMultipleMeetings() {
		Calendar date1 = new GregorianCalendar(2020, 0, 12, 14, 0);
		Calendar date2 = new GregorianCalendar(2020, 4, 2, 11, 0);
		Calendar date3 = new GregorianCalendar(2017, 7, 23, 15, 0);
		Calendar date4 = new GregorianCalendar(2016, 11, 4, 10, 0);
		Calendar date5 = new GregorianCalendar(2018, 10, 11, 12, 0);
		
		int[] output = new int[5];
		output[0] = cManagerWithContacts.addFutureMeeting(testSet, date1);
		output[1] = cManagerWithContacts.addFutureMeeting(testSet, date2);
		output[2] = cManagerWithContacts.addFutureMeeting(testSet, date3);
		output[3] = cManagerWithContacts.addFutureMeeting(testSet, date4);
		output[4] = cManagerWithContacts.addFutureMeeting(testSet, date5);
		boolean greaterThanZero = true;
		
		for (int i = 0; i < output.length; i++) {
			if (output[i] <= 0) {
				greaterThanZero = false;
			}
		}
		assertTrue(greaterThanZero);
		
		boolean different = true;
		for (int i = 0; i < output.length; i++) {
			for (int j = i+1; j < output.length; j++) {
				if (i!=j && output[i]== output[j]) {
					different = false;
				}
			}
		}
		assertTrue(different);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddFutureMeetingWithNoExistingMeetingsAndContactsWhichDontExist() {
		Contact hank = new ContactImpl(100,"Hank MacCoy");
		Contact scott = new ContactImpl(101, "Scott Summers");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(hank);
		contacts.add(scott);
		
		int output = cManagerWithContacts.addFutureMeeting(contacts, testFutureDate);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddFutureMeetingWithPastDate() {
		
		cManagerWithContacts.addFutureMeeting(testSet, testPastDate);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddFutureMeetingEmptyContactsSet() {
		Set<Contact> emptySet = new HashSet<Contact>();
		
		cManagerWithContacts.addFutureMeeting(emptySet, testFutureDate);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddFutureMeetingWithNullDate() {
		cManagerWithContacts.addFutureMeeting(testSet, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddFutureMeetingWithNullSetOfContacts() {
		cManagerWithContacts.addFutureMeeting(null, testFutureDate);
	} 
	
	
	// getFutureMeeting Tests
	
	@Test
	public void testGetFutureMeetingWhichExists() {
		int futureMeetingID = cManagerWithContacts.addFutureMeeting(testSet, testFutureDate);
		
		FutureMeeting output = cManagerWithContacts.getFutureMeeting(futureMeetingID);
		assertNotNull(output);
		assertEquals(futureMeetingID, output.getId()); 
		
		Set<Contact> outputSet = output.getContacts();
		Calendar outputDate = output.getDate();
		
		assertTrue(outputSet.equals(testSet));
		
		assertTrue(outputDate.equals(testFutureDate));
		
		assertEquals(0, outputDate.compareTo(testFutureDate));
	}
	
	@Test
	public void testGetFutureMeetingWhichDoesntExist() {
		FutureMeeting output = emptyCM.getFutureMeeting(1);
		
		assertNull(output);
		
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testGetFutureMeetingWithIdForMeetingInThePast() {
		
		cManagerWithContacts.addNewPastMeeting(testSet, testPastDate, "Past meeting test");
		
		int pastID = ((ContactManagerImpl) cManagerWithContacts).getAllMeetings().get(0).getId();
		
		cManagerWithContacts.getFutureMeeting(pastID);
		
		
	}
	
	//addNewPastMeeting tests
	
	@Test
	public void testAddNewPastMeetingToEmptyMeetingsList() {
				
		cManagerWithContacts.addNewPastMeeting(testSet, testPastDate, "Test notes");
		
		assertNotNull(((ContactManagerImpl)cManagerWithContacts).getAllMeetings().get(0));
		
		Meeting output = ((ContactManagerImpl)cManagerWithContacts).getAllMeetings().get(0);
		
		assertEquals(testSet, output.getContacts());
		assertEquals(testPastDate, output.getDate());
		assertEquals("Test notes", ((PastMeeting) output).getNotes());
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddNewPastMeetingWhenOneOrMoreContactsDoesntExist() {
		
		Contact dave = new ContactImpl(300, "Davy Jones", "Smells of fish");
		
		Set<Contact> nonExistingContacts = new HashSet<Contact>();
		nonExistingContacts.add(dave);
		
		cManagerWithContacts.addNewPastMeeting(nonExistingContacts, testPastDate, "Non existing contacts");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddNewPastMeetingWithFutureDate() {
		cManagerWithContacts.addNewPastMeeting(testSet, testFutureDate, "Future date");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddNewPastMeetingWithEmptyContactsSet() {
		Set<Contact> emptySet = new HashSet<Contact>();
		
		cManagerWithContacts.addNewPastMeeting(emptySet, testPastDate, "Empty contacts");		
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNewPastMeetingWithNullContacts() {
		cManagerWithContacts.addNewPastMeeting(null, testPastDate, "null contacts");
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNewPastMeetingWithNullDate() {
		cManagerWithContacts.addNewPastMeeting(testSet, null, "null date");
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNewPastMeetingWithNullNotes() {
		cManagerWithContacts.addNewPastMeeting(testSet, testPastDate, null);
	}
	
	
	// getPastMeeting tests
	
	@Test
	public void testGetPastMeetingWhereOnlyOneExists() {
		cManagerWithContacts.addNewPastMeeting(testSet, testPastDate, "Past meeting");
		PastMeeting pm = ((ContactManagerImpl) cManagerWithContacts).getAllPastMeetings().get(0);
		int id = pm.getId();
		
		assertEquals(pm, cManagerWithContacts.getPastMeeting(id));
	
	}	
	
	@Test
	public void testGetPastMeetingWhereManyExist() {
		Calendar date1 = new GregorianCalendar(2014, 8, 13);
		Calendar date2 = new GregorianCalendar(2014, 3, 12);
		Calendar date3 = new GregorianCalendar(2013, 5, 9);
		
		Set<Contact> set1 = cManagerWithContacts.getContacts("");
		Set<Contact> set2 = cManagerWithContacts.getContacts("Bruce");
		Set<Contact> set3 = cManagerWithContacts.getContacts("o");
		
		cManagerWithContacts.addNewPastMeeting(set1, date1, "One");
		cManagerWithContacts.addNewPastMeeting(set2, date2, "Two");
		cManagerWithContacts.addNewPastMeeting(set3, date3, "Three");
		
		List<PastMeeting> meetings = ((ContactManagerImpl) cManagerWithContacts).getAllPastMeetings();
		
		PastMeeting pm1 = null;
		int id1 = 0;
	
		
		for (PastMeeting m : meetings) {
			if (m.getNotes().equals("One")) {
				pm1 = m;
				id1 = m.getId();
			} 
		}

		assertEquals(pm1, cManagerWithContacts.getPastMeeting(id1));
	}
	
	@Test
	public void testGetPastMeetingWhereNoneExist() {
		assertNull(cManagerWithContacts.getPastMeeting(12));
	}
	
	@Test (expected = IllegalStateException.class) 
	public void testGetPastMeetingWhereIdRefersToFutureMeeting() {
		int id = cManagerWithContacts.addFutureMeeting(testSet, testFutureDate);
		
		cManagerWithContacts.getPastMeeting(id);
	}
	
	
	// getMeeting tests
	
	@Test
	public void testGetMeetingWithExistingFutureMeeting() {
		int id = cManagerWithContacts.addFutureMeeting(testSet, testFutureDate);
		
		FutureMeeting fm = cManagerWithContacts.getFutureMeeting(id);
		
		Meeting meeting = cManagerWithContacts.getMeeting(id);
		
		assertEquals(fm, meeting);
		assertEquals((Meeting) fm, meeting);
		
	}
	
	@Test
	public void testGetMeetingWithExistingPastMeeting() {
		
		cManagerWithContacts.addNewPastMeeting(testSet, testPastDate, "Past meeting");
		PastMeeting pm = ((ContactManagerImpl) cManagerWithContacts).getAllPastMeetings().get(0);
		int id = pm.getId();
		
		
		assertEquals(pm, cManagerWithContacts.getMeeting(id));
		assertEquals((Meeting) pm, cManagerWithContacts.getMeeting(id));
	}
	
	@Test
	public void testGetMeetingWhereIdDoesntExist() {
		assertNull(cManagerWithContacts.getMeeting(33));
	}
	
	
	// getFutureMeetingList tests
		
	@Test
	public void testGetFutureMeetingListWhereOneExists() {
		cManagerWithContacts.addFutureMeeting(testSet, testFutureDate);
		
		Set<Contact> wadeSet = cManagerWithContacts.getContacts("Wade");
		Contact deadpool = null;
		for (Contact c : wadeSet) {
			if (c.getName().equals("Wade Wilson")) {
				deadpool = c;
			}
		}
		
		List<Meeting> fmList = cManagerWithContacts.getFutureMeetingList(deadpool);
		assertNotNull(fmList);
		assertEquals(1, fmList.size());
		assertEquals(testSet, fmList.get(0).getContacts());
	}
	
	@Test
	public void testGetFutureMeetingListWhereManyExist() {
		
		cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2017, 11, 5));
		cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2020, 1, 3));
		cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2019, 4, 13));
		
		Set<Contact> wadeSet = cManagerWithContacts.getContacts("Wade");
		Contact deadpool = null;
		for (Contact c : wadeSet) {
			if (c.getName().equals("Wade Wilson")) {
				deadpool = c;
			}
		}
		
		List<Meeting> fmList = cManagerWithContacts.getFutureMeetingList(deadpool);
		assertNotNull(fmList);
		assertEquals(3, fmList.size());
		assertEquals(testSet, fmList.get(0).getContacts());		
	}
		
	@Test
	public void testGetFutureMeetingListWhereNoneExist() {
		Set<Contact> wadeSet = cManagerWithContacts.getContacts("Wade");
		Contact deadpool = null;
		for (Contact c : wadeSet) {
			if (c.getName().equals("Wade Wilson")) {
				deadpool = c;
			}
		}
				
		assertTrue(cManagerWithContacts.getFutureMeetingList(deadpool).isEmpty());
	}
	
	@Test
	public void testChronologicalOrderOfGetFutureMeetingList() {
		cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2017, 11, 5));
		cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2020, 1, 3));
		cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2019, 4, 13));
		
		Set<Contact> wadeSet = cManagerWithContacts.getContacts("Wade");
		Contact deadpool = null;
		for (Contact c : wadeSet) {
			if (c.getName().equals("Wade Wilson")) {
				deadpool = c;
			}
		}
		List<Meeting> fmList = cManagerWithContacts.getFutureMeetingList(deadpool);
		
		//First in the list will be the soonest to now, so second in the list should
		//be after. If so, these ints will be negative
		
		int difference = fmList.get(0).getDate().compareTo(fmList.get(1).getDate());
		assertTrue(difference < 0);
		
		difference = fmList.get(1).getDate().compareTo(fmList.get(2).getDate());
		assertTrue(difference < 0);
		
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetFutureMeetingListWhereContactDoesntExist() {
		cManagerWithContacts.getFutureMeetingList(new ContactImpl(64, "Donald Blake", 
												"Speaks like he is in a Shakespeare play"));
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetFutureMeetingListWhereContactIsNull() {
		cManagerWithContacts.getFutureMeetingList(null);
	}
	
	// getMeetingListOn tests
	
	@Test
	public void testGetMeetingListOnForFutureWhereOneExists() {
		cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2029, 1, 1, 12, 0));
		
		List<Meeting> output = cManagerWithContacts.getMeetingListOn(new GregorianCalendar(2029, 1, 1));
		
		assertNotNull(output);
		assertEquals(1, output.size());
		assertEquals(testSet, output.get(0).getContacts());
		
	}
	
	@Test
	public void testGetMeetingListOnForPastWhereOneExists() {
		cManagerWithContacts.addNewPastMeeting(testSet, testPastDate, "Notes");
		
		List<Meeting> output = cManagerWithContacts.getMeetingListOn(testPastDate);
		
		assertNotNull(output);
		assertEquals(1, output.size());
		assertEquals(testSet, output.get(0).getContacts());
		
	}
	
	@Test
	public void testGetMeetingListOnForFutureWhereManyExist() {
		//Check Chronology
		cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2020, 3, 13, 13, 30));
		cManagerWithContacts.addFutureMeeting(testSet2, new GregorianCalendar(2020, 3, 13, 14, 30));
		cManagerWithContacts.addFutureMeeting(testSet3, new GregorianCalendar(2020, 3, 13, 10, 30));
		
		List<Meeting> output = cManagerWithContacts.getMeetingListOn(new GregorianCalendar(2020, 3, 13));
		
		assertNotNull(output);
		assertEquals(3, output.size());
		
		int difference = output.get(0).getDate().compareTo(output.get(1).getDate());
		assertTrue(difference <= 0);
		
		difference = output.get(1).getDate().compareTo(output.get(2).getDate());
		assertTrue(difference <= 0);
		
	}
	
	@Test
	public void testGetMeetingListOnForPastWhereManyExist() {
		//Check Chronology
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 13, 30), "notes1");
		cManagerWithContacts.addNewPastMeeting(testSet2, new GregorianCalendar(2012, 3, 13, 14, 30), "notes2");
		cManagerWithContacts.addNewPastMeeting(testSet3, new GregorianCalendar(2012, 3, 13, 10, 30), "notes3");
		
		List<Meeting> output = cManagerWithContacts.getMeetingListOn(new GregorianCalendar(2012, 3, 13));
		
		assertNotNull(output);
		assertEquals(3, output.size());
		
		int difference = output.get(0).getDate().compareTo(output.get(1).getDate());
		assertTrue(difference >= 0);
		
		difference = output.get(1).getDate().compareTo(output.get(2).getDate());
		assertTrue(difference >= 0);
	}	
	
	@Test
	public void testGetMeetingListOnWhereNoneExist() {
		List<Meeting> output = cManagerWithContacts.getMeetingListOn(new GregorianCalendar(2014, 3,12));
		
		assertTrue(output.isEmpty());
		
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetMeetingListOnWhereDateIsNull() {
		cManagerWithContacts.getMeetingListOn(null);
	}
	
	// getPastMeetingListFor tests
	
	@Test
	public void testGetPastMeetingListForWhereMeetingsExistWithThatContact() {
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 13, 30), "one");
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 14, 30), "two");
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 10, 30), "three");
		
		Contact testContact = null;
		for (Contact c : testSet) {
			if (c.getName() == "Wilson") {
				testContact = c;
			}
		}
		
		List<PastMeeting> output = cManagerWithContacts.getPastMeetingListFor(testContact);
		
		assertNotNull(output);
		assertEquals(3, output.size());
		assertEquals(testSet, output.get(0).getContacts());
		assertEquals(testSet, output.get(1).getContacts());
		assertEquals(testSet, output.get(2).getContacts());
		
		
	}
	
	@Test
	public void testGetPastMeetingListForWhereNoMeetingsExistWithThatContact() {
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 13, 30), "one");
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 14, 30), "two");
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 10, 30), "three");
	
		Contact testContact = null;
			for (Contact c : cManagerWithContacts.getContacts("")) {
				if (c.getName() == "Bruce Wayne") {
					testContact = c;
				}
			}
			
		List<PastMeeting> output = cManagerWithContacts.getPastMeetingListFor(testContact);
		
		assertTrue(output.isEmpty());
	}
	
	@Test
	public void testGetPastMeetingListForChronologicalOrdering() {
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 13, 30), "one");
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 14, 30), "two");
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 10, 30), "three");
		
		Contact testContact = null;
		for (Contact c : cManagerWithContacts.getContacts("")) {
			if (c.getName() == "Wilson") {
				testContact = c;
			}
		}
		
		List<PastMeeting> output = cManagerWithContacts.getPastMeetingListFor(testContact);
		
		assertEquals(3, output.size());
		assertTrue(output.get(0).getNotes() == "two");
		assertTrue(output.get(1).getNotes() == "one");
		assertTrue(output.get(2).getNotes() == "three");
		
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testGetPastMeetingListForWhereContactDoesntExist() {
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 13, 30), "one");
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 14, 30), "two");
		cManagerWithContacts.addNewPastMeeting(testSet, new GregorianCalendar(2012, 3, 13, 10, 30), "three");
		
		Contact dave = new ContactImpl(100, "Dave", "Hello Dave");
		
		List<PastMeeting> output = cManagerWithContacts.getPastMeetingListFor(dave);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetPastMeetingListForWhereContactIsNull() {
		List<PastMeeting> output = cManagerWithContacts.getPastMeetingListFor(null);
	}
	
	// addMeetingNotes tests
	
	@Test
	public void testAddMeetingNotesChangeFutureMeetingToPastMeeting() {
		//add some future meetings
		int id1 = cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2017, 3, 13, 13, 30));
		int id2 = cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2019, 4, 14, 10, 30));
		int id3 = cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2018, 1, 12, 9, 30));
		
		//change the 'now' time so these meetings are now in the pastID
		((ContactManagerImpl) cManagerWithContacts).changeCurrentTimeToFuture();
		
		assertTrue(cManagerWithContacts.getMeeting(id1) instanceof FutureMeeting);
		assertTrue(cManagerWithContacts.getMeeting(id2) instanceof FutureMeeting);
		assertTrue(cManagerWithContacts.getMeeting(id3) instanceof FutureMeeting);
		
		//add the notes and check that they are now past meetings etc.
		cManagerWithContacts.addMeetingNotes(id1, "Test Notes 1");
		cManagerWithContacts.addMeetingNotes(id2, "Test Notes 2");
		cManagerWithContacts.addMeetingNotes(id3, "Test Notes 3");
		
		assertTrue(cManagerWithContacts.getMeeting(id1) instanceof PastMeeting);
		assertTrue(cManagerWithContacts.getMeeting(id2) instanceof PastMeeting);
		assertTrue(cManagerWithContacts.getMeeting(id3) instanceof PastMeeting);
		
		assertNotNull(cManagerWithContacts.getPastMeeting(id1));
		assertNotNull(cManagerWithContacts.getPastMeeting(id2));
		assertNotNull(cManagerWithContacts.getPastMeeting(id3));
		
		assertEquals("Test Notes 1", cManagerWithContacts.getPastMeeting(id1).getNotes());
		assertEquals("Test Notes 2", cManagerWithContacts.getPastMeeting(id2).getNotes());
		assertEquals("Test Notes 3", cManagerWithContacts.getPastMeeting(id3).getNotes());
	}
	
	@Test
	public void testAddMeetingNotesToExistingPastMeeting() {
		//add a past meeting with empty notes
		cManagerWithContacts.addNewPastMeeting(testSet2, testPastDate, "");
		
		assertTrue(cManagerWithContacts.getPastMeeting(1).getNotes() == "");
		assertTrue(cManagerWithContacts.getPastMeeting(1).getDate() == testPastDate);
		
		//add some notes to it.
		PastMeeting pm = cManagerWithContacts.addMeetingNotes(1, "Test Notes");
		
		assertTrue(cManagerWithContacts.getPastMeeting(1).getNotes() == "Test Notes");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddMeetingNotesWhereMeetingDoesntExist() {
		PastMeeting pm = cManagerWithContacts.addMeetingNotes(100, "fail");
	}
	
	@Test (expected = IllegalStateException.class)
	public void testAddMeetingNotesWhereMeetingIsAFutureDate() {
		int id = cManagerWithContacts.addFutureMeeting(testSet, testFutureDate);
		PastMeeting pm = cManagerWithContacts.addMeetingNotes(id, "Fail");
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddMeetingNotesWhereNotesAreNull() {
		int id1 = cManagerWithContacts.addFutureMeeting(testSet, new GregorianCalendar(2017, 3, 13, 13, 30));
		
		((ContactManagerImpl) cManagerWithContacts).changeCurrentTimeToFuture();
		
		PastMeeting pm = cManagerWithContacts.addMeetingNotes(id1, null);
	}
	
	// flush tests
	@Test
	public void testFlushCreatesNewContactsTxtFileWhenThereIsNone() {
		
		cManagerWithContacts.flush();
		assertTrue(new File("." + File.separator + "contacts.txt").exists());
	}
	
	// @Test
	// public void testFlushSendsContactsAndMeetingsToContactsTxt() {
		
	// }
	
	// @Test
	// public void testFlush() {
		
	// }
	
	
}