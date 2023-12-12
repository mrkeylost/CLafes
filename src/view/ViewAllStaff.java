package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class ViewAllStaff {
	UserController staff = new UserController();
	BorderPane bp;
	Button choose, back;
	VBox container;
	ComboBox<String> roles;
	TableView<User> userView;
	TableColumn<User, String> userName;
	TableColumn<User, String> userRole;
	TableColumn<User, Void> changeRoleColumn;
	List<User> staffData;

	public ViewAllStaff(Stage stage, String role, int id) {
		bp = new BorderPane();
		container = new VBox();
		container.setAlignment(Pos.TOP_LEFT);

		back = new Button("Back to Home");
		back.setOnMouseClicked(event -> {

			HomePage homePage = new HomePage(stage, role, id);

			stage.setScene(new Scene(homePage.getBp(), 600, 600));
		});

		userView = new TableView<>();

		userName = new TableColumn<>("User Name");
		userName.setCellValueFactory(new PropertyValueFactory<>("userName"));

		userRole = new TableColumn<>("User Role");
		userRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));

		changeRoleColumn = new TableColumn<>("Change Role");
		changeRoleColumn.setCellFactory(param -> new ButtonCell());

		userView.getColumns().add(userName);
		userView.getColumns().add(userRole);
		userView.getColumns().add(changeRoleColumn);
		staffData = staff.getAllStaff();

		ObservableList<User> staffDataList = FXCollections.observableArrayList(staffData);
		userView.setItems(staffDataList);
		container.getChildren().addAll(back, userView);
		bp.setCenter(container);
	}

	public BorderPane getBp() {
		return bp;
	}

	private class ButtonCell extends TableCell<User, Void> {
		private final Button changeButton = new Button("Change");
		String newRole;
		int userId;

		ButtonCell() {

			changeButton.setOnAction(event -> {
				User user = getTableView().getItems().get(getIndex());
				userId = user.getUserId();
				newRole = user.getUserRole();
				TextInputDialog dialog = new TextInputDialog(user.getUserRole());
				dialog.setTitle("Change Role");
				dialog.setHeaderText("Enter new role:");
				dialog.setContentText("Role:");

				Optional<String> result = dialog.showAndWait();
				result.ifPresent(newRole -> {
					if(staff.ChangeRoleUser(userId, newRole)) {
						refreshView();
					}
				});
			});
		}

		@Override
		protected void updateItem(Void item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setGraphic(null);
			} else {
				setGraphic(changeButton);
			}
		}

		private void refreshView() {
			// Refresh the data and update the TableView
			staffData.clear();
			staffData.addAll(staff.getAllStaff());
			userView.getItems().setAll(staffData);
		}
	}

}
