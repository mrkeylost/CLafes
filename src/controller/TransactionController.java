package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import model.Transaction;
import model.TransactionModel;

public class TransactionController {

	TransactionModel transactionModel = new TransactionModel();
	
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
	
}
