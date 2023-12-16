package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.PcModel;
import model.Report;
import model.ReportModel;

public class ReportController {

	ReportModel reportModel = new ReportModel();
	PcModel pcModel = new PcModel();
	
	// alert function apabila terjadi error
	public void alert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
	// method untuk menambahkan report baru dari Customer atau Operator, disini juga akan divalidasi report bagaimana yang valid
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
	
	// method untuk mengecek apakah PC terdaftar di database dan Statusnya Usable
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
	
	// method untuk mengambil semua data report dari database
	public List<Report> getAllReportData() {
		
		Vector<Report> reportList = new Vector<>();
		
		ResultSet rs = reportModel.getAllReportData();
		
		try {
			while(rs.next()) {
				Integer reportId = rs.getInt("ReportId");
				Integer pcId = rs.getInt("PcId");
				String reportNote = rs.getString("ReportNote");
				Date reportDate = rs.getDate("ReportDate");
				String userRole = rs.getString("UserRole");
				
				reportList.add(new Report(reportId, pcId, reportNote, reportDate, userRole));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportList;
	}
}
