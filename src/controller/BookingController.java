package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.BookingModel;
import model.PcModel;

public class BookingController {

	BookingModel bookingModel = new BookingModel();
	PcModel pcModel = new PcModel();
	
	public void alert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
	public Boolean bookPc(String pcId, String userId, String date) {
		
		if(!checkPc(pcId)) {
			alert("PC is not operationable!");
			
			return false;
		}
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if(LocalDate.parse(date, dateFormat).isBefore(LocalDate.now())) {
			alert("Date cannot be less than today");
			
			return false;
		}
		
		if(!checkBookingdate(pcId, date)) {
			alert("PC already booked");
			
			return false;
		}
		
		return bookingModel.bookPc(pcId, userId, date);
	}
	
	public Boolean checkPc(String pcId) {
		
		ResultSet rs =  pcModel.viewAllPc();
		
		try {
			while(rs.next()) {
				Integer pcIdCheck = rs.getInt("PcId");
				String pcStatus = rs.getString("PcStatus"); 
				
				if(pcId.equals(pcIdCheck.toString()) && pcStatus.equals("Usable")) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean checkBookingdate(String id, String date) {
		
		ResultSet rs = bookingModel.getBookingDate(id, date);
		
		try {
			while(rs.next()) {
				String bookedDate = rs.getDate("BookedDate").toString();
				Integer pcId = rs.getInt("PcId");
				
				if(bookedDate.equals(date) && id.equals(pcId.toString())) {
					return false;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
}
