package view;

import java.util.List;

import controller.JobController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Job;

public class ViewTechnicianJob {

	BorderPane bp;
	
	JobController jobController = new JobController();
	TableView<Job> viewTechnicianJob;
	TableColumn<Job, Integer> colJobId, colPcId;
	TableColumn<Job, String> colJobStatus;
	List<Job> technicianJobData;
	
	VBox vbox, container;
	Label jobFormTitle, jobLbl;
	TextField jobFld;
	Button completeBtn, back;

	public ViewTechnicianJob(Stage stage, Integer id, String role) {
		bp = new BorderPane();
		
		viewTechnicianJob = new TableView<>();
		
		colJobId = new TableColumn<>("Job ID");
		colJobId.setCellValueFactory(new PropertyValueFactory<>("jobId"));
		
		colPcId = new TableColumn<>("PC ID");
		colPcId.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		
		colJobStatus = new TableColumn<>("Job Status");
		colJobStatus.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
		
		viewTechnicianJob.getColumns().add(colJobId);
		viewTechnicianJob.getColumns().add(colPcId);
		viewTechnicianJob.getColumns().add(colJobStatus);
		
		technicianJobData = jobController.viewTechnicianJob(id.toString());
		
		ObservableList<Job> jobDataList = FXCollections.observableArrayList(technicianJobData);
		viewTechnicianJob.setItems(jobDataList);
		
		vbox = new VBox();
		jobFormTitle = new Label("Complete Job Form");
		jobLbl = new Label("Choose Job ID");
		jobFld = new TextField();
		completeBtn = new Button("Complete Job");
		back = new Button("Back to Home");
		
		jobFormTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		
		vbox.getChildren().addAll(jobFormTitle, jobLbl, jobFld, completeBtn, back);
		
		container = new VBox();
		
		container.getChildren().addAll(viewTechnicianJob, vbox);
		
		bp.setCenter(container);
		
		completeBtn.setOnMouseClicked(event ->{
			Boolean completeValid = jobController.completeJob(id.toString(), jobFld.getText(), role);
			
			if(completeValid) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Complete Job Success");
				alert.showAndWait();
			}
		});
		
		back.setOnMouseClicked(event ->{
			
			HomePage homePage = new HomePage(stage, role, id);
			
			stage.setScene(new Scene(homePage.getBp(), 600, 600));
		});
		
	}
	
	public BorderPane getBp() {
		return bp;
	}
	
}
