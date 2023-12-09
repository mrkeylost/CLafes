package view;

import java.util.List;

import controller.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Transaction;

public class ViewCustomerTransactionHistory {

	BorderPane bp;
	TransactionController transactionController = new TransactionController();
	
	TableView<Transaction> viewCustomerHistory;
	TableColumn<Transaction, Integer> colTrId, colPcId;
	TableColumn<Transaction, String> colBookedTime;
	List<Transaction> transacionHistoryData;
	
	public ViewCustomerTransactionHistory(Stage stage, Integer id) {
		
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
		
		bp.setCenter(viewCustomerHistory);
		
		transacionHistoryData = transactionController.getUserTransactionDetail(id.toString());
		
		ObservableList<Transaction> transactionDataList = FXCollections.observableArrayList(transacionHistoryData);
		viewCustomerHistory.setItems(transactionDataList);
		
	}

	public BorderPane getBp() {
		return bp;
	}
	
}
