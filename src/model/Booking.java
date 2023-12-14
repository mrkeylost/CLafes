package model;

public class Booking {
	private int bookId, pcId;
	private String userName, bookedDate;
	
	public Booking(int bookId, int pcId, String userName, String bookedDate) {
		super();
		this.bookId = bookId;
		this.pcId = pcId;
		this.userName = userName;
		this.bookedDate = bookedDate;
	}
	
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public int getPcId() {
		return pcId;
	}
	
	public void setPcId(int pcId) {
		this.pcId = pcId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getBookedDate() {
		return bookedDate;
	}
	
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}
	
}
