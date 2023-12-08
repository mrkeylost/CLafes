package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage extends BorderPane{

	Scene scene;
	BorderPane bp;
	VBox homeContainer;
	Button bookPcBtn; 
	
	public HomePage(Stage stage, String role) {
		
		bp = new BorderPane();
		initHomePage(role);
	}
	
	public void initHomePage(String role) {
		
		switch(role) {
		case "Admin":
			initAdminHomePage();
			break;
		case "Customer":
			initCustomerHomePage();
			break;
		case "Technician":
			initTechinicianHomePage();
			break;
		case "Operator":
			initOperatorHomePage();
			break;
		}
	}
	
	public void initCustomerHomePage() {
		bp = new BorderPane();
		homeContainer = new VBox();
		bookPcBtn = new Button("Book PC");
	
		homeContainer.getChildren().addAll(bookPcBtn);
		
		bp.setCenter(homeContainer);
	}
	
	public void initAdminHomePage() {
		bp = new BorderPane();
		homeContainer = new VBox();
		bookPcBtn = new Button("Job management");
	
		homeContainer.getChildren().addAll(bookPcBtn);
		
		bp.setCenter(homeContainer);
	}
	
	public void initOperatorHomePage() {
		bp = new BorderPane();
		homeContainer = new VBox();
		bookPcBtn = new Button("Manage PC");
	
		homeContainer.getChildren().addAll(bookPcBtn);
		
		bp.setCenter(homeContainer);
	}

	public void initTechinicianHomePage() {
		bp = new BorderPane();
		homeContainer = new VBox();
		bookPcBtn = new Button("View Job");
	
		homeContainer.getChildren().addAll(bookPcBtn);
		
		bp.setCenter(homeContainer);
	}

	public BorderPane getBp() {
		return bp;
	}
	
}
