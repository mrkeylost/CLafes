package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	private final String username = "root";
	private final String password = "";
	private final String database = "msclafes";
	private final String host = "localhost:3306";
	private final String connection = String.format("jdbc:mysql://%s/%s", host, database);
	
	private Connection con;
	private Statement st;
	
	public static Connect instance;
	public ResultSet rs;
	
	public static synchronized Connect getInstance() {
		
		if(instance == null) {
			return new Connect();
		}
		
		return instance;
	}
	
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
	
	public ResultSet selectData(String query) {
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void execute(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

