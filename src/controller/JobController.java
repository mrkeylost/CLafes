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
	PcController pcController = new PcController();
	UserController userController = new UserController();
	
	public void alert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
	public List<Job> getAllJobData(){
		
		Vector<Job> jobDataList = new Vector<>();
		
		ResultSet rs = jobModel.getAllJobData();
		
		try {
			while(rs.next()) {
				Integer jobId = rs.getInt("JobId");
				Integer userId = rs.getInt("UserId");
				Integer pcId = rs.getInt("PcId");
				String jobStatus = rs.getString("JobStatus");
				
				jobDataList.add(new Job(jobId, userId, pcId, jobStatus));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jobDataList;
	}
	
	public void checkUpdateStaffJob(String userId, String jobStatus, String pcId) {
		if(userId.isEmpty()) {
			alert("Select the job first!");
			
			return;
		}
		
		if(!userController.checkRoleTechnician(userId)) {
			alert("user Role must be “Technician”");
			
			return;
		}
		
		if(!jobStatus.equals("Complete") && !jobStatus.equals("UnComplete")) {
			alert("Job status must be either “Complete” or “UnComplete”");
			
			return;
		}
		
		
		if(pcController.isUniquePCId(pcId)) {
			alert("PC id not found");
		}
		
		jobModel.updateStaffJobStatus(userId, jobStatus, pcId);
		
		if(jobStatus.equals("Complete")) {
			pcController.updatePC("Usable", pcId);
		}
		
		if(jobStatus.equals("UnComplete")) {
			pcController.updatePC("Maintenance", pcId);
		}
		
	}
	
	public void addStaffJob(String userId, String pcId) {
		
		if(userId.isEmpty() || pcId.isEmpty()) {
			alert("Fill the field first!");
			
			return;
		}
		
		if(!userController.checkRoleTechnician(userId)) {
			alert("user Role must be “Technician”");
			
			return;
		}
		
		if(pcController.isUniquePCId(pcId)) {
			alert("PC id not found");
			
			return;
		}
		
		try {
			if(jobModel.getUnCompleteData(pcId).next()) {
				alert("PC is being handled");
				
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jobModel.addJob(userId, pcId);
		
	}
}
