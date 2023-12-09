package model;

import java.sql.ResultSet;

import database.Connect;

public class UserModel {

	Connect db = Connect.getInstance();
	
	public Boolean registerUser(String name, String password, String confirm, String role, String age) {
		
		String query = String.format("INSERT INTO `users`(`UserName`, `Password`, `UserRole`, `UserAge`) VALUES ('%s','%s','%s','%d')", name, password, role, Integer.parseInt(age));
		
		db.execute(query);
		
		return true;
	}
	
	public ResultSet loginUser(String name, String password) {
		
		String query = "SELECT * FROM users WHERE UserName='" + name + "' AND Password='" + password + "'";
		
		return db.selectData(query);
	}
	
	public ResultSet getAllUser() {
		
		String query = "SELECT * FROM `users`";
		
		return db.selectData(query);
	}
	
}
