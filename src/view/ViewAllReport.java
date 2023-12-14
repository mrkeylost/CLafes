package view;

import java.util.Date;
import java.util.List;

import controller.ReportController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Report;

public class ViewAllReport {

	BorderPane bp;
	ReportController report = new ReportController();
	
	TableView<Report> viewReport;
	TableColumn<Report, Integer> reportId, pcId;
	TableColumn<Report, String> reportNote, userRole;
	TableColumn<Report, Date> reportDate;
	List<Report> reportData;
	
	VBox vbox, container;
	Button back; 
	
	public ViewAllReport(Stage stage, String role, Integer id) {
		
		bp = new BorderPane();
		
		viewReport = new TableView<>();
		
		reportId = new TableColumn<>("Report ID");
		reportId.setCellValueFactory(new PropertyValueFactory<>("reportId"));
		
		pcId = new TableColumn<>("PC ID");
		pcId.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		
		reportNote = new TableColumn<>("Report Note");
		reportNote.setCellValueFactory(new PropertyValueFactory<>("reportNote"));

		reportDate = new TableColumn<>("Report Date");
		reportDate.setCellValueFactory(new PropertyValueFactory<>("reportDate"));
		
		userRole = new TableColumn<>("User Role");
		userRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		
		viewReport.getColumns().add(reportId);
		viewReport.getColumns().add(pcId);
		viewReport.getColumns().add(reportNote);		
		viewReport.getColumns().add(reportDate);		
		viewReport.getColumns().add(userRole);		

		reportData = report.getAllReportData();

		ObservableList<Report> reportDataList = FXCollections.observableArrayList(reportData);
		viewReport.setItems(reportDataList);
		
		vbox = new VBox();
		back = new Button("Back to Home");
		
		vbox.getChildren().addAll(back);
		
		container = new VBox();
		
		container.getChildren().addAll(viewReport, vbox);
		
		bp.setCenter(container);
		
		back.setOnMouseClicked(event ->{
			
			HomePage homePage = new HomePage(stage, role, id);
				
			stage.setScene(new Scene(homePage.getBp(), 600, 600));
		});
	}

	public BorderPane getBp() {
		return bp;
	}
	
}
