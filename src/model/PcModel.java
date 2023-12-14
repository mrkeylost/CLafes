package model;

import java.sql.ResultSet;

import database.Connect;

public class PcModel {

	Connect db = Connect.getInstance();
	
	
	public ResultSet viewAllPc() {
		
		String query = "SELECT * FROM `pc`";
		
		return db.selectData(query);
	}
	
	public void addNewPC(String id) {
		String query = String.format("INSERT INTO `pc`(`PcId`, `PcStatus`) VALUES (%d,'Usable')", Integer.parseInt(id));
		
		db.execute(query);
	}
	
	public void updatePC(String status, String id) {
		String query = String.format("UPDATE pc SET PcStatus = '%s' WHERE PcId = %d", status, Integer.parseInt(id));
		
		db.execute(query);
	}
	
	public ResultSet checkBookedPC(String id) {
		String query = String.format("SELECT * FROM pcbook\n" + 
				"WHERE PcId = %d AND pcbook.BookedDate > NOW() ", Integer.parseInt(id));

		return db.selectData(query);
	}
	
	public void deletePc(String id) {
		String query1 = String.format("DELETE FROM `report` WHERE `report`.`PcId` = %d", Integer.parseInt(id));
		String query2 = String.format("DELETE FROM `job` WHERE `job`.`PcId` = %d", Integer.parseInt(id));
		String query3 = String.format("DELETE FROM `pcbook` WHERE `pcbook`.`PcId` = %d", Integer.parseInt(id));
		String query4 = String.format("DELETE FROM `pc` WHERE `pc`.`PcId` = %d", Integer.parseInt(id));
				
		db.execute(query1);
		db.execute(query2);
		db.execute(query3);
		db.execute(query4);
	}
}
