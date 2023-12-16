package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import database.Connect;

public class TransactionModel {

	Connect db = Connect.getInstance();
	
	// method execute query untuk mengambil data transaction detail berdasarkan Customer ID
	public ResultSet getUserTransactionDetail(String id) {
		
		String query = 	"SELECT td.TransactionId, td.PcId, td.CustomerName, td.BookedTime \r\n" + 
						"FROM trdetail td\r\n" + 
						"JOIN users u \r\n" + 
						"ON u.UserName = td.CustomerName\r\n" + 
						"WHERE u.UserId =  '" + Integer.parseInt(id) + "'";
		
		return db.selectData(query);
	}
	
	// method execute query untuk menambahkan Transaction Header baru ke database
	public Boolean addNewTransactionHeader(Integer staffId, String staffName, String transactionDate) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String query = String.format("INSERT INTO `trheader`(`StaffId`, `StaffName`, `TransactionDate`) VALUES ('%d','%s','%s')", staffId, staffName, LocalDate.parse(transactionDate, dateFormat));
		
		db.execute(query);
		
		return true;
	}
	
	// method execute query untuk mengambil data transaction Header yang paling baru ditambahkan ke database
	public ResultSet getLatestHeaderId() {
		String query = "SELECT * FROM `trheader` ORDER BY TransactionId DESC LIMIT 1";
		
		return db.selectData(query);
	}
	
	// method execute query untuk menambahkan Transaction Detail baru ke database
	public Boolean addTransactionDetail(Integer transactionHeaderId, int pcId, String customerName, String bookedDate) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String query = String.format("INSERT INTO `trdetail`(`TransactionId`, `PcId`, `CustomerName`, `BookedTime`) VALUES ('%d','%d','%s','%s')", transactionHeaderId, pcId, customerName, LocalDate.parse(bookedDate, dateFormat));
		
		db.execute(query);
		return true;
	}
	
	// method execute query untuk mengambil data Transaction detail berdasarkan Transaction IDnya
	public ResultSet getTransactionDetail(String transactionId) {
		String query = String.format("SELECT * FROM `trdetail` WHERE TransactionId = %d", Integer.parseInt(transactionId));
		
		return db.selectData(query);
	}
}