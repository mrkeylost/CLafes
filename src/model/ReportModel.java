package model;

import java.sql.ResultSet;
import java.time.LocalDateTime;

import database.Connect;

public class ReportModel {

	Connect db = Connect.getInstance();
	
	public Boolean addNewReport(String userRole, String pcId, String reportNote) {
		
		String query = String.format("INSERT INTO `report`(`PcId`, `ReportNote`, `ReportDate`, `UserRole`) VALUES ('%s','%s','%s','%s')", Integer.parseInt(pcId), reportNote, LocalDateTime.now(), userRole);
		
		db.execute(query);
		
		return true;
	}
	
	public ResultSet getAllReportData() {
		
		String query = "SELECT * FROM report";
		
		return db.selectData(query);
		
	}
	
}
