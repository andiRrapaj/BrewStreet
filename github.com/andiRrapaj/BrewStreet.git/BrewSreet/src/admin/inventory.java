package admin;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.sql.Blob;

import Maini.DatabaseHelper;
import Maini.DbConn;
import Maini.ImageRenderer;
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
import java.sql.Types;
import java.awt.*;

public class inventory{
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    
    private JTextField textField_2;
    private JTextField textField_3;
   
    private JTable inventoryTable;
    private JTextField txtSearch;
    private JTextField nameFiled;
    private JTextField priceField;
    private JTextField quantityFld;
    private JLabel imageLabel;
    private DefaultTableModel model;
    private JTextField textFieldName, textFieldPrice, textFieldQuantity;
    private JCheckBox chckbxActive, chckbxNotActive;
    private JComboBox<String> comboBoxCategory;
    private JComboBox<String> categoryFilter;
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
        


        

 String[] columnNames1 = {}; 
 model = new DefaultTableModel(columnNames1, 0);
 inventoryTable = new JTable(model);
 JScrollPane scrollPane = new JScrollPane(inventoryTable);
 scrollPane.setBounds(10, 156, 545, 549);
 panel.add(scrollPane); 
 
 JTextField idField = new JTextField();
 idField.setBounds(575, 200, 92, 23);
 idField.setEditable(false); 
 panel.add(idField);

 		
 
 inventoryTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        int selectedRow = inventoryTable.getSelectedRow();
	        if (selectedRow != -1) {
	            idField.setText(inventoryTable.getValueAt(selectedRow, 0).toString());
	            nameFiled.setText(inventoryTable.getValueAt(selectedRow, 1).toString());
	            priceField.setText(inventoryTable.getValueAt(selectedRow, 2).toString());
	            quantityFld.setText(inventoryTable.getValueAt(selectedRow, 3).toString());

	            String category = inventoryTable.getValueAt(selectedRow, 5).toString();
	            comboBoxCategory.setSelectedItem(category);

	            boolean state = Boolean.parseBoolean(inventoryTable.getValueAt(selectedRow, 6).toString());
	            chckbxActive.setSelected(state);
	            chckbxNotActive.setSelected(!state);
	        }
	    }
	});

 
 

        txtSearch = new JTextField();
        txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtSearch.setBounds(25, 78, 148, 31);
        panel.add(txtSearch);
        txtSearch.setColumns(10);
        
        nameFiled = new JTextField();
        nameFiled.setBounds(575, 260, 92, 23);
        panel.add(nameFiled);
        nameFiled.setColumns(10);
        
        priceField = new JTextField();
        priceField.setBounds(691, 260, 103, 23);
        panel.add(priceField);
        priceField.setColumns(10);
      
            
        

        getTableContent(); 
        
        categoryFilter = new JComboBox(new String[]{"bar","all","restaurant"});
        categoryFilter.setBounds(213, 79, 138, 31); 
        panel.add(categoryFilter);
        
        categoryFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });

        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }
        });

        
        JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setBounds(575, 232, 71, 23);
        panel.add(lblNewLabel_1);
        
        quantityFld = new JTextField();
        quantityFld.setBounds(575, 347, 92, 23);
        panel.add(quantityFld);
        quantityFld.setColumns(10);
        
        JButton uploadImg = new JButton("Upload Image");
        uploadImg.setBounds(687, 343, 107, 30);
        panel.add(uploadImg);
        uploadImg.addActionListener(e -> chooseImage());

        
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
        
        
        comboBoxCategory = new JComboBox(new String[]{"bar", "restaurant"});
        comboBoxCategory.setBounds(691, 414, 103, 31); 
        panel.add(comboBoxCategory);

        
        JButton addBtn = new JButton("Add+");
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameFiled.getText().trim();
                String quantity = quantityFld.getText().trim();
                String price = priceField.getText().trim();
                String category = (String) comboBoxCategory.getSelectedItem();
                boolean isActive = chckbxActive.isSelected();

                if (name.isEmpty() || price.isEmpty() || quantity.isEmpty() || selectedFile == null || (!chckbxActive.isSelected() && !chckbxNotActive.isSelected())) {
                    JOptionPane.showMessageDialog(panel, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (chckbxActive.isSelected() && chckbxNotActive.isSelected()) {
                    JOptionPane.showMessageDialog(panel, "Please select only one state.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                byte[] imageBytes = null;
                try (FileInputStream fis = new FileInputStream(selectedFile);
                     ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }
                    imageBytes = baos.toByteArray();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error processing image.", "Image Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection conn = DbConn.getConnection();
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory (name, price, quantity, photo, category, state) VALUES (?, ?, ?, ?, ?, ?)")) {

                    stmt.setString(1, name);
                    stmt.setInt(2, Integer.parseInt(price));
                    stmt.setInt(3, Integer.parseInt(quantity));
                    stmt.setBytes(4, imageBytes);
                    stmt.setString(5, category);
                    stmt.setBoolean(6, isActive);

                    int rowsInserted = stmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(panel, "Item added successfully!");
                        getTableContent();
                    } else {
                        JOptionPane.showMessageDialog(panel, "Failed to add item.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Database error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        chckbxActive.addItemListener(e -> {
            if (chckbxActive.isSelected()) {
                chckbxNotActive.setSelected(false);
            }
        });

        chckbxNotActive.addItemListener(e -> {
            if (chckbxNotActive.isSelected()) {
                chckbxActive.setSelected(false);
            }
        });

        
        addBtn.setBounds(575, 568, 85, 31);
        panel.add(addBtn);
        JButton delBtn = new JButton("Delete");
        delBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = inventoryTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(panel, "Please select an item to delete.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete this item?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = inventoryTable.getValueAt(selectedRow, 0).toString();

                try (Connection conn = DbConn.getConnection();
                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM inventory WHERE id=?")) {

                    stmt.setInt(1, Integer.parseInt(id));

                    int rowsDeleted = stmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(panel, "Item deleted successfully!");
                        getTableContent();
                    } else {
                        JOptionPane.showMessageDialog(panel, "Failed to delete item.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Database error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        delBtn.setBounds(702, 568, 85, 31);
        panel.add(delBtn);
        
        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = inventoryTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(panel, "Please select an item to update.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String id = inventoryTable.getValueAt(selectedRow, 0).toString();
                String name = nameFiled.getText().trim();
                String price = priceField.getText().trim();
                String quantity = quantityFld.getText().trim();
                String category = (String) comboBoxCategory.getSelectedItem();
                boolean isActive = chckbxActive.isSelected();

                if (name.isEmpty() || price.isEmpty() || quantity.isEmpty() || (!chckbxActive.isSelected() && !chckbxNotActive.isSelected())) {
                    JOptionPane.showMessageDialog(panel, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                byte[] imageBytes = null;
                if (selectedFile != null) {
                    try (FileInputStream fis = new FileInputStream(selectedFile);
                         ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            baos.write(buffer, 0, bytesRead);
                        }
                        imageBytes = baos.toByteArray();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "Error processing image.", "Image Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                try (Connection conn = DbConn.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(
                             "UPDATE inventory SET name=?, price=?, quantity=?, category=?, state=?, photo=? WHERE id=?")) {

                    stmt.setString(1, name);
                    stmt.setDouble(2, Double.parseDouble(price));
                    stmt.setInt(3, Integer.parseInt(quantity));
                    stmt.setString(4, category);
                    stmt.setBoolean(5, isActive);
                    if (imageBytes != null) {
                        stmt.setBytes(6, imageBytes);
                    } else {
                        stmt.setNull(6, Types.BLOB);
                    }
                    stmt.setInt(7, Integer.parseInt(id));

                    int rowsUpdated = stmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(panel, "Item updated successfully!");
                        getTableContent();
                    } else {
                        JOptionPane.showMessageDialog(panel, "Failed to update item.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Database error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateBtn.setBounds(575, 626, 85, 31);
        panel.add(updateBtn);
        
        JButton printBTn = new JButton("Print");
        printBTn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        printBTn.setBounds(689, 81, 113, 23);
        panel.add(printBTn);
        
        
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
    
    public void getTableContent() {
        
        try (Connection conn = DbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory");
             ResultSet rs = stmt.executeQuery()) {

            
            DefaultTableModel newModel = new DefaultTableModel();
            int columnCount = rs.getMetaData().getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                newModel.addColumn(rs.getMetaData().getColumnName(i)); 
            }

            
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i); 
                }
                newModel.addRow(rowData);
            }

            
            inventoryTable.setModel(newModel);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error loading inventory data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterTable() {
        String searchText = txtSearch.getText().trim().toLowerCase();
        String selectedCategory = (String) categoryFilter.getSelectedItem();
        
        try (Connection conn = DbConn.getConnection()) {
            String query = "SELECT * FROM inventory WHERE LOWER(name) LIKE ?";

            if (!selectedCategory.equals("all")) {
                query += " AND category = ?";
            }

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + searchText + "%");

                if (!selectedCategory.equals("all")) {
                    stmt.setString(2, selectedCategory);
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    DefaultTableModel newModel = new DefaultTableModel();
                    int columnCount = rs.getMetaData().getColumnCount();

                    for (int i = 1; i <= columnCount; i++) {
                        newModel.addColumn(rs.getMetaData().getColumnName(i));
                    }

                    while (rs.next()) {
                        Object[] rowData = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            rowData[i - 1] = rs.getObject(i);
                        }
                        newModel.addRow(rowData);
                    }

                    inventoryTable.setModel(newModel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error filtering inventory data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public JPanel getPanel() {
        return panel;
    }
}