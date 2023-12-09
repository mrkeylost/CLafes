package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterView extends BorderPane{
	
	BorderPane bp;
	VBox vbox;
	Label title, nameLbl, passLbl, confPassLbl, ageLbl;
	TextField nameFld;
	PasswordField passFld, confPassFld;
	Spinner<Integer> ageFld;
	Button registerBtn, loginBtn;
	
	public RegisterView(Stage stage) {
		
		UserController userController = new UserController();
		MainMenu mainMenu = new MainMenu(stage);
		vbox = new VBox();
		title = new Label("Register");
		nameLbl = new Label("Name");
		passLbl = new Label("Password");
		confPassLbl = new Label("Confirm Password");
		ageLbl = new Label("Age");
		
		nameFld = new TextField();
		passFld = new PasswordField();
		confPassFld = new PasswordField();
		ageFld = new Spinner<>(1, 100, 13);
		
		registerBtn = new Button("Register");
		
		loginBtn = new Button("Login");
		
		vbox.getChildren().addAll(title, nameLbl, nameFld, passLbl, passFld, confPassLbl, confPassFld, ageLbl, ageFld, registerBtn, loginBtn);
		
		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		
		registerBtn.setOnMouseClicked(event ->{
			Boolean registerValid = userController.registerUser(nameFld.getText(), passFld.getText(), confPassFld.getText(), "Customer",  ageFld.getValue().toString());
			
			if(registerValid) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Registration Success");
				alert.showAndWait();
				
				stage.setScene(new Scene(mainMenu.getBp(), 600, 600));
			}
		});
		
		loginBtn.setOnMouseClicked(event ->{
			stage.setScene(new Scene(mainMenu.getBp(), 600, 600));
		});
		
		setCenter(vbox);
	}	
	
}
