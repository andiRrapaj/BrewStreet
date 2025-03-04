package admin;



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

public class adminusers{
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
   
    private JTable usersTable;
    private JTextField usernameField;
    private JTextField passwordField;
    private JComboBox<String> roleComboBox;
    private JTextField nrField;
    private JTextField searchField;
    private File selectedFile;
    
    private JTextField idField;

    
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
        
        usersTable = new JTable();
        usersTable.setModel(DatabaseHelper.getTableData("users")); 
        usersTable.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer()); 

        
        usersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow != -1) { 
                    idField.setText(usersTable.getValueAt(selectedRow, 0).toString()); 
                    usernameField.setText(usersTable.getValueAt(selectedRow, 1).toString()); 
                    nrField.setText(usersTable.getValueAt(selectedRow, 2).toString()); 
                    passwordField.setText(usersTable.getValueAt(selectedRow, 3).toString()); 
                    
                    String role = usersTable.getValueAt(selectedRow, 4).toString();
                    roleComboBox.setSelectedItem(role);

                   
                }
            }
        });

        JButton uploadImage = new JButton("Upload Image");
        uploadImage.setBounds(714, 197, 107, 30);
        
        panel.add(uploadImage);
        

 
        uploadImage.addActionListener(e -> {
        
        	JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
            int result = fileChooser.showOpenDialog(panel);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                
                ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                JLabel imageLabel = new JLabel(imageIcon);
                imageLabel.setBounds(170, 520, 100, 100); 
                panel.add(imageLabel);

                panel.revalidate();
                panel.repaint();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(usersTable);
        scrollPane.setBounds(32, 174, 521, 522);
        panel.add(scrollPane);
   
                
        usernameField = new JTextField();
        usernameField.setBounds(578, 262, 117, 26);
        panel.add(usernameField);
        usernameField.setColumns(10);
        
        passwordField = new JTextField();
        passwordField.setBounds(578, 329, 117, 26);
        panel.add(passwordField);
        passwordField.setColumns(10);
        
        roleComboBox = new JComboBox<>(new String[]{"receptionist", "admin", "waiter"});
        roleComboBox.setBounds(578, 483, 117, 26);
        panel.add(roleComboBox);
        
        idField = new JTextField();
        idField.setBounds(578, 200, 92, 26);
        idField.setEditable(false); 
        panel.add(idField);

        
        nrField = new JTextField();
        nrField.setBounds(578, 410, 117, 26);
        panel.add(nrField);
        nrField.setColumns(10);
        
        JButton addBtn = new JButton("Add+");
        addBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String phone = nrField.getText();
            String password = passwordField.getText();
            String role = (String) roleComboBox.getSelectedItem(); 
            byte[] profilePicture = null;

            if (selectedFile != null) {
                try {
                    FileInputStream fis = new FileInputStream(selectedFile);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }
                    profilePicture = baos.toByteArray();
                    fis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            try {
                Connection conn = DbConn.getConnection();
                String query = "INSERT INTO users (username, phone, password, role, profile_picture) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, phone);
                stmt.setString(3, password);
                stmt.setString(4, role);
                if (profilePicture != null) {
                    stmt.setBytes(5, profilePicture);
                } else {
                    stmt.setNull(5, Types.BLOB);
                }

                stmt.executeUpdate();
                stmt.close();
                conn.close();
                
                
                usersTable.setModel(DatabaseHelper.getTableData("users"));
                usersTable.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());

                JOptionPane.showMessageDialog(panel, "User added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error adding user!");
            }
        });


        addBtn.setBounds(563, 577, 85, 31);
        
        panel.add(addBtn);
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> {
            String userId = idField.getText(); 
            if (userId.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please select a user to delete!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete this user?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {


                    Connection conn = DbConn.getConnection();
                    String query = "DELETE FROM users WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, Integer.parseInt(userId)); 

                    stmt.executeUpdate();
                    stmt.close();
                    conn.close();

                    
                    usersTable.setModel(DatabaseHelper.getTableData("users"));
                    usersTable.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());

                    JOptionPane.showMessageDialog(panel, "User deleted successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error deleting user!");
                }
            }
        });

        deleteBtn.setBounds(714, 577, 92, 30);
        panel.add(deleteBtn);
        
        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> {
            String userId = idField.getText(); 
            String username = usernameField.getText();
            String phone = nrField.getText();
            String password = passwordField.getText();
            String role = (String) roleComboBox.getSelectedItem();  
            byte[] profilePicture = null;

             
            if (selectedFile != null) {
                try {
                    FileInputStream fis = new FileInputStream(selectedFile);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }
                    profilePicture = baos.toByteArray();
                    fis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            try {
                
                Connection conn = DbConn.getConnection();
                String query = "UPDATE users SET username = ?, phone = ?, password = ?, role = ?, profile_picture = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, phone);
                stmt.setString(3, password);
                stmt.setString(4, role);
                if (profilePicture != null) {
                    stmt.setBytes(5, profilePicture); 
                } else {
                    stmt.setNull(5, Types.BLOB); 
                }
                stmt.setInt(6, Integer.parseInt(userId)); 

                stmt.executeUpdate();
                stmt.close();
                conn.close();

               
                usersTable.setModel(DatabaseHelper.getTableData("users"));
                usersTable.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());

                JOptionPane.showMessageDialog(panel, "User updated successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error updating user!");
            }
        });

        updateBtn.setBounds(563, 641, 92, 30);
        panel.add(updateBtn);
        
        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(578, 236, 101, 26);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(577, 309, 102, 21);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Phone-Number");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(578, 386, 102, 21);
        panel.add(lblNewLabel_3);
        
        JLabel lblNewLabel_5 = new JLabel("Role");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_5.setBounds(578, 459, 92, 21);
        panel.add(lblNewLabel_5);
        
        searchField = new JTextField();
     
        searchField.setBounds(32, 101, 161, 30);
        panel.add(searchField);
        searchField.setColumns(10);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText().trim();

                
                if (searchText.length() >= 3) {
                    filterTable(searchText); 
                } else {
                    
                    usersTable.setModel(DatabaseHelper.getTableData("users"));
                    usersTable.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());
                }
            }
        });


        
        JLabel lblNewLabel_4 = new JLabel("Kerko");
        lblNewLabel_4.setBounds(32, 74, 82, 26);
        panel.add(lblNewLabel_4);
        
        

    }
    
    private void refreshTable() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        DefaultTableModel model = dbHelper.getTableData("users");
        usersTable.setModel(model);
    }

    private void filterTable(String searchText) {
        
        String query = "SELECT * FROM users WHERE username LIKE ?";

        try {
            Connection conn = DbConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            String searchPattern = "%" + searchText + "%";  

            stmt.setString(1, searchPattern);

            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Username", "Phone", "Password", "Role", "Profile Picture"}, 0);

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("username");
                row[2] = rs.getString("phone");
                row[3] = rs.getString("password");
                row[4] = rs.getString("role");
                row[5] = rs.getBlob("profile_picture");  // Assuming you store image as a BLOB

                model.addRow(row);
            }

            usersTable.setModel(model);
            usersTable.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error filtering table!");
        }
    }

    
    public JPanel getPanel() {
        return panel;
    }
}