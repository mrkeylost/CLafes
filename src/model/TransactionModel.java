package model;

import java.sql.ResultSet;

import database.Connect;

public class TransactionModel {

	Connect db = Connect.getInstance();
	
	public ResultSet getUserTransactionDetail(String id) {
		
		String query = 	"SELECT td.TransactionId, td.PcId, td.CustomerName, td.BookedTime \r\n" + 
						"FROM trdetail td\r\n" + 
						"JOIN users u \r\n" + 
						"ON u.UserName = td.CustomerName\r\n" + 
						"WHERE u.UserId =  '" + Integer.parseInt(id) + "'";
		
		return db.selectData(query);
	}
	
}
