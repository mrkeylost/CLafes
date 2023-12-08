package view;

import java.util.List;

import controller.PcController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Pc;

public class HomePage{

	PcController pcController =  new PcController();
	Stage stage;
	Scene scene;
	BorderPane bp;
	VBox homeContainer;
	Button bookPcBtn; 
	MenuBar navbar;
	Menu bookPc, manageJob, viewBookedPc, viewJob;
	MenuItem bookPcItem, manageJobItem, viewBookedPcItem, viewJobItem;
	
	TableView<Pc> pcTableView;
	TableColumn<Pc, Integer> colPcId;
	TableColumn<Pc, String> colPcStatus;
	List<Pc> pcData;
	
	public HomePage(Stage stage, String role) {
		
		initHomePage(role);
		
//		scene = new Scene(bp, 600, 600);
//		
//		this.stage = stage;
//		this.stage.setScene(scene);
//		this.stage.show();
	}
	
	public void viewAllPc() {
		bp = new BorderPane();
		
		pcTableView = new TableView<>();
		
		colPcId = new TableColumn<>("PC ID");
		colPcId.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		
		colPcStatus = new TableColumn<>("PC Status");
		colPcStatus.setCellValueFactory(new PropertyValueFactory<>("pcStatus"));
		
		pcTableView.getColumns().add(colPcId);
		pcTableView.getColumns().add(colPcStatus);
		
		bp.setCenter(pcTableView);
		
		pcData = pcController.viewAllPc();
		
		ObservableList<Pc> pcDataList = FXCollections.observableArrayList(pcData);
		pcTableView.setItems(pcDataList);
	}
	
	public void initHomePage(String role) {
		
		switch(role) {
		case "Admin":
			initAdminHomePage();
			viewAllPc();
			break;
		case "Customer":
			initCustomerHomePage();
			viewAllPc();
			break;
		case "Technician":
			initTechinicianHomePage();
			viewAllPc();
			break;
		case "Operator":
			initOperatorHomePage();
			viewAllPc();
			break;
		}
		
		bp.setTop(navbar);
	}
	
	public void initCustomerHomePage() {
		bp = new BorderPane();
		
		navbar = new MenuBar();
		bookPc = new Menu("Book PC");
		bookPcItem = new MenuItem("Book PC");
		
		navbar.getMenus().add(bookPc);
		
		bookPc.getItems().add(bookPcItem);
	}
	
	public void initAdminHomePage() {
		bp = new BorderPane();
		
		navbar = new MenuBar();
		manageJob = new Menu("Manage Job");
		manageJobItem = new MenuItem("Manage Job");
		
		navbar.getMenus().add(manageJob);
		
		manageJob.getItems().add(manageJobItem);
	}
	
	public void initOperatorHomePage() {
		bp = new BorderPane();
		
		navbar = new MenuBar();
		viewBookedPc = new Menu("View Booked PC");
		viewBookedPcItem = new MenuItem("View Booked PC");
		
		navbar.getMenus().add(viewBookedPc);
		
		viewBookedPc.getItems().add(viewBookedPcItem);
	}

	public void initTechinicianHomePage() {
		bp = new BorderPane();
		
		navbar = new MenuBar();
		viewJob = new Menu("View Job");
		viewJobItem = new MenuItem("View Job Item");
		
		navbar.getMenus().add(viewJob);
		
		viewJob.getItems().add(viewJobItem);
	}

	public BorderPane getBp() {
		return bp;
	}
	
}
