package model;

import database.Connect;

public class UserModel {

	Connect db = Connect.getInstance();
	
	public Boolean registerUser(String name, String password, String confirm, String role, String age) {
		
		String query = String.format("INSERT INTO `users`(`UserName`, `Password`, `UserRole`, `UserAge`) VALUES ('%s','%s','%s','%d')", name, password, role, age);
		
		db.execute(query);
		
		return true;
	}
	
}
