package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;
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
		
		if(!checkUniqueUserName(name)) {
			alert("Name already used");

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
		
		return rs;
	}
	
	public Boolean checkUniqueUserName(String name) {

		ResultSet rs = userModel.getAllUser();

		try {
			while(rs.next()) {
				String userName = rs.getString("UserName");

				if(userName.equals(name)) {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	public List<User> getAllStaff(){
		
		Vector<User> staffList = new Vector<>();
		
		ResultSet rs = userModel.getStaffList();
		
		try {
			while(rs.next()) {
				Integer userId = rs.getInt("UserId");
				String userName = rs.getString("UserName");
				String userRole = rs.getString("UserRole");
				
				staffList.add(new User(userId,userName, userRole));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return staffList;
		
	}
	
	public void ChangeRoleUser(int userId, String roleName) {
		userModel.ChangeUserRole(userId, roleName);
	}
}
