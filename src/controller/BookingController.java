package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

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
			alert("PC Number not exist!");
			
			return false;
		}
		
		return bookingModel.bookPc(pcId, userId, date);
	}
	
	public Boolean checkPc(String pcId) {
		
		ResultSet rs =  pcModel.viewAllPc();
		
		try {
			while(rs.next()) {
				Integer pcIdCheck = rs.getInt("PcId");
				String pcAvailability = rs.getString("PcAvailability");
				
				if(pcId.equals(pcIdCheck.toString()) && pcAvailability.equals("ON")) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
