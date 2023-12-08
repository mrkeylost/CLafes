package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterView extends BorderPane{
	
	public RegisterView(Stage stage) {
		
		UserController userController = new UserController();
		MainMenu mainMenu = new MainMenu(stage);
		VBox vbox = new VBox();
		Label title = new Label("Register");
		Label nameLbl = new Label("Name");
		Label passLbl = new Label("Password");
		Label confPassLbl = new Label("Confirm Password");
		Label ageLbl = new Label("Age");
		
		TextField nameFld = new TextField();
		PasswordField passFld = new PasswordField();
		PasswordField confPassFld = new PasswordField();
		TextField ageFld = new TextField();
		
		Button registerBtn = new Button("Register");
		
		Button loginBtn = new Button("Login");
		
		vbox.getChildren().addAll(title, nameLbl, nameFld, passLbl, passFld, confPassLbl, confPassFld, ageLbl, ageFld, registerBtn, loginBtn);
		
		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		
		registerBtn.setOnMouseClicked(event ->{
			Boolean registerValid = userController.registerUser(nameFld.getText(), passFld.getText(), confPassFld.getText(), "Customer", ageFld.getText());
			
			if(registerValid) {
				stage.setScene(new Scene(mainMenu.getBp(), 600, 600));
			}
		});
		
		loginBtn.setOnMouseClicked(event ->{
			stage.setScene(new Scene(mainMenu.getBp(), 600, 600));
		});
		
		setCenter(vbox);
	}	
	
}
