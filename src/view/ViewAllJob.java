package view;

import java.util.List;

import controller.JobController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Job;

public class ViewAllJob {
	JobController jobController = new JobController();
	BorderPane bp;
	Button choose, back, updateJob, addJob;
	VBox container;
	HBox buttonContainer;
	TextField selected;
	TableView<Job> jobView;
	TableColumn<Job, Integer> jobId;
	TableColumn<Job, Integer> userId;
	TableColumn<Job, Integer> pcId;
	TableColumn<Job, String> jobStatus;
	List<Job> jobData;
	String selectedPCId = "", selectedUserid = "", selectedJobId = "";

	public ViewAllJob(Stage stage, String role, int id) {
		bp = new BorderPane();
		container = new VBox();
		container.setAlignment(Pos.TOP_LEFT);

		back = new Button("Back to Home");
		updateJob = new Button("Update Job");
		selected = new TextField();
		selected.setEditable(false);
		addJob = new Button("Add Job");
		buttonContainer = new HBox();
		
		buttonContainer.getChildren().addAll(back, addJob);
		buttonContainer.setSpacing(2);
		
		back.setOnMouseClicked(event -> {

			HomePage homePage = new HomePage(stage, role, id);

			stage.setScene(new Scene(homePage.getBp(), 600, 600));
		});

		jobView = new TableView<>();
		
		jobId = new TableColumn<>("Job Id");
		jobId.setCellValueFactory(new PropertyValueFactory<>("jobId"));
		
		userId = new TableColumn<>("User Id");
		userId.setCellValueFactory(new PropertyValueFactory<>("userId"));

		pcId = new TableColumn<>("PC Id");
		pcId.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		
		jobStatus = new TableColumn<>("Job Status");
		jobStatus.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
		
		jobView.getColumns().add(jobId);
		jobView.getColumns().add(userId);
		jobView.getColumns().add(pcId);
		jobView.getColumns().add(jobStatus);
		jobData = jobController.getAllJobData();

		ObservableList<Job> staffDataList = FXCollections.observableArrayList(jobData);
		jobView.setItems(staffDataList);
		container.getChildren().addAll(buttonContainer, jobView, selected, updateJob);
		bp.setCenter(container);
		
		jobView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null) {
				Integer selectedIndex = jobView.getSelectionModel().getSelectedIndex();
				if(selectedIndex != null) {
					selectedJobId= jobData.get(selectedIndex).getJobId().toString();
					selectedPCId= jobData.get(selectedIndex).getPcId().toString();
					selectedUserid= jobData.get(selectedIndex).getUserId().toString();
					selected.setText(selectedJobId);
				}
			}
		});
		
		updateJob.setOnAction(e -> {
			new UpdateStaffJobForm(selectedUserid, selectedPCId);
			selectedUserid = "";
			selectedPCId = "";
			selectedJobId = "";
			selected.setText("");
			refreshView();
		});
		
		addJob.setOnAction(e -> {
			new AddJobForm();
			refreshView();
		});
	}

	public BorderPane getBp() {
		return bp;
	}

	private void refreshView() {
		// Refresh the data and update the TableView
		jobData.clear();
		jobData.addAll(jobController.getAllJobData());
		jobView.getItems().setAll(jobData);
	}

}
