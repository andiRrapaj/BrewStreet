package admin;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.sql.Blob;

import Maini.maini;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;

public class inventory{
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    
    private JTextField textField_2;
    private JTextField textField_3;
   
    private JTable table;
    private JTextField txtSearch;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_4;
    private JLabel imageLabel;
    private DefaultTableModel model;
    private JTextField textFieldName, textFieldPrice, textFieldQuantity;
    private JCheckBox chckbxActive, chckbxNotActive;
    private JComboBox<String> comboBox1;
    private File selectedFile;
    private JButton btnNewButton_3;
    private JButton btnNewButton_1;
    
    public inventory(JPanel layeredPane, CardLayout cards) {
        this.container = layeredPane;
        this.cards = cards;
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(128, 128, 128)); 
        
        JLabel lblNewLabel = new JLabel("Inventory");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNewLabel.setBounds(25, 10, 125, 40);
        panel.add(lblNewLabel);
        
 String[] columnNames = {"Name", "1", "2", "3", "4"};

        

 String[] columnNames1 = {"Name", "Price", "Quantity", "State", "Category", "Image"};
 model = new DefaultTableModel(columnNames1, 0);
 table = new JTable(model);
 JScrollPane scrollPane = new JScrollPane(table);
 scrollPane.setBounds(10, 156, 545, 549);
 panel.add(scrollPane);
 loadTableData(table);  // Add this after table initialization
 table.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
	            // Get values from the selected row
	            String name = (String) table.getValueAt(selectedRow, 0);
	            String price = (String) table.getValueAt(selectedRow, 1);
	            String quantity = (String) table.getValueAt(selectedRow, 2);
	            String state = (String) table.getValueAt(selectedRow, 4); // Assuming 'state' is in column 4

	            // Set these values in your text fields
	            textFieldName.setText(name);
	            textFieldPrice.setText(price);
	            textFieldQuantity.setText(quantity);

	            // Check box based on state
	            if (state.equals("Active")) {
	                chckbxActive.setSelected(true);
	                chckbxNotActive.setSelected(false);
	            } else {
	                chckbxNotActive.setSelected(true);
	                chckbxActive.setSelected(false);
	            }
	        }
	    }
	});
 

        txtSearch = new JTextField();
        txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtSearch.setText("Search....");
        txtSearch.setBounds(25, 78, 148, 31);
        panel.add(txtSearch);
        txtSearch.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setBounds(575, 260, 92, 23);
        panel.add(textField_5);
        textField_5.setColumns(10);
        
        textField_6 = new JTextField();
        textField_6.setBounds(691, 260, 103, 23);
        panel.add(textField_6);
        textField_6.setColumns(10);
      
            
        


        
        JComboBox comboBox = new JComboBox(new String[]{"Category ", "Item 2", "Item 3", "Item 4"});
        comboBox.setBounds(213, 79, 138, 31); // Adjusted size for better visibility
        panel.add(comboBox);
        
        JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setBounds(575, 232, 71, 23);
        panel.add(lblNewLabel_1);
        
        textField_4 = new JTextField();
        textField_4.setBounds(575, 347, 92, 23);
        panel.add(textField_4);
        textField_4.setColumns(10);
        
        JButton uploadButton = new JButton("Upload Image");
        uploadButton.setBounds(687, 343, 107, 30);
        panel.add(uploadButton);
        uploadButton.addActionListener(e -> chooseImage());

        
        JLabel lblNewLabel_2 = new JLabel("Price");
        lblNewLabel_2.setBounds(689, 232, 47, 23);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Quantity");
        lblNewLabel_3.setBounds(575, 314, 71, 23);
        panel.add(lblNewLabel_3);
        
        JLabel lblState = new JLabel("State");
        lblState.setBounds(575, 387, 71, 23);
        panel.add(lblState);
        chckbxActive = new JCheckBox("Active");
        chckbxActive.setBounds(575, 416, 79, 31);
        panel.add(chckbxActive);
        chckbxNotActive = new JCheckBox("Not Active");
        chckbxNotActive.setBounds(575, 464, 79, 31);
        panel.add(chckbxNotActive);
        
        
        comboBox1 = new JComboBox(new String[]{"Category ", "Item 2", "Item 3", "Item 4"});
        comboBox1.setBounds(691, 414, 103, 31); // Adjusted size for better visibility
        panel.add(comboBox1);

        
        JButton btnNewButton = new JButton("Add+");
        btnNewButton.setBounds(575, 568, 85, 31);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	insertProduct();
            }
        });

        panel.add(btnNewButton);
        
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(702, 626, 85, 31);
        btnSave.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a row to save.");
                return;
            }

            // Get the new values from the input fields
            String name = textFieldName.getText();
            String price = textFieldPrice.getText();
            String quantity = textFieldQuantity.getText();
            String category = (String) comboBox1.getSelectedItem();
            String state = chckbxActive.isSelected() ? "Active" : "Not Active";

            // Update the table model
            table.setValueAt(name, selectedRow, 0);
            table.setValueAt(price, selectedRow, 1);
            table.setValueAt(quantity, selectedRow, 2);
            table.setValueAt(state, selectedRow, 4); // Assuming state is in column 4
            table.setValueAt(category, selectedRow, 3); // Assuming category is in column 3

            // Update the database
            updateDatabase(name, price, quantity, category, state);
        });

        panel.add(btnSave);
        
        JButton btnNewButton_2 = new JButton("Delete");
        btnNewButton_2.setBounds(702, 568, 85, 31);

        btnNewButton_2.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a row to delete.");
                return;
            }

            String name = table.getValueAt(selectedRow, 0).toString();

            ((DefaultTableModel) table.getModel()).removeRow(selectedRow);

            deleteFromDatabase(name);
        });
        panel.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("Edit");
        btnNewButton_3.setBounds(575, 626, 85, 31);
        btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(panel, "Please select a row to edit.");
                    return;
                }

                // Get the updated values from the input fields
                String name = textFieldName.getText();
                String price = textFieldPrice.getText();
                String quantity = textFieldQuantity.getText();
                String category = (String) comboBox1.getSelectedItem();
                String state = chckbxActive.isSelected() ? "Active" : "Not Active";

                // Update the selected row in the table model
                table.setValueAt(name, selectedRow, 0);       // Update Name
                table.setValueAt(price, selectedRow, 1);      // Update Price
                table.setValueAt(quantity, selectedRow, 2);   // Update Quantity
                table.setValueAt(state, selectedRow, 4);      // Update State
                table.setValueAt(category, selectedRow, 3);   // Update Category

                // Update the database with new values
                updateDatabase(name, price, quantity, category, state);
            }
        });

        panel.add(btnNewButton_3);
        
        JButton btnNewButton_4 = new JButton("Print");
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_4.setBounds(689, 81, 113, 23);
        panel.add(btnNewButton_4);
        
        
 }

    
    private void loadTableData(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
            String sql = "SELECT name, price, quantity, category, state, image FROM inventory";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            model.setRowCount(0);  // Clear existing rows

            while (rs.next()) {
                String name = rs.getString("name");
                String price = rs.getString("price");
                String quantity = rs.getString("quantity");
                String category = rs.getString("category");
                String state = rs.getString("state");
                Blob imageBlob = (Blob) rs.getBlob("image");
                byte[] imageBytes = imageBlob != null ? imageBlob.getBytes(1, (int) imageBlob.length()) : null;

                model.addRow(new Object[]{name, price, quantity, category, state, imageBytes});  // Add the row with the correct order
            }

            // Set the custom image renderer
            setImageColumnRenderer(table);

            // Resize the row heights based on the image
            for (int i = 0; i < table.getRowCount(); i++) {
                byte[] imageBytes = (byte[]) table.getValueAt(i, 5);  // Image is in the 5th column
                if (imageBytes != null) {
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image img = imageIcon.getImage();
                    Image resizedImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);  
                    int rowHeight = resizedImg.getHeight(null) + 100;  // Adjust row height
                    table.setRowHeight(i, rowHeight);  
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error loading data.");
        }
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

    private void setImageColumnRenderer(JTable table) {
        table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof byte[]) {
                    byte[] imageBytes = (byte[]) value;
                    if (imageBytes != null) {
                        ImageIcon imageIcon = new ImageIcon(imageBytes);
                        Image img = imageIcon.getImage();
                        Image resizedImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);  // Resize image
                        return new JLabel(new ImageIcon(resizedImg));
                    }
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
    }
    
    
    
    private void insertProduct() {
        String name = textField_5.getText();
        String price = textField_6.getText();
        String category = comboBox1.getSelectedItem().toString(); 
        String quantity = textField_4.getText();
        String state = chckbxActive.isSelected() ? "Active" : "Not Active";
        
        if (name.isEmpty() || price.isEmpty() || category.isEmpty() || quantity.isEmpty() || selectedFile == null) {
            JOptionPane.showMessageDialog(null, "Please fill all fields and select an image.");
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
            String sql = "INSERT INTO inventory (name, price, category, quantity, state, image) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, price);
            pst.setString(3, category);
            pst.setString(4, quantity);
            pst.setString(5, state);
            FileInputStream fis = new FileInputStream(selectedFile);
            pst.setBinaryStream(6, fis, (int) selectedFile.length());

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Product added successfully!");
                loadTableData(table); // Refresh table data
            }
            pst.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting product.");
        }
    }

    private void updateDatabase(String name, String price, String quantity, String category, String state) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
            String sql = "UPDATE inventory SET price = ?, quantity = ?, category = ?, state = ? WHERE name = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, price);
            pst.setString(2, quantity);
            pst.setString(3, category);
            pst.setString(4, state);
            pst.setString(5, name);

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(panel, "Product updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error updating product.");
        }
    }

    private void deleteFromDatabase(String name) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root");
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM inventory WHERE name = ?")) {
            
            stmt.setString(1, name);
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(panel, "Product deleted successfully from database!");
            loadTableData(table); // Refresh table data
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Error deleting product: " + ex.getMessage());
        }
    }


        
    
    
    public JPanel getPanel() {
        return panel;
    }
}