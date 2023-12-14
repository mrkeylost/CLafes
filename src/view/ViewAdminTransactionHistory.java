package view;

import java.util.Date;
import java.util.List;

import controller.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Transaction;
import model.TransactionHeader;

public class ViewAdminTransactionHistory {
	TransactionController transaction = new TransactionController();
	BorderPane bp;
	Button choose, back;
	VBox container;
	TableView<TransactionHeader> transactionHeaderView;
	TableView<Transaction> viewCustomerHistory;
	TableColumn<TransactionHeader, Integer> transactionId;
	TableColumn<TransactionHeader, Integer> staffId;
	TableColumn<TransactionHeader, String> staffName;
	TableColumn<TransactionHeader, Date> transactionDate;
	TableColumn<TransactionHeader, Void> viewDetail;
	List<TransactionHeader> transactionHeaderData;
	TransactionController transactionController = new TransactionController();
	VBox vbox;
	TableColumn<Transaction, Integer> colTrId, colPcId;
	TableColumn<Transaction, String> colBookedTime;
	List<Transaction> transacionHistoryData;

	public ViewAdminTransactionHistory(Stage stage, String role, int id) {
		bp = new BorderPane();
		container = new VBox();
		container.setAlignment(Pos.TOP_LEFT);

		back = new Button("Back to Home");
		back.setOnMouseClicked(event -> {

			HomePage homePage = new HomePage(stage, role, id);

			stage.setScene(new Scene(homePage.getBp(), 600, 600));
		});

		transactionHeaderView = new TableView<>();

		transactionId = new TableColumn<>("Transaction Id");
		transactionId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));

		staffId = new TableColumn<>("Staff Id");
		staffId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
		
		staffName = new TableColumn<>("Staff Name");
		staffName.setCellValueFactory(new PropertyValueFactory<>("staffName"));
		
		transactionDate = new TableColumn<>("Transaction Date");
		transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
		
		
		viewDetail = new TableColumn<>("View Detail");
		viewDetail.setCellFactory(param -> new ButtonCell(stage, role, id));

		transactionHeaderView.getColumns().add(transactionId);
		transactionHeaderView.getColumns().add(staffId);
		transactionHeaderView.getColumns().add(staffName);
		transactionHeaderView.getColumns().add(transactionDate);
		transactionHeaderView.getColumns().add(viewDetail);
		transactionHeaderData = transaction.getTransactionHeader();

		ObservableList<TransactionHeader> transactionHeaderList = FXCollections.observableArrayList(transactionHeaderData);
		transactionHeaderView.setItems(transactionHeaderList);
		container.getChildren().addAll(back, transactionHeaderView);
		bp.setCenter(container);
	}

	public BorderPane getBp() {
		return bp;
	}

	private class ButtonCell extends TableCell<TransactionHeader, Void> {
		private final Button viewDetail = new Button("View Detail");

		ButtonCell(Stage stage, String role, int id) {

			viewDetail.setOnAction(event -> {
				TransactionHeader th = getTableView().getItems().get(getIndex());
				Integer transactionId = th.getTransactionId();
				
				viewTransactionDetail(stage, role, transactionId);
				
				stage.setScene(new Scene(bp, 600, 600));
			});
		}

		@Override
		protected void updateItem(Void item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setGraphic(null);
			} else {
				setGraphic(viewDetail);
			}
		}
		
		protected void viewTransactionDetail(Stage stage, String role, Integer id) {
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

			transacionHistoryData = transactionController.getTransactionDetail(id.toString());

			ObservableList<Transaction> transactionDataList = FXCollections.observableArrayList(transacionHistoryData);
			viewCustomerHistory.setItems(transactionDataList);
			
			vbox = new VBox();
			back = new Button("Back to Transaction Header");
			
			vbox.getChildren().addAll(back);
			
			container = new VBox();
			
			container.getChildren().addAll(vbox, viewCustomerHistory);
			
			bp.setCenter(container);
			
			back.setOnMouseClicked(e ->{
				
				ViewAdminTransactionHistory viewAdminTH = new ViewAdminTransactionHistory(stage, role, id);
					
				stage.setScene(new Scene(viewAdminTH.getBp(), 600, 600));
			});
		}

	}

}