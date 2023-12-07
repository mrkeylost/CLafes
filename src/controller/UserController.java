package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.UserModel;

public class UserController {

	UserModel userModel = new UserModel();
	
	public Boolean registerUser(String name, String password, String confirm, String role, String age) {
		
		if(name.isEmpty() || password.isEmpty() || confirm.isEmpty() || age.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.showAndWait();
			
			return false;
		}
		
		if(name.length() < 7) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("Name must has minimum 7 characters");
			alert.showAndWait();
			
			return false;
		}
		
		if(password.length() < 6) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("Password must has minimum 6 characters");
			alert.showAndWait();
			
			return false;
		}
		
		for(char pass : password.toCharArray()) {
			if(!Character.isLetterOrDigit(pass)) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid Input");
				alert.setHeaderText("Password must use alphanumeric characters");
				alert.showAndWait();
				
				return false;
			}
		}
		
		if(!confirm.equals(password)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("Confirm password incorrect");
			alert.showAndWait();
			
			return false;
		}
		
		if(Integer.parseInt(age) < 13 || Integer.parseInt(age) > 65) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("Age invalid, please input between 13 - 65");
			alert.showAndWait();
			
			return false;
		}
		
		return userModel.registerUser(name, password, confirm, role, age);
	}
	
	public Boolean loginUser(String name, String password) {
		
		ResultSet rs = userModel.loginUser(name, password);
		
		Boolean loginValid = null;
		
		try {
			if(rs.next()) {
				loginValid = true;
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Invalid");
				alert.setHeaderText("Username or Password Incorrect");
				alert.showAndWait();
				
				loginValid = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loginValid;
	}
	
	
}
