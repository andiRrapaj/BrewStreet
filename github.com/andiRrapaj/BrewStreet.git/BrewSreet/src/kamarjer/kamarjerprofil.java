package kamarjer;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Maini.DbConn;
import Maini.log;


//
public class kamarjerprofil {
    private JPanel container;
    private CardLayout cards;
    private JPanel layeredPane; 
    private CardLayout layeredCards;
    private JTextField textField;
    private JTextField textField_1;
    
    private JTextField textField_2;
    private JTextField textField_3;
   
    private JTable table;
    private JPasswordField passwordField;
    private int userId;
    
    //
    public kamarjerprofil(JPanel container, CardLayout cards, int userId) {
        this.userId = userId;
    	this.container = container;
        this.cards = cards;
    }
    
	/**
     * @wbp.parser.entryPoint
     */
    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(200, 50, 999, 750);
        panel.setBackground(new Color(227, 220, 198));
        
        
        String[] columnNames = {"Name", "1", "2", "3", "4"};
        		System.out.println("hiii" + userId);
               

               DefaultTableModel model = new DefaultTableModel( columnNames, 0);
               
               JLabel lblNewLabel_4 = new JLabel("State");
               lblNewLabel_4.setBounds(487, 446, 92, 23);
               panel.add(lblNewLabel_4);
               
               JCheckBox chckbxNewCheckBox = new JCheckBox("Active");
               chckbxNewCheckBox.setBounds(487, 479, 79, 31);
               panel.add(chckbxNewCheckBox);
               
               JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Not Active");
               chckbxNewCheckBox_1.setBounds(585, 479, 79, 31);
               panel.add(chckbxNewCheckBox_1);
               
               JButton btnNewButton_3 = new JButton("Edit");
               btnNewButton_3.setBounds(426, 415, 85, 21);
               panel.add(btnNewButton_3);
               
               JLabel lblNewLabel = new JLabel("   Profile Picture");
               lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
               lblNewLabel.setBackground(new Color(255, 255, 255));
               lblNewLabel.setBounds(376, 218, 203, 187);
               lblNewLabel.setOpaque(true);
               panel.add(lblNewLabel);
               
               
               String[] shiftColumns = {"Shift ID", "Date", "Start Time", "End Time", "Status"};
               String[][] shiftData = {
                   {"1", "2025-02-14", "08:00", "16:00", "Completed"},
                   {"2", "2025-02-15", "09:00", "17:00", "Scheduled"},
                   {"3", "2025-02-16", "10:00", "18:00", "Pending"}
               };
               
               DefaultTableModel shiftModel = new DefaultTableModel(shiftData, shiftColumns);
               JTable tableShifts = new JTable(shiftModel);
               JScrollPane shiftScrollPane = new JScrollPane(tableShifts);
               shiftScrollPane.setBounds(487, 540, 489, 200);
               panel.add(shiftScrollPane);

               String[] orderColumns = {"Order ID", "Customer Name", "Item", "Quantity", "Total"};
               String[][] orderData = {
                   {"101", "John Doe", "Pizza", "2", "$20.00"},
                   {"102", "Jane Smith", "Pasta", "1", "$12.00"},
                   {"103", "Tom Lee", "Salad", "3", "$15.00"}
               };
               
               DefaultTableModel orderModel = new DefaultTableModel(orderData, orderColumns);
               JTable tableOrderHistory = new JTable(orderModel);
               JScrollPane orderScrollPane = new JScrollPane(tableOrderHistory);
               orderScrollPane.setBounds(23, 540, 434, 200);
               panel.add(orderScrollPane);
               
               JButton btnNewButton_4 = new JButton("Log Out");
               btnNewButton_4.setBackground(new Color(241, 231, 214));
               btnNewButton_4.setBounds(861, 10, 98, 23);
               btnNewButton_4.addActionListener(e -> cards.show(container, "kamarjerprofil"));
               panel.add(btnNewButton_4);
               
               passwordField = new JPasswordField();
               passwordField.setBounds(151, 483, 106, 23);
               panel.add(passwordField);
               
               JLabel lblNewLabel_7 = new JLabel("Password");
               lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
               lblNewLabel_7.setBounds(42, 482, 99, 23);
               panel.add(lblNewLabel_7);
               
               JButton btnSave = new JButton("Save");
               btnSave.setBounds(302, 484, 85, 21);
               panel.add(btnSave);
               
               loadPassword();

               // Save button action
               btnSave.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       updatePassword();
                   }
               });
               
               JLabel lblNewLabel_8 = new JLabel("Password Configuration");
               lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
               lblNewLabel_8.setBounds(23, 407, 153, 25);
               panel.add(lblNewLabel_8);
               
               JSeparator separator_1 = new JSeparator();
               separator_1.setBounds(23, 379, 328, 18);
               panel.add(separator_1);
               
               JSeparator separator_2 = new JSeparator();
               separator_2.setBounds(593, 379, 376, 21);
               panel.add(separator_2);
               
               JSeparator separator_3 = new JSeparator();
               separator_3.setOrientation(SwingConstants.VERTICAL);
               separator_3.setBounds(467, 450, 9, 279);
               panel.add(separator_3);
               
               JButton btnNewButton_9 = new JButton("<-Back");
               btnNewButton_9.setBackground(new Color(233, 220, 201));
               btnNewButton_9.setBounds(746, 10, 97, 23);
               btnNewButton_9.addActionListener(e -> cards.show(container, "tables1"));
               panel.add(btnNewButton_9);
               
             
               JLabel imageLabel = new JLabel();

               // Set the bounds for the JLabel (x, y, width, height)
               imageLabel.setBounds(0, 0, 999, 379); // Adjusted bounds to match the resized image

               
               ImageIcon icon = new ImageIcon(log.class.getResource("/img/sm.jpg")); // Replace with your image path
               Image image = icon.getImage(); // Get the image from the icon

               // Resize the image to 800x300
               Image resizedImage = image.getScaledInstance(999, 379, Image.SCALE_SMOOTH);
               ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create a new ImageIcon with the resized image

               // Set the resized image to the JLabel
               imageLabel.setIcon(resizedIcon);

               // Add the JLabel to the panel
               panel.add(imageLabel);
        
        return panel;
    }
    
    private void loadPassword() {
        try (Connection conn = DbConn.getConnection()) {
            String query = "SELECT password FROM users WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                passwordField.setText(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePassword() {
        String newPassword = new String(passwordField.getPassword());

        try (Connection conn = DbConn.getConnection()) {
            String query = "UPDATE users SET password = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, userId);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Password updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

