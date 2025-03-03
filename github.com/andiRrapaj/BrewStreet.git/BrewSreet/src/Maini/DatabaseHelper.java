package Maini;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.*;
import javax.imageio.ImageIO;

public class DatabaseHelper {
    
    // Method to fetch table data dynamically
	public static DefaultTableModel getTableData(String tableName) {
	    DefaultTableModel model = null;
	    try {
	        Connection conn = DbConn.getConnection();
	        String query = "SELECT * FROM " + tableName;
	        PreparedStatement stmt = conn.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();

	        // Define column names
	        String[] columnNames = {"ID", "Username", "Phone", "Password", "Role", "Profile Picture"};
	        model = new DefaultTableModel(columnNames, 0);

	        while (rs.next()) {
	            Object[] row = new Object[6];
	            row[0] = rs.getInt("id");
	            row[1] = rs.getString("username");
	            row[2] = rs.getString("phone");
	            row[3] = rs.getString("password");
	            row[4] = rs.getString("role");
	            row[5] = rs.getBlob("profile_picture");  // Store image as Blob
	            model.addRow(row);
	        }

	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return model;
	}

}
