package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.PcModel;
import model.ReportModel;

public class ReportController {

	ReportModel reportModel = new ReportModel();
	PcModel pcModel = new PcModel();
	
	public void alert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
	public Boolean addNewReport(String userRole, String pcId, String reportNote) {
		
		if(!checkPc(pcId)) {
			alert("PC Number Invalid!");
			
			return false;
		}
		
		if(reportNote.isEmpty()) {
			alert("Report not inputted yet");
			
			return false;
		}
		
		return reportModel.addNewReport(userRole, pcId, reportNote);
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
