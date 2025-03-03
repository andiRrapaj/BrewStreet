package Maini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbConn {
	public static Connection getConnection() {
		Connection conn = null;
		final String URL = "jdbc:mysql://localhost:3306/brewstreet2";
		final String USER = "root";
		final String 	PASSWORD = "root";
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			//JOptionPane.showMessageDialog(null, "Database Connected");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		getConnection();
	}
}
