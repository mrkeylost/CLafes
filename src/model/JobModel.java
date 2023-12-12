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
	
}
