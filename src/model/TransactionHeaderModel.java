package model;

import java.sql.ResultSet;

import database.Connect;

public class TransactionHeaderModel {
	
	Connect db = Connect.getInstance();
	
	// method execute query untuk mengambil semua data transaction header
	public ResultSet getTransactionHeader() {

		String query = "SELECT * FROM `trheader`";

		return db.selectData(query);
	}
}
