package model;

import java.sql.ResultSet;

import database.Connect;

public class PcModel {

	Connect db = Connect.getInstance();
	
	// method execute query untuk mengambil semua data PC yang terdaftar di database
	public ResultSet viewAllPc() {
		
		String query = "SELECT * FROM `pc`";
		
		return db.selectData(query);
	}
	
	// method execute query untuk menambahkan data PC baru ke database
	public void addNewPC(String id) {
		String query = String.format("INSERT INTO `pc`(`PcId`, `PcStatus`) VALUES (%d,'Usable')", Integer.parseInt(id));
		
		db.execute(query);
	}
	
	// method execute query untuk mengupdate data PC Status berdasarkan IDnya
	public void updatePC(String status, String id) {
		String query = String.format("UPDATE pc SET PcStatus = '%s' WHERE PcId = %d", status, Integer.parseInt(id));
		
		db.execute(query);
	}
	
	// method execute query untuk mengambil semua data booking berdasarkan ID pc dan tanggal booking di masa mendatang
	public ResultSet checkBookedPC(String id) {
		String query = String.format("SELECT * FROM pcbook\n" + 
				"WHERE PcId = %d AND pcbook.BookedDate > NOW() ", Integer.parseInt(id));

		return db.selectData(query);
	}
	
	// method execute query untuk menghapus data report, job, pcbook yang sudah lewat hari ini, dan pc berdasarkan IDNya
	// query ini pada dasarnya menggunakan ON CASCADE dengan asumsi semua data report, job, dan pcbook yang sudah lewat akan hilang akan dihapus
	// DISCLAIMER : pcID di TrDetail tidak menggunakan FK sehingga tidak akan terhapus
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
