package model;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public ResultSet getStaffData(Integer id) {

		String query = "SELECT * FROM `users` WHERE UserId = '" + id + "'";

		return db.selectData(query);
	}
	
	public ResultSet getStaffList() {
		
		String query = "SELECT UserId, UserName, UserRole FROM users WHERE UserRole != 'Customer'";
		
		return db.selectData(query);
	}
	
	public void ChangeUserRole(String userId, String RoleName) {
		String query = String.format("UPDATE users SET UserRole = '%s' WHERE UserId = %d", RoleName, Integer.parseInt(userId));
		
		 db.execute(query);
	}
	
	public Boolean isRoleTechnician(String userId) {
		String query = String.format("SELECT UserRole FROM users WHERE UserId = %d", Integer.parseInt(userId));
		
		ResultSet rs = db.selectData(query);
		
		String role = "";
		
		try {
			while(rs.next()) {
				role = rs.getString("UserRole");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role.equals("Computer Technician") ? true : false;
	}
	
}