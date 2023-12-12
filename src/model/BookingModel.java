package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import database.Connect;

public class BookingModel {

	Connect db = Connect.getInstance();
	
	public Boolean bookPc(String pcId, String userId, String date) {
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
		String query = String.format("INSERT INTO `pcbook`(`PcId`, `UserId`, `BookedDate`) VALUES ('%s','%s','%s')", Integer.parseInt(pcId), Integer.parseInt(userId), LocalDate.parse(date, dateFormat));
		
		db.execute(query);
		
		return true;
		
	}
	
	public ResultSet getBookingDate(String id, String date) {
		
		String query = "SELECT * FROM `pcbook` WHERE BookedDate = '" + date + "' AND PcId='" + Integer.parseInt(id) + "'";
		
		return db.selectData(query);
	}
	
	public ResultSet getAllPcBookedData() {
		
		String query = 	"SELECT pb.BookId, pb.PcId, u.UserName, pb.BookedDate FROM pcbook pb\r\n" + 
						"JOIN users u \r\n" + 
						"ON pb.UserId = u.UserId";
		
		
		return db.selectData(query);
	}
	
	public Boolean deleteBookData(String bookId) {
		
		String query = "DELETE FROM `pcbook` WHERE BookId = '" + Integer.parseInt(bookId) + "'";
		
		db.execute(query);
		
		return true;
	}
	
}
