package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Job;
import model.JobModel;

public class JobController {

	JobModel jobModel = new JobModel();
	
	public void alert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
	public List<Job> viewTechnicianJob(String id) {
		
		List<Job> jobData = new Vector<Job>();
		
		ResultSet rs = jobModel.viewTechnicianJob(id);
		
		try {
			while(rs.next()) {
				Integer jobId = rs.getInt("JobId");
				Integer pcId = rs.getInt("PcId");
				String jobStatus = rs.getString("JobStatus");
				
				jobData.add(new Job(jobId, pcId, jobStatus));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jobData;
	}
	
	public Boolean completeJob(String userId, String jobId, String role) {
		
		if(!checkJobId(userId, jobId)) {
			alert("Job ID not found");
			
			return false;
		}
		
		if(!checkJobStatus(userId, jobId)) {
			alert("Job already completed");
			
			return false;
		}
		
		if(!role.equals("Computer Technician")) {
			alert("Unauthorized user");
			
			return false;
		}
		
		return jobModel.completeJob(jobId);
	}
	
	public Boolean checkJobId(String userId, String jobId) {
		
		ResultSet rs =  jobModel.viewTechnicianJob(userId);
		
		try {
			while(rs.next()) {
				Integer jobIdCheck = rs.getInt("JobId");
				
				if(jobId.equals(jobIdCheck.toString())) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean checkJobStatus(String userId, String jobId) {
		
		ResultSet rs =  jobModel.viewTechnicianJob(userId);
		
		try {
			while(rs.next()) {
				Integer jobIdCheck = rs.getInt("JobId");
				String jobStatus = rs.getString("JobStatus"); 
				
				if(jobId.equals(jobIdCheck.toString()) && jobStatus.equals("Uncomplete")) {
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
