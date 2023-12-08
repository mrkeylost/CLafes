package view;

import java.time.LocalDate;
import java.util.List;

import controller.BookingController;
import controller.PcController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import model.Pc;

public class BookPcForm {

	PcController pcController = new PcController();
	BookingController bookingController = new BookingController();
	Scene scene;
	Stage stage;
	BorderPane bp;
	VBox container, bookForm;
	Label bookFormTitle, pcIdLbl, dateLbl;
	TextField pcIdFld;
	Button booking;
	DatePicker bookDate;
	
	TableView<Pc> pcTableView;
	TableColumn<Pc, Integer> colPcId;
	TableColumn<Pc, String> colPcStatus, colPcAvailable;
	List<Pc> pcData;
	
	public BookPcForm(Stage stage, String role, Integer id) {
		
		bp = new BorderPane();
		
		container = new VBox();
		container.setAlignment(Pos.TOP_CENTER);
		
		pcTableView = new TableView<>();
		
		colPcId = new TableColumn<>("PC ID");
		colPcId.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		
		colPcStatus = new TableColumn<>("PC Status");
		colPcStatus.setCellValueFactory(new PropertyValueFactory<>("pcStatus"));
		
		colPcAvailable = new TableColumn<>("PC Booking Availability");
		colPcAvailable.setCellValueFactory(new PropertyValueFactory<>("pcAvailability"));
		
		pcTableView.getColumns().add(colPcId);
		pcTableView.getColumns().add(colPcStatus);
		pcTableView.getColumns().add(colPcAvailable);
		
		pcData = pcController.viewAllPc();
		
		ObservableList<Pc> pcDataList = FXCollections.observableArrayList(pcData);
		pcTableView.setItems(pcDataList);
		
		bookForm = new VBox();
		bookFormTitle = new Label("Book your PC");
		
		pcIdLbl = new Label("PC ID");
		pcIdFld = new TextField();
		
		dateLbl = new Label("Booking Date");
		bookDate = new DatePicker();
		
		booking = new Button("Book PC");
		
		bookForm.getChildren().addAll(bookFormTitle, pcIdLbl, pcIdFld, dateLbl, bookDate, booking);
		
		bookDate.setEditable(false);
		bookDate.setValue(LocalDate.now());
		
		bookFormTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		
		container.getChildren().addAll(pcTableView, bookForm);
		
		bp.setCenter(container);
		
		booking.setOnMouseClicked(event ->{
			bookingController.bookPc(pcIdFld.getText(), id.toString(), bookDate.getValue().toString());
		});
		
//		scene = new Scene(bp, 600, 600);
//		
//		this.stage = stage;
//		this.stage.setScene(scene);
//		this.stage.show();
		
	}

	public BorderPane getBp() {
		return bp;
	}
	
}
