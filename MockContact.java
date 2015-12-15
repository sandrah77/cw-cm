public class MockContact {
	private int ID;
	private String name;
	private String notes;
	
	public MockContact(int ID, String name, String notes) {
		this.ID = ID;
		this.name = name;
		this.notes = notes;
	}
	
	public int getId() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNotes() {
		return notes;
	}
	
}