package controller;

import java.sql.ResultSet;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.UserModel;

public class UserController {

	UserModel userModel = new UserModel();
	
	public void alert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
	public Boolean registerUser(String name, String password, String confirm, String role, String age) {
		
		if(name.isEmpty() || password.isEmpty() || confirm.isEmpty() || age.isEmpty()) {
			alert("All field can't empty");
			
			return false;
		}
		
		if(name.length() < 7) {
			alert("Name must has minimum 7 characters");
			
			return false;
		}
		
		if(password.length() < 6) {
			alert("Password must has minimum 6 characters");
			
			return false;
		}
		
		for(char pass : password.toCharArray()) {
			if(!Character.isLetterOrDigit(pass)) {
				
				alert("Password must use alphanumeric characters");
				
				return false;
			}
		}
		
		if(!confirm.equals(password)) {
			alert("Confirm password incorrect");
			
			return false;
		}
		
		if(Integer.parseInt(age) < 13 || Integer.parseInt(age) > 65) {
			alert("Age invalid, please input between 13 - 65");
			
			return false;
		}
		
		return userModel.registerUser(name, password, confirm, role, age);
	}
	
	public ResultSet loginUser(String name, String password) {
		
		ResultSet rs = userModel.loginUser(name, password);
		
//		Boolean loginValid = null;
//		
//		try {
//			if(rs.next()) {
//				loginValid = true;
//				
//				String roleAuthorization = rs.getString("UserRole");
//			} else {
//				alert("Username or Password Incorrect");
//				
//				loginValid = false;
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return rs;
	}
}
