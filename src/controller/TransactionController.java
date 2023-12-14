package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import model.Transaction;
import model.TransactionHeader;
import model.TransactionHeaderModel;
import model.TransactionModel;

public class TransactionController {

	TransactionModel transactionModel = new TransactionModel();
	TransactionHeaderModel transactionHeaderModel = new TransactionHeaderModel();
	
	public List<Transaction> getUserTransactionDetail(String id) {
		
		List<Transaction> userTransactionData = new Vector<Transaction>();
		
		ResultSet rs = transactionModel.getUserTransactionDetail(id);
		
		try {
			while(rs.next()) {
				Integer transactionId = rs.getInt("TransactionId");
				Integer pcId = rs.getInt("PcId");
				String bookedTime = rs.getDate("BookedTime").toString();
				
				userTransactionData.add(new Transaction(transactionId, pcId, bookedTime));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return userTransactionData;
	}
	
	public List<Transaction> getTransactionDetail(String id) {
		
		List<Transaction> userTransactionData = new Vector<Transaction>();
		
		ResultSet rs = transactionModel.getTransactionDetail(id);
		
		try {
			while(rs.next()) {
				Integer transactionId = rs.getInt("TransactionId");
				Integer pcId = rs.getInt("PcId");
				String bookedTime = rs.getDate("BookedTime").toString();
				
				userTransactionData.add(new Transaction(transactionId, pcId, bookedTime));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return userTransactionData;
	}
	
	public List<TransactionHeader> getTransactionHeader(){
		List<TransactionHeader> transactionHeaderList = new Vector<TransactionHeader>();
		
		ResultSet rs = transactionHeaderModel.getTransactionHeader();
		
		try {
			while(rs.next()) {
				Integer transactionId = rs.getInt("TransactionId");
				Integer staffId = rs.getInt("StaffId");
				String staffName = rs.getString("StaffName");
				Date transactionDate = rs.getDate("TransactionDate");
				
				transactionHeaderList.add(new TransactionHeader(transactionId, staffId, staffName, transactionDate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionHeaderList;
	}
	
}
