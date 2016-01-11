/**
 * A mock class for the Contact interface
 *
 * @author ocouls01
 */

public class MockContact implements Contact {
	private int ID;
	private String name;
	private String notes;
	
	public MockContact(int ID, String name, String notes) {
		this.ID = ID;
		this.name = name;
		this.notes = notes;
	}
	public MockContact() {}
	
	public int getId() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void addNotes(String note) {}
	
}