	package admin;
	import Maini.ImageRenderer;
	import javax.swing.*;
	import javax.swing.filechooser.FileNameExtensionFilter;
	import javax.swing.table.DefaultTableCellRenderer;
	import javax.swing.table.DefaultTableModel;

import Maini.DbConn;

import java.sql.Blob;
	
	import java.awt.*;
	//00
	import java.awt.event.*;
	import java.io.File;
	import java.io.FileInputStream;
import java.nio.file.Files;
import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
	import java.awt.*;
	public class products{
	    private JPanel panel;
	    private JPanel container;
	    private CardLayout cards;
	    private JTextField textField;
	    private JTextField textField_1;
	    private JTable productsTable;
	    private JTextField textField_2;
	    private JTextField textField_3;
	    private JTextField priceField;
	    private JTextField nameField;
	    private JTextField txtSearch;
	    private File selectedFile;
	    private DefaultTableModel model;
	    private JComboBox comboBoxAdd;
	    
	    public products(JPanel container, CardLayout cards) {
	        this.container = container;
	        this.cards = cards;
	        
	        panel = new JPanel();
	        panel.setLayout(null);
	        panel.setBounds(0, 0, 1000, 800); 
	        panel.setBackground(new Color(192, 192, 192)); 
	        
	        
	        String[] columnNames = {"Name", "Quantity", "Price", "Category", "Image"};
	        model = new DefaultTableModel(columnNames, 0);
	        JTable productsTable = new JTable(model);
	        productsTable.setRowHeight(50); 
            productsTable.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());
	        
	       
	
	       
	        JButton uploadButton = new JButton("Upload Image");
	        uploadButton.setBounds(574, 334, 107, 30);
	        panel.add(uploadButton);
	        uploadButton.addActionListener(e -> chooseImage());
	    
	  
	        
	   
	        
	        
	        JScrollPane scrollPane = new JScrollPane(productsTable);
	        scrollPane.setBounds(10, 148, 543, 555);
	        panel.add(scrollPane);
	        updateTable();
	        
	        
	        JLabel lblNewLabel = new JLabel("Products");
	        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
	        lblNewLabel.setBounds(24, 20, 159, 30);
	        panel.add(lblNewLabel);
	        
	        priceField = new JTextField();
	        priceField.setBounds(574, 252, 107, 30);
	        panel.add(priceField);
	        priceField.setColumns(10);
	        
	        nameField = new JTextField();
	        nameField.setBounds(574, 180, 107, 30);
	        panel.add(nameField);
	        nameField.setColumns(10);
	        
	        JLabel lblNewLabel_1 = new JLabel("Name");
	        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_1.setBounds(574, 148, 107, 22);
	        panel.add(lblNewLabel_1);
	        
	        JLabel lblNewLabel_3 = new JLabel("Price");
	        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_3.setBounds(574, 220, 99, 22);
	        panel.add(lblNewLabel_3);
	        
	        JLabel lblNewLabel_4 = new JLabel("Category");
	        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_4.setBounds(574, 389, 107, 22);
	        panel.add(lblNewLabel_4);
	        
	        JButton btnNewButton = new JButton("Edit");
	        btnNewButton.setBounds(582, 515, 99, 30);
	        
	
	        panel.add(btnNewButton);
	        
	        JButton btnNewButton_2 = new JButton("Add+");
	        btnNewButton_2.setBounds(715, 515, 99, 30);
	        btnNewButton_2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String name = nameField.getText().trim();
	                String price = priceField.getText().trim();
	                String category = comboBoxAdd.getSelectedItem().toString();

	                if (name.isEmpty() || price.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please enter all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
	                    return;
	                }

	                if (selectedFile == null) {
	                    JOptionPane.showMessageDialog(null, "Please select an image!", "Warning", JOptionPane.WARNING_MESSAGE);
	                    return;
	                }

	                try (Connection conn = DbConn.getConnection()) {
	                    String sql = "INSERT INTO products (name, price, category, image) VALUES (?, ?, ?, ?)";
	                    PreparedStatement stmt = conn.prepareStatement(sql);
	                    stmt.setString(1, name);
	                    stmt.setDouble(2, Double.parseDouble(price));
	                    stmt.setString(3, category);

	                    
	                    byte[] imageData = Files.readAllBytes(selectedFile.toPath());
	                    stmt.setBytes(4, imageData);

	                    int rowsInserted = stmt.executeUpdate();
	                    if (rowsInserted > 0) {
	                        JOptionPane.showMessageDialog(null, "Product added successfully!");
	                        updateTable(); 
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Failed to add product!", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });


	       
	        panel.add(btnNewButton_2);
	        
	        JButton btnNewButton_3 = new JButton("Delete");
	        btnNewButton_3.setBounds(582, 575, 99, 30);
	        panel.add(btnNewButton_3);
	       	
	
	
	        
	        JLabel lblNewLabel_5 = new JLabel("Filter");
	        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_5.setBounds(205, 70, 107, 20);
	        panel.add(lblNewLabel_5);
	        
	        JComboBox comboBoxFilter = new JComboBox(new String[]{"Category ", "Item 2", "Item 3", "Item 4"});
	        comboBoxFilter.setBounds(205, 95, 138, 31); 
	        panel.add(comboBoxFilter);
	        
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
	        
	        comboBoxAdd = new JComboBox(new Object[]{"bar", "restaurant"});
	        comboBoxAdd.setBounds(574, 426, 138, 31);
	        panel.add(comboBoxAdd);
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
	
	    
	    
	    private void updateTable() {
	        model.setRowCount(0); 
	        model.setColumnCount(0); 

	        try (Connection conn = DbConn.getConnection()) {
	            String sql = "SELECT * FROM products"; 
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            ResultSetMetaData metaData = rs.getMetaData();
	            int columnCount = metaData.getColumnCount();

	            
	            for (int i = 1; i <= columnCount; i++) {
	                model.addColumn(metaData.getColumnName(i));
	            }

	            
	            while (rs.next()) {
	                Object[] rowData = new Object[columnCount];

	                for (int i = 1; i <= columnCount; i++) {
	                    if (metaData.getColumnTypeName(i).equalsIgnoreCase("BLOB")) {
	                      
	                        Blob imageBlob = rs.getBlob(i);
	                        byte[] imageBytes = (imageBlob != null) ? imageBlob.getBytes(1, (int) imageBlob.length()) : null;
	                        if (imageBytes != null) {
	                            System.out.println("Image size: " + imageBytes.length);
	                            ImageIcon img = new ImageIcon(imageBytes);
	                            Image scaledImg = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	                            rowData[i - 1] = new ImageIcon(scaledImg); 

	                        } else {
	                            rowData[i - 1] = "No Image"; 

	                        }
	                    } else {
	                        rowData[i - 1] = rs.getObject(i); 
	                    }
	                }
	                model.addRow(rowData);
	            }

	       

	           

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error loading products!", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }


	    
	    class ImageRenderer extends DefaultTableCellRenderer {
	        @Override
	        public void setValue(Object value) {
	            if (value instanceof ImageIcon) {
	                setIcon((ImageIcon) value);
	                setText("");
	            } else {
	                setIcon(null);
	                setText(value != null ? value.toString() : "");
	            }
	        }
	    }


	    
	    public JPanel getPanel() {
	        return panel;
	    }
	}
