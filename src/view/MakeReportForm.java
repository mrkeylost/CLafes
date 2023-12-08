package view;

import controller.ReportController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MakeReportForm {
	
	ReportController reportController = new ReportController();
	BorderPane bp;
	VBox reportContainer;
	Label makeReportTitle, pcIdLbl, reportNoteLbl;
	TextField pcIdFld, reportNoteFld;
	Button report;

	public MakeReportForm(Stage stage, String role) {
		
		bp = new BorderPane();
		reportContainer = new VBox();
		makeReportTitle = new Label("Make Your Report Here");
		
		pcIdLbl = new Label("PC ID");
		reportNoteLbl = new Label("Report Note");
		
		pcIdFld = new TextField();
		reportNoteFld = new TextField();
		
		report = new Button("Report");
		
		reportContainer.getChildren().addAll(makeReportTitle, pcIdLbl, pcIdFld, reportNoteLbl, reportNoteFld, report);
		
		makeReportTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		
		bp.setCenter(reportContainer);
		
		report.setOnMouseClicked(event ->{
			Boolean reportValid = reportController.addNewReport(role, pcIdFld.getText(), reportNoteFld.getText());
			
			if(reportValid) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Report submitted");
				alert.showAndWait();
			}
		});
	}
	
	public BorderPane getBp() {
		return bp;
	}
	
}
