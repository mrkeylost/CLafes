package view;

import java.util.List;

import controller.PcController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
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
	MenuBar navbar;
	Menu bookPc, manageJob, viewBookedPc, viewJob, viewTransactionHistory, makeReport;
	MenuItem bookPcItem, manageJobItem, viewBookedPcItem, viewJobItem, viewTransactionHistoryItem, makeReportItem;
	
	TableView<Pc> pcTableView;
	TableColumn<Pc, Integer> colPcId;
	TableColumn<Pc, String> colPcStatus;
	List<Pc> pcData;
	
	public HomePage(Stage stage, String role, int id) {
		
		initHomePage(stage, role, id);
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
	
	public void initHomePage(Stage stage, String role, int id) {
		
		switch(role) {
		case "Admin":
			initAdminHomePage(stage, role, id);
			viewAllPc();
			break;
		case "Customer":
			initCustomerHomePage(stage, role, id);
			viewAllPc();
			break;
		case "Technician":
			initTechinicianHomePage(stage, role, id);
			viewAllPc();
			break;
		case "Operator":
			initOperatorHomePage(stage, role, id);
			viewAllPc();
			break;
		}
		
		bp.setTop(navbar);
	}
	
	public void initCustomerHomePage(Stage stage, String role, int id) {
		bp = new BorderPane();
		
		navbar = new MenuBar();
		bookPc = new Menu("Book PC");
		viewTransactionHistory = new Menu("View Transaction History");
		makeReport = new Menu("Make Report");
		
		bookPcItem = new MenuItem("Book PC");
		viewTransactionHistoryItem = new MenuItem("View TransactionHistory");
		makeReportItem = new MenuItem("Make Report");
		
		navbar.getMenus().add(bookPc);
		navbar.getMenus().add(viewTransactionHistory);
		navbar.getMenus().add(makeReport);
		
		bookPc.getItems().add(bookPcItem);
		viewTransactionHistory.getItems().add(viewTransactionHistoryItem);
		makeReport.getItems().add(makeReportItem);
		
		bookPcItem.setOnAction(event ->{
			
			try {
				BookPcForm bookPcForm = new BookPcForm(stage, role, id);
				
				stage.setScene(new Scene(bookPcForm.getBp(), 600, 600));
			} catch (Exception e) {
				// TODO: handle exception
				 e.printStackTrace();
			}
		});
		
		viewTransactionHistoryItem.setOnAction(event ->{
			ViewCustomerTransactionHistory viewCustomerTransactionHistory = new ViewCustomerTransactionHistory(stage, role, id);
			
			stage.setScene(new Scene(viewCustomerTransactionHistory.getBp(), 600, 600));
		});
		
		makeReportItem.setOnAction(event ->{
			MakeReportForm makeReportForm = new MakeReportForm(stage, role, id);
			
			stage.setScene(new Scene(makeReportForm.getBp(), 600, 600));
		});
	}
	
	public void initAdminHomePage(Stage stage, String role, int id) {
		bp = new BorderPane();
		
		navbar = new MenuBar();
		manageJob = new Menu("Manage Job");
		manageJobItem = new MenuItem("Manage Job");
		
		navbar.getMenus().add(manageJob);
		
		manageJob.getItems().add(manageJobItem);
	}
	
	public void initOperatorHomePage(Stage stage, String role, int id) {
		bp = new BorderPane();
		
		navbar = new MenuBar();
		viewBookedPc = new Menu("View Booked PC");
		makeReport = new Menu("Make Report");
		
		viewBookedPcItem = new MenuItem("View Booked PC");
		makeReportItem = new MenuItem("Make Report");
		
		navbar.getMenus().add(viewBookedPc);
		navbar.getMenus().add(makeReport);
		
		viewBookedPc.getItems().add(viewBookedPcItem);
		makeReport.getItems().add(makeReportItem);
		
		makeReportItem.setOnAction(event ->{
			MakeReportForm makeReportForm = new MakeReportForm(stage, role, id);
			
			stage.setScene(new Scene(makeReportForm.getBp(), 600, 600));
		});
	}

	public void initTechinicianHomePage(Stage stage, String role, int id) {
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
