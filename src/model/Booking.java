package model;

public class Booking {

	private Integer bookId;
	private Integer pcId;
	private String userName;
	private String bookedTime;
	
	public Booking(Integer bookId, Integer pcId, String userName, String bookedTime) {
		super();
		this.bookId = bookId;
		this.pcId = pcId;
		this.userName = userName;
		this.bookedTime = bookedTime;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getPcId() {
		return pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookedTime() {
		return bookedTime;
	}

	public void setBookedTime(String bookedTime) {
		this.bookedTime = bookedTime;
	}
}
