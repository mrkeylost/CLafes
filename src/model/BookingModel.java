package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import database.Connect;

public class BookingModel {

	Connect db = Connect.getInstance();
	
	public Boolean bookPc(String pcId, String userId, String date) {
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {			
			String query = String.format("INSERT INTO `pcbook`(`PcId`, `UserId`, `BookedDate`) VALUES ('%s','%s','%s')", Integer.parseInt(pcId), Integer.parseInt(userId), LocalDate.parse(date, dateFormat));
			
			db.execute(query);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public ResultSet getBookingDate(String id, String date) {
		
		String query = "SELECT * FROM `pcbook` WHERE BookedDate = '" + date + "' AND PcId='" + Integer.parseInt(id) + "'";
		
		return db.selectData(query);
	}
	
}
