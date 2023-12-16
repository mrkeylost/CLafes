package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;

public class UserModel {

	Connect db = Connect.getInstance();
	
	// method execute query untuk menambahkan atau mendaftarkan Customer baru ke database
	public Boolean registerUser(String name, String password, String confirm, String role, String age) {
		
		String query = String.format("INSERT INTO `users`(`UserName`, `Password`, `UserRole`, `UserAge`) VALUES ('%s','%s','%s','%d')", name, password, role, Integer.parseInt(age));
		
		db.execute(query);
		
		return true;
	}
	
	// method execute query untuk mengambil data user berdasarkan Username dan passwordnya
	public ResultSet loginUser(String name, String password) {
		
		String query = "SELECT * FROM users WHERE UserName='" + name + "' AND Password='" + password + "'";
		
		return db.selectData(query);
	}
	
	// method execute query untuk mengambil semua data user yang terdaftar di database
	public ResultSet getAllUser() {

		String query = "SELECT * FROM `users`";

		return db.selectData(query);
	}
	
	// method execute query untuk menagmbil semua data user staff berdasarkan user idnya
	public ResultSet getStaffData(Integer id) {

		String query = "SELECT * FROM `users` WHERE UserId = '" + id + "'";

		return db.selectData(query);
	}
	
	// method execute query untuk mengambil semua data user yang rolenya Admin, Operator, atau Computer Technician
	public ResultSet getStaffList() {
		
		String query = "SELECT UserId, UserName, UserRole FROM users WHERE UserRole != 'Customer'";
		
		return db.selectData(query);
	}
	
	// method execute query untuk mengupdate data User role berdasarkan user idnya
	public void ChangeUserRole(String userId, String RoleName) {
		String query = String.format("UPDATE users SET UserRole = '%s' WHERE UserId = %d", RoleName, Integer.parseInt(userId));
		
		 db.execute(query);
	}
	
	// method execute query untuk mengambil data User role berdasarkan user idnya
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
	
	// method execute query untuk mengambil data job status berdasarkan user idnya
	public Boolean isTechnicianBeingAssigned(String userId) {
		String query = String.format("SELECT JobStatus FROM `job` WHERE UserId = %d", Integer.parseInt(userId));
		
		ResultSet rs = db.selectData(query);
		
		try {
			while(rs.next()) {
				String status = rs.getString("JobStatus");
				
				if(status.equals("UnComplete")) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}