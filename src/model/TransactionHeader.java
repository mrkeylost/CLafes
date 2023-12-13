package model;

import java.util.Date;

public class TransactionHeader {
	private Integer TransactionId;
	private Integer StaffId;
	private String StaffName;
	private Date TransactionDate;

	public TransactionHeader(Integer transactionId, Integer staffId, String staffName, Date transactionDate) {
		super();
		TransactionId = transactionId;
		StaffId = staffId;
		StaffName = staffName;
		TransactionDate = transactionDate;
	}

	public Integer getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(Integer transactionId) {
		TransactionId = transactionId;
	}

	public Integer getStaffId() {
		return StaffId;
	}

	public void setStaffId(Integer staffId) {
		StaffId = staffId;
	}

	public String getStaffName() {
		return StaffName;
	}

	public void setStaffName(String staffName) {
		StaffName = staffName;
	}

	public Date getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		TransactionDate = transactionDate;
	}

}
