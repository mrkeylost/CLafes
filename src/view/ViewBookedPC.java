package view;

import java.util.List;

import controller.BookingController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
	Label bookLbl, bookedPcTitle;
	TextField bookFld;
	Button cancelBtn, finishBtn, back;
	
	public ViewBookedPC(Stage stage, String role, Integer id) {
		
		bp = new BorderPane();
		
		viewAllBookedPc = new TableView<>();
		
		colBookId = new TableColumn<>("Booking ID");
		colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		
		colPcId = new TableColumn<>("PC ID");
		colPcId.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		
		colUserName = new TableColumn<>("User Name");
		colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		
		colBookedDate = new TableColumn<>("Booking Date");
		colBookedDate.setCellValueFactory(new PropertyValueFactory<>("bookedTime"));
		
		viewAllBookedPc.getColumns().add(colBookId);
		viewAllBookedPc.getColumns().add(colPcId);
		viewAllBookedPc.getColumns().add(colUserName);
		viewAllBookedPc.getColumns().add(colBookedDate);
		
		bookedPcData = bookingController.getAllPcBookedData();
		
		ObservableList<Booking> bookedPcList = FXCollections.observableArrayList(bookedPcData);
		viewAllBookedPc.setItems(bookedPcList);
		
		vbox = new VBox();
		bookedPcTitle = new Label("Booking Management");
		bookLbl = new Label("Choose Booking ID");
		bookFld = new TextField();
		cancelBtn = new Button("Cancel Book");
		finishBtn = new Button("Finish Book");
		back = new Button("Back to Home");
		
		vbox.getChildren().addAll(bookedPcTitle, bookLbl, bookFld, cancelBtn, finishBtn, back);
		
		bookedPcTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		
		container = new VBox();
		
		container.getChildren().addAll(viewAllBookedPc, vbox);
		
		bp.setCenter(container);
		
		cancelBtn.setOnMouseClicked(event ->{
			Boolean cancelValid = bookingController.deleteBookData(bookFld.getText());
			
			if(cancelValid) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Operation Succeed");
				alert.setHeaderText("Cancel Booking successed");
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
