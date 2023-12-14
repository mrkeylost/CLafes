package view;

import java.util.Optional;

import controller.JobController;
import javafx.scene.control.TextInputDialog;

public class UpdateStaffJobForm extends TextInputDialog{
	JobController jobController = new JobController(); 
	public UpdateStaffJobForm(String userId, String pcId) {
		setTitle("Update Staff Job");
		setHeaderText("Enter job status");
		setContentText("Job Status: ");
		
		Optional<String> result = showAndWait();
		
		result.ifPresent(newStatus -> {
			jobController.checkUpdateStaffJob(userId, newStatus, pcId);
		});
	}

}
