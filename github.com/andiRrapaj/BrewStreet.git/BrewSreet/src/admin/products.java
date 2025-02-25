	package admin;
	
	import javax.swing.*;
	import javax.swing.filechooser.FileNameExtensionFilter;
	import javax.swing.table.DefaultTableCellRenderer;
	import javax.swing.table.DefaultTableModel;
	

	
	import java.awt.*;
	import java.awt.event.*;
	import java.io.File;
	import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
	public class products{
	    private JPanel panel;
	    private JPanel container;
	    private CardLayout cards;
	    private JTextField textField;
	    private JTextField textField_1;
	    private JTable table;
	    private JTextField textField_2;
	    private JTextField textField_3;
	    private JTextField textField_4;
	    private JTextField textField_5;
	    private JTextField textField_6;
	    private JTextField textField_7;
	    private JTextField txtSearch;
	    private JTextField textField_8;
	    private File selectedFile;
	    private DefaultTableModel model;
	    
	    public products(JPanel container, CardLayout cards) {
	        this.container = container;
	        this.cards = cards;
	        
	        panel = new JPanel();
	        panel.setLayout(null);
	        panel.setBounds(0, 0, 1000, 800); 
	        panel.setBackground(new Color(192, 192, 192)); 
	        
	        
	        String[] columnNames = {"Name", "Quantity", "Price", "Category", "Image"};
	        model = new DefaultTableModel(columnNames, 0);
	        JTable table = new JTable(model);
	
	        
	        loadTableData(table);
	        setImageColumnRenderer(table);
	
	       
	        JButton uploadButton = new JButton("Upload Image");
	        uploadButton.setBounds(574, 334, 107, 30);
	        panel.add(uploadButton);
	        uploadButton.addActionListener(e -> chooseImage());
	    
	  
	        
	   
	        
	        
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(10, 148, 543, 555);
	        panel.add(scrollPane);
	        
	        JLabel lblNewLabel = new JLabel("Products");
	        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
	        lblNewLabel.setBounds(24, 20, 159, 30);
	        panel.add(lblNewLabel);
	        
	        textField_4 = new JTextField();
	        textField_4.setBounds(707, 335, 107, 30);
	        panel.add(textField_4);
	        textField_4.setColumns(10);
	        
	        textField_5 = new JTextField();
	        textField_5.setBounds(707, 421, 107, 30);
	        panel.add(textField_5);
	        textField_5.setColumns(10);
	        
	        textField_6 = new JTextField();
	        textField_6.setBounds(574, 180, 107, 30);
	        panel.add(textField_6);
	        textField_6.setColumns(10);
	        
	        textField_7 = new JTextField();
	        textField_7.setBounds(574, 421, 107, 30);
	        panel.add(textField_7);
	        textField_7.setColumns(10);
	        
	        JLabel lblNewLabel_1 = new JLabel("Name\r\n");
	        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_1.setBounds(574, 148, 107, 22);
	        panel.add(lblNewLabel_1);
	        
	        JLabel lblNewLabel_2 = new JLabel("Quantity");
	        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_2.setBounds(707, 389, 107, 22);
	        panel.add(lblNewLabel_2);
	        
	        JLabel lblNewLabel_3 = new JLabel("Price");
	        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_3.setBounds(707, 303, 99, 22);
	        panel.add(lblNewLabel_3);
	        
	        JLabel lblNewLabel_4 = new JLabel("Category\r\n");
	        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_4.setBounds(574, 389, 107, 22);
	        panel.add(lblNewLabel_4);
	        
	        JButton btnNewButton = new JButton("Edit");
	        btnNewButton.setBounds(582, 515, 99, 30);
	        btnNewButton.addActionListener(e -> {
	            int selectedRow = table.getSelectedRow(); 
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(panel, "Please select a row to edit.");
	                return;
	            }
	
	            // Get the data from the selected row
	            String name = (String) table.getValueAt(selectedRow, 0);
	            String quantity = (String) table.getValueAt(selectedRow, 1);
	            String price = (String) table.getValueAt(selectedRow, 2);
	            String category = (String) table.getValueAt(selectedRow, 3);
	
	            // Set the text fields with the selected row's data
	            textField_6.setText(name);
	            textField_5.setText(quantity);
	            textField_4.setText(price);
	            textField_7.setText(category);
	        });
	
	        panel.add(btnNewButton);
	        
	        JButton btnNewButton_1 = new JButton("Save");
	        btnNewButton_1.setBounds(582, 577, 99, 30);
	        btnNewButton_1.addActionListener(e -> updateProduct());
	
	        panel.add(btnNewButton_1);
	        
	        JButton btnNewButton_2 = new JButton("Add+");
	        btnNewButton_2.setBounds(715, 515, 99, 30);
	        btnNewButton_2.addActionListener(e -> insertProduct());
	        panel.add(btnNewButton_2);
	        
	        JButton btnNewButton_3 = new JButton("Delete");
	        btnNewButton_3.setBounds(715, 577, 99, 30);
	        panel.add(btnNewButton_3);
	        btnNewButton_3.addActionListener(e -> {
	            int selectedRow = table.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(panel, "Please select a row to delete.");
	                return;
	            }
	
	            String name = table.getValueAt(selectedRow, 0).toString();
	
	            ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
	            deleteFromDatabase(name);
	            loadTableData(table); // Refresh the table after deletion
	        });
	
	
	
	        
	        JLabel lblNewLabel_5 = new JLabel("Filter");
	        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_5.setBounds(205, 70, 107, 20);
	        panel.add(lblNewLabel_5);
	        
	        JComboBox comboBox = new JComboBox(new String[]{"Category ", "Item 2", "Item 3", "Item 4"});
	        comboBox.setBounds(205, 95, 138, 31); 
	        panel.add(comboBox);
	        
	        txtSearch = new JTextField();
	        txtSearch.setText("Search....");
	        txtSearch.setBounds(24, 95, 131, 31);
	        panel.add(txtSearch);
	        txtSearch.setColumns(10);
	        
	        JLabel lblNewLabel_6 = new JLabel("Search?");
	        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_6.setBounds(24, 69, 99, 22);
	        panel.add(lblNewLabel_6);
	        
	        JLabel lblNewLabel_7 = new JLabel("Photo");
	        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_7.setBounds(574, 303, 93, 21);
	        panel.add(lblNewLabel_7);
	        
	        textField_8 = new JTextField();
	        textField_8.setBounds(691, 180, 123, 113);
	        panel.add(textField_8);
	        textField_8.setColumns(10);
	        
	        JLabel lblNewLabel_8 = new JLabel("Description");
	        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_8.setBounds(691, 149, 85, 20);
	        panel.add(lblNewLabel_8);
	 }
	    
	    
	    
	    
	    
	    
	    
	    private void chooseImage() {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));
	        int result = fileChooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            selectedFile = fileChooser.getSelectedFile();
	            JOptionPane.showMessageDialog(null, "Image Selected: " + selectedFile.getName());
	        }
	    }
	
	    
	    private void insertProduct() {
	        String name = textField_6.getText();  // Get from textField
	        String price = textField_4.getText(); // Get from textField
	        String category = textField_7.getText();  // Get from textField
	        String quantity = textField_5.getText();  // Get from textField

	        if (name.isEmpty() || price.isEmpty() || category.isEmpty() || quantity.isEmpty() || selectedFile == null) {
	            JOptionPane.showMessageDialog(null, "Please fill all fields and select an image.");
	            return;
	        }

	        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
	            String sql = "INSERT INTO products (name, price, category, quantity, image) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement pst = con.prepareStatement(sql);
	            pst.setString(1, name);
	            pst.setString(2, price);
	            pst.setString(3, category);
	            pst.setString(4, quantity);

	            // Check if file is selected before reading
	            if (selectedFile != null) {
	                try (FileInputStream fis = new FileInputStream(selectedFile)) {
	                    pst.setBinaryStream(5, fis, (int) selectedFile.length());
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "No image selected.");
	                return;
	            }

	            int rowsInserted = pst.executeUpdate();
	            if (rowsInserted > 0) {
	                JOptionPane.showMessageDialog(null, "Product added successfully!");
	                loadTableData(table); // Refresh the table after insertion
	            }
	            pst.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error inserting product: " + ex.getMessage());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage());
	        }
	    }

	    private void updateProduct() {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(panel, "Please select a row to edit.");
	            return;
	        }

	        String name = textField_6.getText();
	        String price = textField_4.getText();
	        String category = textField_7.getText();
	        String quantity = textField_5.getText();

	        if (name.isEmpty() || price.isEmpty() || category.isEmpty() || quantity.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Please fill all fields.");
	            return;
	        }

	        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
	            String sql = "UPDATE products SET price = ?, category = ?, quantity = ? WHERE name = ?";
	            PreparedStatement pst = con.prepareStatement(sql);
	            pst.setString(1, price);
	            pst.setString(2, category);
	            pst.setString(3, quantity);
	            pst.setString(4, name);

	            int rowsUpdated = pst.executeUpdate();
	            if (rowsUpdated > 0) {
	                model.setValueAt(price, selectedRow, 2);
	                model.setValueAt(quantity, selectedRow, 1);
	                model.setValueAt(category, selectedRow, 3);
	                JOptionPane.showMessageDialog(null, "Product updated successfully!");
	                loadTableData(table); // Refresh the table after updating
	            }
	            pst.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error updating product.");
	        }
	    }

	    private void deleteFromDatabase(String name) {
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root");
	             PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE name = ?")) {

	            stmt.setString(1, name);
	            stmt.executeUpdate();
	            JOptionPane.showMessageDialog(panel, "Product deleted successfully from database!");
	            loadTableData(table); // Refresh the table after deletion
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(panel, "Error deleting product: " + ex.getMessage());
	        }
	    }

	    private void loadTableData(JTable table) {
	        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
	            String sql = "SELECT name, price, quantity, category, image FROM products";
	            PreparedStatement pst = con.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();

	            model.setRowCount(0); // Clear previous data

	            while (rs.next()) {
	                String name = rs.getString("name");
	                String price = rs.getString("price");
	                String quantity = rs.getString("quantity");
	                String category = rs.getString("category");
	                Blob imageBlob = (Blob) rs.getBlob("image");
	                byte[] imageBytes = imageBlob != null ? imageBlob.getBytes(1, (int) imageBlob.length()) : null;

	                model.addRow(new Object[]{name, quantity, price, category, imageBytes});
	            }

	            // Resize rows to fit image size
	            for (int i = 0; i < table.getRowCount(); i++) {
	                byte[] imageBytes = (byte[]) table.getValueAt(i, 4);
	                if (imageBytes != null) {
	                    ImageIcon imageIcon = new ImageIcon(imageBytes);
	                    Image img = imageIcon.getImage();
	                    Image resizedImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	                    int rowHeight = resizedImg.getHeight(null) + 100;
	                    table.setRowHeight(i, rowHeight);
	                }
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(panel, "Error loading data: " + ex.getMessage());
	        }
	    }

	    private void setImageColumnRenderer(JTable table) {
	        table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                if (value instanceof byte[]) {
	                    byte[] imageBytes = (byte[]) value;
	                    ImageIcon imageIcon = new ImageIcon(imageBytes);
	                    Image image = imageIcon.getImage();
	                    Image resizedImg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	                    JLabel label = new JLabel(new ImageIcon(resizedImg));
	                    return label;
	                }
	                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            }
	        });
	    }

	    
	
	
	    public JPanel getPanel() {
	        return panel;
	    }
	}
