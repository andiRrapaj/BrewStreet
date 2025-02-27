package admin;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.Blob;

import Maini.maini;

import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class adminusers{
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    private JLabel imageLabel;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTable table;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_8;
    private JTextField txtSearch;
    private File selectedFile;
    private JButton btnNewButton_3;
    private JButton btnNewButton;
    
    public adminusers(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(192, 192, 192)); 
        
        JLabel lblNewLabel = new JLabel("Manage Users");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel.setBounds(32, 21, 161, 35);
        panel.add(lblNewLabel);
        
        String[] columnNames = {"Name", "Phone Number", "Address", "Email","Image" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model); 

        JButton uploadButton = new JButton("Upload Image");
        uploadButton.setBounds(714, 326, 107, 30);
        
        panel.add(uploadButton);
        uploadButton.addActionListener(e -> chooseImage());

 
        uploadButton.addActionListener(e -> {
        
        	JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
            int result = fileChooser.showOpenDialog(panel);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                
                ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                JLabel imageLabel = new JLabel(imageIcon);
                imageLabel.setBounds(170, 520, 100, 100); // You can adjust the size if needed
                panel.add(imageLabel);

                panel.revalidate();
                panel.repaint();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(32, 174, 521, 522);
        panel.add(scrollPane);
     // Call this method to load the data from the database into the table
        loadTableData(table);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get values from the selected row
                    String name = (String) table.getValueAt(selectedRow, 0);  // Assuming name is in column 0
                    String phone = (String) table.getValueAt(selectedRow, 1); // Assuming phone is in column 1
                    String address = (String) table.getValueAt(selectedRow, 2); // Assuming address is in column 2
                    String email = (String) table.getValueAt(selectedRow, 3);  // Assuming email is in column 3

                    // Set these values in your text fields (if needed)
                    textField_4.setText(name);
                    textField_8.setText(phone);
                    textField_6.setText(address);
                    textField_5.setText(email);
                }
            }
        });
        
        textField_4 = new JTextField();
        textField_4.setBounds(578, 262, 117, 26);
        panel.add(textField_4);
        textField_4.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setBounds(578, 329, 117, 26);
        panel.add(textField_5);
        textField_5.setColumns(10);
        
        textField_6 = new JTextField();
        textField_6.setBounds(714, 410, 117, 26);
        panel.add(textField_6);
        textField_6.setColumns(10);
        
        textField_8 = new JTextField();
        textField_8.setBounds(578, 410, 117, 26);
        panel.add(textField_8);
        textField_8.setColumns(10);
        
        JButton btnNewButton = new JButton("Save");
        btnNewButton.setBounds(697, 554, 92, 30);
        panel.add(btnNewButton);
        
        JButton btnNewButton1 = new JButton("Add+");
        btnNewButton1.setBounds(578, 489, 85, 31);
        btnNewButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	insertusers();
            }
        });
        panel.add(btnNewButton1);
        JButton btnNewButton_2 = new JButton("Delete");
        btnNewButton_2.setBounds(697, 489, 92, 30);
        panel.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("Edit");
        btnNewButton_3.setBounds(578, 554, 92, 30);
        panel.add(btnNewButton_3);
        
        JLabel lblNewLabel_1 = new JLabel("Name\r\n");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(578, 226, 101, 26);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Email");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(578, 298, 102, 21);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Phone-Number");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(578, 379, 102, 21);
        panel.add(lblNewLabel_3);
        
        JLabel lblNewLabel_5 = new JLabel("Address");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_5.setBounds(714, 379, 92, 21);
        panel.add(lblNewLabel_5);
        
        txtSearch = new JTextField();
        txtSearch.setText("Search...");
        txtSearch.setBounds(32, 102, 125, 30);
        panel.add(txtSearch);
        txtSearch.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Kerko");
        lblNewLabel_4.setBounds(32, 74, 82, 26);
        panel.add(lblNewLabel_4);
       
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(panel, "Please select a row to save.");
                    return;
                }

                // Get edited values
                String name = textField_4.getText();
                String phone = textField_8.getText();
                String address = textField_6.getText();
                String email = textField_5.getText();

                // Update JTable
                table.setValueAt(name, selectedRow, 0);
                table.setValueAt(phone, selectedRow, 1);
                table.setValueAt(address, selectedRow, 2);
                table.setValueAt(email, selectedRow, 3);

                // Update database
                updateDatabase(name, phone, address, email);
            }
        });
        
        btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(panel, "Please select a row to edit.");
                    return;
                }

                // Populate text fields with selected row data
                textField_4.setText(table.getValueAt(selectedRow, 0).toString());
                textField_8.setText(table.getValueAt(selectedRow, 1).toString());
                textField_6.setText(table.getValueAt(selectedRow, 2).toString());
                textField_5.setText(table.getValueAt(selectedRow, 3).toString());
            }
        });

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

    
    
    private void insertusers() {
        String name = textField_4.getText();
        String phone = textField_8.getText();
        String address = textField_6.getText();
        String email = textField_5.getText();
        
        if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || email.isEmpty() || selectedFile == null) {
            JOptionPane.showMessageDialog(null, "Please fill all fields and select an image.");
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
            String sql = "INSERT INTO users (name, phone, address, email, image) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, phone);
            pst.setString(3, address); // Corrected here
            pst.setString(4, email);

            FileInputStream fis = new FileInputStream(selectedFile);
            pst.setBinaryStream(5, fis, (int) selectedFile.length());

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "User added successfully!");
                loadTableData(table);  // Refresh the table after inserting data
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting user.");
        }
    }

    
    
    private void deleteFromDatabase(String name) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root");
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE name = ?")) {
            
            stmt.setString(1, name);
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(panel, "Product deleted successfully from database!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Error deleting product: " + ex.getMessage());
        }
    }
    private void loadTableData(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
            String sql = "SELECT name, phone, address, email, image FROM users";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            model.setRowCount(0);  // Clear existing rows

            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String email = rs.getString("email");
                
                Blob imageBlob = (Blob) rs.getBlob("image");
                byte[] imageBytes = imageBlob != null ? imageBlob.getBytes(1, (int) imageBlob.length()) : null;

                model.addRow(new Object[]{name, phone, address, email, imageBytes});  // Add the row with the correct order
            }

            // Set the custom image renderer
            setImageColumnRenderer(table);

            // Resize the row heights based on the image
            for (int i = 0; i < table.getRowCount(); i++) {
                byte[] imageBytes = (byte[]) table.getValueAt(i, 4);  // Image is in the 5th column (index 4)
                if (imageBytes != null) {
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image img = imageIcon.getImage();
                    Image resizedImg = img.getScaledInstance(150, 200, Image.SCALE_SMOOTH);  
                    int rowHeight = resizedImg.getHeight(null) + 100;  // Adjust row height
                    table.setRowHeight(i, rowHeight);  
                    int columnWidth = resizedImg.getWidth(null) + 90;  // Add padding to column width
                    table.getColumnModel().getColumn(4).setPreferredWidth(columnWidth); 
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error loading data.");
        }
    }



    private void setImageColumnRenderer(JTable table) {
        table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
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
    private void updateDatabase(String name, String phone, String address, String email) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
            // SQL query to update based on the name
            String sql = "UPDATE users SET phone = ?, address = ?, email = ? WHERE name = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            // Remove leading and trailing spaces from the name (if any)
            name = name.trim();

            // Set the parameters
            pst.setString(1, phone);   // Updating phone
            pst.setString(2, address); // Updating address
            pst.setString(3, email);   // Updating email
            pst.setString(4, name);    // Using the trimmed name to find the user

            // Execute the update
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "User updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "User not found or no changes made.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error.");
        }
    }



    
    
    
    public JPanel getPanel() {
        return panel;
    }
}