package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import database.Connect;

public class BookingModel {

	Connect db = Connect.getInstance();
	
	// method execute query untuk memasukkan data booking baru ke database  
	public Boolean bookPc(String pcId, String userId, String date) {
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
		String query = String.format("INSERT INTO `pcbook`(`PcId`, `UserId`, `BookedDate`) VALUES ('%s','%s','%s')", Integer.parseInt(pcId), Integer.parseInt(userId), LocalDate.parse(date, dateFormat));
		
		db.execute(query);
		
		return true;
		
	}
	
	// method execute query untuk mengambil data booking berdasarkan ID dan tanggalnya
	public ResultSet getBookingDate(String id, String date) {
		
		String query = "SELECT * FROM `pcbook` WHERE BookedDate = '" + date + "' AND PcId='" + Integer.parseInt(id) + "'";
		
		return db.selectData(query);
	}
	
	// method execute query untuk mengambil semua data booking yang terdaftar di database
	public ResultSet getAllPcBookedData() {
		
		String query = 	"SELECT pb.BookId, pb.PcId, u.UserName, pb.BookedDate FROM pcbook pb\r\n" + 
						"JOIN users u \r\n" + 
						"ON pb.UserId = u.UserId";
		
		return db.selectData(query);
	}
	
	// // method execute query untuk menghapus data booking berdasarkan BookId nya
	public Boolean deleteBookData(String bookId) {
		
		String query = "DELETE FROM `pcbook` WHERE BookId = '" + Integer.parseInt(bookId) + "'";
		
		db.execute(query);
		
		return true;
	}
	
	// method execute query untuk menghapus data booking berdasarkan tanggal bookingnya
	public Boolean deleteBookDataByDate(String date) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String query = "DELETE FROM `pcbook` WHERE BookedDate = '" + LocalDate.parse(date, dateFormat) + "'";
		
		db.execute(query);
		
		return true;
	}
	
	// method execute query untuk data booking berdasarkan tanggal bookingnya
	public ResultSet getBookDateToFinish(String date) {
		
		String query = "SELECT * FROM `pcbook` WHERE BookedDate = '" + date + "'";
		
		return db.selectData(query);
	}
	
	// method execute query untuk mengupdate PC ID di table booking sesuai id bookingnya
	public Boolean assignUserToAnotherPc(String newPcId, String bookId) {
		String query = "UPDATE pcbook SET PcId = '"+ newPcId +"' WHERE BookId = '"+ bookId + "'";
		
		db.execute(query);
		return true;
	}
	
	// method execute query untuk mengambil data booking berdasarkan idnya
	public ResultSet getBookDataById(String bookId) {
		String query = "SELECT * FROM `pcbook` WHERE BookId = '" + bookId + "'";
		
		return db.selectData(query);
	}
	
}
