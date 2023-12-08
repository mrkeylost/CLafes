package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu{
	
	Scene scene;
	BorderPane bp;
	MenuBar navbar;
	Menu register, login;
	MenuItem registerUser;
	VBox container;
	Label nameLbl, passLbl;
	TextField nameFld;
	PasswordField passFld;
	Button loginBtn;

	public MainMenu(Stage stage) {
		
		UserController userController = new UserController();
		bp = new BorderPane();
		navbar = new MenuBar();
		register = new Menu("Register");
		
		registerUser = new MenuItem("Register");
		
		navbar.getMenus().add(register);
		
		register.getItems().add(registerUser);
		
		bp.setTop(navbar);
		
		container = new VBox();
		
		nameLbl = new Label("Name");
		passLbl = new Label("Password");
		
		nameFld = new TextField();
		passFld = new PasswordField();
		
		loginBtn = new Button("Login");
		
		container.getChildren().addAll(nameLbl, nameFld, passLbl, passFld, loginBtn);
		
		bp.setCenter(container);
		
		loginBtn.setOnMouseClicked(event ->{
			ResultSet loginValid = userController.loginUser(nameFld.getText(), passFld.getText());
			
			try {
				if(loginValid.next()) {
					String role = loginValid.getString("UserRole");
					Integer id = loginValid.getInt("UserId");
					
					HomePage homePage = new HomePage(stage, role, id);
				
					stage.setScene(new Scene(homePage.getBp(), 600, 600));
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Invalid Input");
					alert.setHeaderText("Username or Password incorrect");
					alert.showAndWait();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		register.setOnAction(event ->{
			stage.setScene(new Scene(new RegisterView(stage), 600, 600));
		});
	}
	
	public BorderPane getBp() {
		return bp;
	}
	
	
	
}
