package view;

import java.util.List;

import controller.TransactionController;
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
import model.Transaction;

public class ViewCustomerTransactionHistory {

	BorderPane bp;
	TransactionController transactionController = new TransactionController();
	
	TableView<Transaction> viewCustomerHistory;
	TableColumn<Transaction, Integer> colTrId, colPcId;
	TableColumn<Transaction, String> colBookedTime;
	List<Transaction> transacionHistoryData;
	
	VBox vbox, container;
	Button back; 
	
	public ViewCustomerTransactionHistory(Stage stage, String role, Integer id) {
		
		bp = new BorderPane();
		
		viewCustomerHistory = new TableView<>();
		
		colTrId = new TableColumn<>("Transaction ID");
		colTrId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		
		colPcId = new TableColumn<>("PC ID");
		colPcId.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		
		colBookedTime = new TableColumn<>("Booked Time");
		colBookedTime.setCellValueFactory(new PropertyValueFactory<>("bookedTime"));
		
		viewCustomerHistory.getColumns().add(colTrId);
		viewCustomerHistory.getColumns().add(colPcId);
		viewCustomerHistory.getColumns().add(colBookedTime);		

		transacionHistoryData = transactionController.getUserTransactionDetail(id.toString());

		ObservableList<Transaction> transactionDataList = FXCollections.observableArrayList(transacionHistoryData);
		viewCustomerHistory.setItems(transactionDataList);
		
		vbox = new VBox();
		back = new Button("Back to Home");
		
		vbox.getChildren().addAll(back);
		
		container = new VBox();
		
		container.getChildren().addAll(viewCustomerHistory, vbox);
		
		bp.setCenter(container);
		
		back.setOnMouseClicked(event ->{
			
			HomePage homePage = new HomePage(stage, "Admin", id);
				
			stage.setScene(new Scene(homePage.getBp(), 600, 600));
		});
	}

	public BorderPane getBp() {
		return bp;
	}
	
}
