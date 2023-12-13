package model;

import java.sql.ResultSet;

import database.Connect;

public class TransactionHeaderModel {
	
	Connect db = Connect.getInstance();
	
	public ResultSet getTransactionHeader() {

		String query = "SELECT * FROM `trheader`";

		return db.selectData(query);
	}
}
