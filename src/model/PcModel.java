package model;

import java.sql.ResultSet;

import database.Connect;

public class PcModel {

	Connect db = Connect.getInstance();
	
	public ResultSet viewAllPc() {
		
		String query = "SELECT * FROM `pc`";
		
		return db.selectData(query);
	}
	
}
