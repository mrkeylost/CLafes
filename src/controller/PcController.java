package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Pc;
import model.PcModel;

public class PcController {

	PcModel pcModel = new PcModel();
	
	public void alert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
	public List<Pc> viewAllPc() {
		List<Pc> pcData = new Vector<Pc>();
		
		ResultSet rs = pcModel.viewAllPc();
		
		try {
			while(rs.next()) {
				Integer pcId = rs.getInt("PcId");
				String pcStatus = rs.getString("PcStatus");
				
				pcData.add(new Pc(pcId, pcStatus));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pcData;
	}
	
	public void addNewPc(String id) {
		
		if(id.isEmpty()) {
			alert("Id cannot be empty");
			
			return;
		}
		if(!isUniquePCId(id)) {
			alert("Pc Id must be unique");
			
			return;
		}
		
		pcModel.addNewPC(id);

	}
	
	public Boolean isUniquePCId(String id) {

		ResultSet rs = pcModel.viewAllPc();

		try {
			while(rs.next()) {
				String pcId = rs.getString("PcId");

				if(pcId.equals(id)) {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	public Boolean updatePC(String status, String id) {
		if(id.isEmpty()) {
			alert("Select Pc first");
			
			return false;
		}
		
		if(isUniquePCId(id)) {
			alert("PC id not found");
			
			return false;
		}
		
		if(!status.equals("Usable") && !status.equals("Maintenance") && !status.equals("Broken")) {
			alert(" PC status must be either “Usable”, “Maintenance” or “Broken”");
			
			return false;
		}
		pcModel.updatePC(status, id);
		return true;
	}
	
	public Boolean deletePC(String id) {
		if(id.isEmpty()) {
			alert("Select Pc first");
			
			return false;
		}
		
		if(isUniquePCId(id)) {
			alert("PC id not found");
			
			return false;
		}
		
		try {
			if(pcModel.checkBookedPC(id).next()) {
				alert("PC has been booked for the future");
				
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pcModel.deletePc(id);
		return true;
	}
}