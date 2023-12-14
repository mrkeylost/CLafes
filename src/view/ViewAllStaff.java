package view;

import java.util.List;

import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class ViewAllStaff {
	UserController staff = new UserController();
	BorderPane bp;
	Button choose, back, changeRole;
	VBox container;
	TextField selected;
	ComboBox<String> roles;
	TableView<User> userView;
	TableColumn<User, Integer> userId;
	TableColumn<User, String> userName;
	TableColumn<User, String> userRole;
	TableColumn<User, Void> changeRoleColumn;
	List<User> staffData;
	String selectedUserId = "";

	public ViewAllStaff(Stage stage, String role, int id) {
		bp = new BorderPane();
		container = new VBox();
		container.setAlignment(Pos.TOP_LEFT);

		back = new Button("Back to Home");
		changeRole = new Button("Change Role");
		selected = new TextField();
		selected.setEditable(false);
		back.setOnMouseClicked(event -> {

			HomePage homePage = new HomePage(stage, role, id);

			stage.setScene(new Scene(homePage.getBp(), 600, 600));
		});

		userView = new TableView<>();
		
		userId = new TableColumn<>("User Id");
		userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
		
		userName = new TableColumn<>("User Name");
		userName.setCellValueFactory(new PropertyValueFactory<>("userName"));

		userRole = new TableColumn<>("User Role");
		userRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));

		userView.getColumns().add(userId);
		userView.getColumns().add(userName);
		userView.getColumns().add(userRole);
		
		staffData = staff.getAllStaff();

		ObservableList<User> staffDataList = FXCollections.observableArrayList(staffData);
		userView.setItems(staffDataList);
		container.getChildren().addAll(back, userView, selected, changeRole);
		bp.setCenter(container);
		
		userView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null) {
				Integer selectedIndex = userView.getSelectionModel().getSelectedIndex();
				if(selectedIndex != null) {
					selectedUserId= staffData.get(selectedIndex).getUserId().toString();
					selected.setText(selectedUserId);
				}
			}
		});
		
		changeRole.setOnAction(e -> {
			new ChangeRoleForm(selected.getText());
			selected.setText("");
			selectedUserId = "";
			refreshView();
		});
	}

	public BorderPane getBp() {
		return bp;
	}

	private void refreshView() {
		// Refresh the data and update the TableView
		staffData.clear();
		staffData.addAll(staff.getAllStaff());
		userView.getItems().setAll(staffData);
	}
	

}
