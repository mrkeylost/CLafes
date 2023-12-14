package model;

import java.sql.ResultSet;

import database.Connect;

public class JobModel {

	Connect db = Connect.getInstance();
	
	public ResultSet viewTechnicianJob(String id) {
		
		String query = "SELECT * FROM `job` WHERE UserId = '" + Integer.parseInt(id) + "'";
		
		return db.selectData(query);
	}
	
	public Boolean completeJob(String jobId) {
		
		String query = "UPDATE job SET JobStatus = 'Complete' WHERE JobId = '"+ Integer.parseInt(jobId) + "'";
		
		db.execute(query);
		
		return true;
	}
	
	public ResultSet getAllJobData() {
		
		String query = "SELECT * FROM job";
		
		return db.selectData(query);
	}
	
	public void updateStaffJobStatus(String userId, String jobStatus, String PcId) {
		String query = String.format("UPDATE job SET JobStatus = '%s' WHERE UserId = %d AND PcId = %d", jobStatus, Integer.parseInt(userId), Integer.parseInt(PcId));
		
		db.execute(query);
	}
	
	public ResultSet getUnCompleteData(String pcId) {
		String query = String.format("SELECT * FROM job WHERE PcId = %d AND JobStatus = 'UnComplete'", Integer.parseInt(pcId));
		
		return db.selectData(query);
		
	}
	
	public void addJob(String userId, String PcId) {
		String query1 = String.format("INSERT INTO `job`(`UserId`, `PcId`, `JobStatus`) VALUES (%d,%d,'UnComplete')",Integer.parseInt(userId), Integer.parseInt(PcId));
		String query2 = String.format("UPDATE pc SET PcStatus = 'Maintenance' WHERE PcId = %d", Integer.parseInt(PcId));
		
		db.execute(query1);
		db.execute(query2);
	}
	
}