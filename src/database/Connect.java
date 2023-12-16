package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	// data localhost dan nama database yang digunakan 
	private final String username = "root";
	private final String password = "";
	private final String database = "msclafes";
	private final String host = "localhost:3306";
	private final String connection = String.format("jdbc:mysql://%s/%s", host, database);
	
	private Connection con;
	private Statement st;
	
	public static Connect instance;
	public ResultSet rs;
	
	// database singleton agar hanya perlu satu objek ketika mengakses database
	public static synchronized Connect getInstance() {
		
		if(instance == null) {
			return new Connect();
		}
		
		return instance;
	}
	
	// method untuk membuat connection jdbc ke databasenya
	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(connection, username, password);
			st = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// method untuk mereturn query data yang ingin di read
	public ResultSet selectData(String query) {
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	// method untuk meng-execute query data seperi insert, update, delete
	public void execute(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

