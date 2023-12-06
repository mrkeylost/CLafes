package controller;

import model.UserModel;

public class UserController {

	UserModel userModel = new UserModel();
	
	public Boolean registerUser(String name, String password, String confirm, String role, String age) {
		
		if(name.isEmpty() || password.isEmpty() || confirm.isEmpty() || age.isEmpty()) {
			return false;
		}
		
		if(name.length() < 7) {
			return false;
		}
		
		if(password.length() < 6) {
			return false;
		}
		
		if(confirm != password) {
			return false;
		}
		
		if(Integer.parseInt(age) < 13 || Integer.parseInt(age) > 65) {
			return false;
		}
		
		return true;
	}
	
	
}
