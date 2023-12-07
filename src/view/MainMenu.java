package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends BorderPane{
	
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
		
		UserController loginController = new UserController();
		bp = new BorderPane();
		navbar = new MenuBar();
		register = new Menu("Register");
		login = new Menu("Login");
		
		registerUser = new MenuItem("Register");
		
		navbar.getMenus().add(register);
		navbar.getMenus().add(login);
		
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
			loginController.loginUser(nameFld.getText(), passFld.getText());
		});
		
		registerUser.setOnAction(event ->{
			stage.setScene(new Scene(new RegisterView(stage), 600, 600));
		});
	}
	
	public BorderPane getBp() {
		return bp;
	}
	
	
	
}
