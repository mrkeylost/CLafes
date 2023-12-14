package view;

import java.util.List;

import controller.BookingController;
import controller.PcController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
import model.Pc;

public class HomePage {

	PcController pcController = new PcController();
	BookingController bookingController = new BookingController();
	
	Stage stage;
	Scene scene;
	BorderPane bp;
	VBox homeContainer;
	MenuBar navbar;
	Menu bookPc, manageJob, viewBookedPc, viewJob, viewTransactionHistory, makeReport, viewAllStaff, viewAllPc;
	MenuItem bookPcItem, manageJobItem, viewBookedPcItem, viewJobItem, viewTransactionHistoryItem, makeReportItem,
			viewAllStaffItem, viewPcDetail, viewAllPcItem;

	TableView<Pc> pcTableView;
	TableColumn<Pc, Integer> colPcId;
	TableColumn<Pc, String> colPcStatus;
	List<Pc> pcData;

	public HomePage(Stage stage, String role, int id) {

		initHomePage(stage, role, id);
	}

	public void viewAllPc(String role) {
		bp = new BorderPane();
		VBox container = new VBox();
		TextField selectedField = new TextField();
		selectedField.setEditable(false);
		Button addPCButton = new Button("Add PC");
		Button deletePCButton = new Button("Delete PC");
		Button updatePCButton = new Button("Update PC");

		pcTableView = new TableView<>();

		colPcId = new TableColumn<>("PC ID");
		colPcId.setCellValueFactory(new PropertyValueFactory<>("pcId"));

		colPcStatus = new TableColumn<>("PC Status");
		colPcStatus.setCellValueFactory(new PropertyValueFactory<>("pcStatus"));

		pcTableView.getColumns().add(colPcId);
		pcTableView.getColumns().add(colPcStatus);


		pcData = pcController.viewAllPc();

		ObservableList<Pc> pcDataList = FXCollections.observableArrayList(pcData);
		pcTableView.setItems(pcDataList);

		if (role.equals("Admin")) {
			container.getChildren().addAll(pcTableView, selectedField, addPCButton, deletePCButton, updatePCButton);
			bp.setCenter(container);
			
			pcTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue != null) {
					Integer selectedIndex = pcTableView.getSelectionModel().getSelectedIndex();
					if(selectedIndex != null) {
						Integer text = pcData.get(selectedIndex).getPcId();
						selectedField.setText(text.toString());
					}
				}
			});
			
			addPCButton.setOnAction(e -> {
				new AddPCForm();
				refetchPcData();
			});
			
			updatePCButton.setOnAction(e -> {
				String pcId = selectedField.getText();
				new UpdatePCForm(pcId);
				selectedField.setText("");
				refetchPcData();
				
			});
			
			deletePCButton.setOnAction(e -> {
				String pcId = selectedField.getText();
				pcController.deletePC(pcId);
				selectedField.setText("");
				refetchPcData();
			});
			
		} else {
			bp.setCenter(pcTableView);
		}

	}

	public void initHomePage(Stage stage, String role, int id) {

		switch (role) {
		case "Admin":
			initAdminHomePage(stage, role, id);
			viewAllPc(role);
			break;
		case "Customer":
			initCustomerHomePage(stage, role, id);
			viewAllPc(role);
			break;
		case "Computer Technician":
			initTechinicianHomePage(stage, role, id);
			viewAllPc(role);
			break;
		case "Operator":
			initOperatorHomePage(stage, role, id);
			viewAllPc(role);
			break;
		}

		bp.setTop(navbar);
	}

	public void initCustomerHomePage(Stage stage, String role, int id) {
		bp = new BorderPane();

		navbar = new MenuBar();
		viewAllPc = new Menu("View All PC");
		bookPc = new Menu("Book PC");
		viewTransactionHistory = new Menu("View Transaction History");
		makeReport = new Menu("Make Report");

		viewAllPcItem = new MenuItem("View All PC");
		bookPcItem = new MenuItem("Book PC");
		viewTransactionHistoryItem = new MenuItem("View TransactionHistory");
		makeReportItem = new MenuItem("Make Report");

		navbar.getMenus().add(viewAllPc);
		navbar.getMenus().add(bookPc);
		navbar.getMenus().add(viewTransactionHistory);
		navbar.getMenus().add(makeReport);

		bookPc.getItems().add(bookPcItem);
		viewTransactionHistory.getItems().add(viewTransactionHistoryItem);
		makeReport.getItems().add(makeReportItem);
		viewAllPc.getItems().add(viewAllPcItem);

		bookPcItem.setOnAction(event -> {
			BookPcForm bookPcForm = new BookPcForm(stage, role, id);

			stage.setScene(new Scene(bookPcForm.getBp(), 600, 600));

		});

		viewTransactionHistoryItem.setOnAction(event -> {
			ViewCustomerTransactionHistory viewCustomerTransactionHistory = new ViewCustomerTransactionHistory(stage,
					role, id);

			stage.setScene(new Scene(viewCustomerTransactionHistory.getBp(), 600, 600));
		});

		makeReportItem.setOnAction(event -> {
			MakeReportForm makeReportForm = new MakeReportForm(stage, role, id);

			stage.setScene(new Scene(makeReportForm.getBp(), 600, 600));
		});
		viewAllPcItem.setOnAction(e -> {
			viewAllPc(role);
		});
	}

	public void initAdminHomePage(Stage stage, String role, int id) {
		bp = new BorderPane();

		navbar = new MenuBar();
		viewAllPc = new Menu("View All PC");
		manageJob = new Menu("Manage Job");
		manageJobItem = new MenuItem("Manage Job");
		viewAllStaff = new Menu("View All Staff");
		viewTransactionHistory = new Menu("View Transaction History");
		makeReport = new Menu("View All Report");
		
		viewAllPcItem = new MenuItem("View All PC");
		viewPcDetail = new MenuItem("View PC Detail");
		viewAllStaffItem = new MenuItem("View All Staff");
		viewTransactionHistoryItem = new MenuItem("View Transaction History");
		makeReportItem = new MenuItem("View All Report");
		
		navbar.getMenus().add(viewAllPc);
		navbar.getMenus().add(manageJob);
		navbar.getMenus().add(viewAllStaff);
		navbar.getMenus().add(viewTransactionHistory);
		navbar.getMenus().add(makeReport);

		viewAllStaff.getItems().add(viewAllStaffItem);
		viewAllPc.getItems().add(viewPcDetail);
		viewTransactionHistory.getItems().add(viewTransactionHistoryItem);
		makeReport.getItems().add(makeReportItem);

		viewAllStaffItem.setOnAction(e -> {
			ViewAllStaff viewAllStaff = new ViewAllStaff(stage, role, id);

			stage.setScene(new Scene(viewAllStaff.getBp(), 600, 600));
		});
		
		viewPcDetail.setOnAction(e -> {
			viewAllPc(role);
		});
		manageJob.getItems().add(manageJobItem);
		
		manageJobItem.setOnAction(e -> {
			ViewAllJob viewAllJob = new ViewAllJob(stage, role, id);
			
			stage.setScene(new Scene(viewAllJob.getBp(), 600, 600));
		});
		
		viewTransactionHistoryItem.setOnAction(e -> {
			ViewAdminTransactionHistory viewAdminTH = new ViewAdminTransactionHistory(stage, role, id);
			
			stage.setScene(new Scene(viewAdminTH.getBp(), 600, 600));
		});
		
		makeReportItem.setOnAction(e -> {
			ViewAllReport viewReport = new ViewAllReport(stage, role, id);
			
			stage.setScene(new Scene(viewReport.getBp(), 600, 600));
		});
	}

	public void initOperatorHomePage(Stage stage, String role, int id) {
		bp = new BorderPane();

		navbar = new MenuBar();
		viewAllPc = new Menu("View All PC");
		viewBookedPc = new Menu("View Booked PC");
		makeReport = new Menu("Make Report");

		viewBookedPcItem = new MenuItem("View Booked PC");
		makeReportItem = new MenuItem("Make Report");
		viewAllPcItem = new MenuItem("View All PC");
		
		navbar.getMenus().add(viewAllPc);
		navbar.getMenus().add(viewBookedPc);
		navbar.getMenus().add(makeReport);

		viewBookedPc.getItems().add(viewBookedPcItem);
		makeReport.getItems().add(makeReportItem);
		viewAllPc.getItems().add(viewAllPcItem);
		
		viewBookedPcItem.setOnAction(event -> {
			ViewBookedPC viewBookedPc = new ViewBookedPC(stage, role, id);
			stage.setScene(new Scene(viewBookedPc.getBp(), 600, 700));
		});
		
		makeReportItem.setOnAction(event ->{
			MakeReportForm makeReportForm = new MakeReportForm(stage, role, id);
			
			stage.setScene(new Scene(makeReportForm.getBp(), 600, 600));
		});
		
		viewAllPcItem.setOnAction(e -> {
			viewAllPc(role);
		});
	}

	public void initTechinicianHomePage(Stage stage, String role, int id) {
		bp = new BorderPane();

		navbar = new MenuBar();
		viewAllPc = new Menu("View All PC");
		viewJob = new Menu("View Job");
		viewJobItem = new MenuItem("View Job Item");
		viewAllPcItem = new MenuItem("View All PC");
		
		navbar.getMenus().add(viewAllPc);
		navbar.getMenus().add(viewJob);

		viewJob.getItems().add(viewJobItem);
		viewAllPc.getItems().add(viewAllPcItem);
		
		viewJobItem.setOnAction(event ->{
			ViewTechnicianJob viewTechnicianJob = new ViewTechnicianJob(stage, id, role);
			
			stage.setScene(new Scene(viewTechnicianJob.getBp(), 600, 600));
		});
		
		viewAllPcItem.setOnAction(e -> {
			viewAllPc(role);
		});
	}

	public BorderPane getBp() {
		return bp;
	}
	
	public void refetchPcData() {
		pcData.clear();
		pcData.addAll(pcController.viewAllPc());
		pcTableView.getItems().setAll(pcData);
	}

}
