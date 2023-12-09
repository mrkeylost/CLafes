package model;

public class Transaction {

	private Integer transactionId;
	private Integer pcId;
	private String bookedTime;
	
	public Transaction(Integer transactionId, Integer pcId, String bookedTime) {
		super();
		this.transactionId = transactionId;
		this.pcId = pcId;
		this.bookedTime = bookedTime;
	}
	
	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getPcId() {
		return pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public String getBookedTime() {
		return bookedTime;
	}

	public void setBookedTime(String bookedTime) {
		this.bookedTime = bookedTime;
	}
}
