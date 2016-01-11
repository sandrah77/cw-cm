import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Created by Oliver Coulson on 07/01/2016.
 */
public class FutureMeetingTest {
    private Calendar testFutureDate;
    private Set<Contact> testSet;

    @Before
    public void setUp() {
        testSet = new HashSet<Contact>();
        testSet.add(new ContactImpl(1, "Albert Einstein"));
        testSet.add(new ContactImpl(2, "Alan Turing"));
        testSet.add(new ContactImpl(3, "Richard Feynman"));

        testFutureDate = new GregorianCalendar(2020, 5, 13, 13, 0);


    }

    @After
    public void breakDown() {
        testFutureDate = null;
        testSet = null;


    }

    @Test
    public void testConstruction() {
        FutureMeeting output = new FutureMeetingImpl(1, testFutureDate, testSet);
        assertNotNull(output);

        assertEquals(1, output.getId());
        assertEquals(testFutureDate, output.getDate());
        assertEquals(testSet, output.getContacts());

    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructionWithEmptySetOfContacts() {
        Set<Contact> emptySet = new HashSet<Contact>();

        FutureMeeting output = new FutureMeetingImpl(1, testFutureDate, emptySet);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructionWithIdOfZero() {

        FutureMeeting output = new FutureMeetingImpl(0, testFutureDate, testSet);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructionWithNegativeId() {

        FutureMeeting output = new FutureMeetingImpl(-2, testFutureDate, testSet);
    }


    @Test (expected = NullPointerException.class)
    public void testContstructionWithNullDate() {
        FutureMeeting output = new FutureMeetingImpl(1, null, testSet);
    }

    @Test (expected = NullPointerException.class)
    public void testContstructionWithNullContacts() {
        FutureMeeting output = new FutureMeetingImpl(1, testFutureDate, null);
    }
}