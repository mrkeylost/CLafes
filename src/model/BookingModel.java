package model;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.Connect;

public class BookingModel {

	Connect db = Connect.getInstance();
	
	public Boolean bookPc(String pcId, String userId, String date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date parseDate = dateFormat.parse(date);
			
			SimpleDateFormat outDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String bookDate = outDateFormat.format(parseDate);
			
			String query = String.format("INSERT INTO `pcbook`(`PcId`, `UserId`, `BookedDate`) VALUES ('%s','%s','%s')", Integer.parseInt(pcId), Integer.parseInt(userId), bookDate);
			
			db.execute(query);
			
			return true;
		} catch (ParseException e) {
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
