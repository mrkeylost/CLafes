package view;

import java.util.List;

import controller.BookingController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Booking;

public class ViewBookedPC {

	BorderPane bp;
	
	BookingController bookingController = new BookingController();
	TableView<Booking> viewAllBookedPc;
	TableColumn<Booking, Integer> colBookId, colPcId;
	TableColumn<Booking, String> colUserName, colBookedDate;
	List<Booking> bookedPcData;
	
	VBox vbox, container;
	Label bookLbl, bookedPcTitle, cancelBookLbl, finishBookLbl, finishDateLbl, assignUserTitle, choosePcLbl, bookIdLbl;
	TextField bookFld, choosePcFld, bookIdFld;
	Button cancelBtn, finishBtn, back, assignUserBtn;
	DatePicker finishBookDate;
	
	public ViewBookedPC(Stage stage, String role, Integer id) {
		setBookedPcTable();
		
		vbox = new VBox();
		initBookingManagementForm();
		initAssignUserForm();
		back = new Button("Back to Home");
		
		vbox.getChildren().addAll(
			bookedPcTitle, 
			cancelBookLbl, bookLbl, bookFld, cancelBtn,
			finishBookLbl, finishDateLbl, finishBookDate, finishBtn, 
			assignUserTitle, choosePcLbl, choosePcFld, bookIdLbl, bookIdFld, assignUserBtn, 
			back
		);
		
		style();
		
		container = new VBox();
		container.getChildren().addAll(viewAllBookedPc, vbox);
		bp.setCenter(container);
		
		action(stage, role, id);
	}
	
	private void setBookedPcTable() {
		bp = new BorderPane();
		
		viewAllBookedPc = new TableView<>();
		
		colBookId = new TableColumn<>("Booking ID");
		colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		
		colPcId = new TableColumn<>("PC ID");
		colPcId.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		
		colUserName = new TableColumn<>("User Name");
		colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		
		colBookedDate = new TableColumn<>("Booking Date");
		colBookedDate.setCellValueFactory(new PropertyValueFactory<>("bookedDate"));
		
		viewAllBookedPc.getColumns().add(colBookId);
		viewAllBookedPc.getColumns().add(colPcId);
		viewAllBookedPc.getColumns().add(colUserName);
		viewAllBookedPc.getColumns().add(colBookedDate);
		
		bookedPcData = bookingController.getAllPcBookedData();
		
		ObservableList<Booking> bookedPcList = FXCollections.observableArrayList(bookedPcData);
		viewAllBookedPc.setItems(bookedPcList);
	}
	
	private void initBookingManagementForm() {
		bookedPcTitle = new Label("Booking Management");
		
		cancelBookLbl = new Label("Cancel Book");
		bookLbl = new Label("Choose Booking ID");
		bookFld = new TextField();
		cancelBtn = new Button("Cancel Book");
		
		finishBookLbl = new Label("Finish Book");
		finishDateLbl = new Label("Choose Book Date to Finish");
		finishBookDate = new DatePicker();
		finishBookDate.setEditable(false);
		finishBtn = new Button("Finish Book");
	}
	
	private void initAssignUserForm() {
		assignUserTitle = new Label("Assign User to Another PC");
		choosePcLbl = new Label("Choose New PC ID");
		choosePcFld = new TextField();
		bookIdLbl = new Label("Choose Booking ID");
		bookIdFld = new TextField();
		assignUserBtn = new Button("Reassign User PC");
	}
	
	private void style() {
		bookedPcTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		bookedPcTitle.setPadding(new Insets(0, 0, 20, 0));
		
		cancelBookLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
		cancelBookLbl.setPadding(new Insets(0, 0, 6, 0));
		VBox.setMargin(cancelBtn, new Insets(0, 0, 23, 0));
		
		finishBookLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
		finishBookLbl.setPadding(new Insets(0, 0, 6, 0));
		VBox.setMargin(finishBtn, new Insets(0, 0, 23, 0));
		
		assignUserTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		assignUserTitle.setPadding(new Insets(0, 0, 20, 0));
		
		VBox.setMargin(choosePcFld, new Insets(0, 0, 15, 0));
		VBox.setMargin(bookIdFld, new Insets(0, 0, 15, 0));
		VBox.setMargin(assignUserBtn, new Insets(0, 0, 10, 0));
	}
	
	private void action(Stage stage, String role, Integer id) {
		cancelBtn.setOnMouseClicked(event -> {
			Boolean cancelValid = bookingController.deleteBookData(bookFld.getText());
			
			if(cancelValid) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Cancelation Succeed");
				alert.setHeaderText("Booking Canceled Successfully!");
				alert.showAndWait();
			}
		});
		
		finishBtn.setOnMouseClicked(event -> {
			Boolean isFinishBookSucceed = bookingController.finishBook(finishBookDate.getValue().toString(), id);
			
			if (isFinishBookSucceed) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Finish Book Succeed");
				alert.setHeaderText("All Passed Bookings Finished Successfully!");
				alert.showAndWait();
			}
		});
		
		assignUserBtn.setOnMouseClicked(event -> {
			Boolean isAssignUserSucceed = bookingController.assignUserToAnotherPc(choosePcFld.getText(), bookIdFld.getText());
			
			if(isAssignUserSucceed) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Assign User Succeed");
				alert.setHeaderText("User's new PC has been assigned successfully!");
				alert.showAndWait();
			}
		});
		
		back.setOnMouseClicked(event -> {
			HomePage homePage = new HomePage(stage, role, id);
			
			stage.setScene(new Scene(homePage.getBp(), 600, 600));
		});
	}

	public BorderPane getBp() {
		return bp;
	}
	
}
