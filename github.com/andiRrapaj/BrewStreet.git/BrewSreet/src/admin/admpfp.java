package admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
;


public class admpfp {
    private JPanel container;
    private CardLayout cards;
    private JPanel layeredPane; 
    private CardLayout layeredCards;
    private JTextField textField;
    private JTextField textField_1;
    
    private JTextField textField_2;
    private JTextField textField_3;
   
    private JTable table;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_4;
    private JTextField textField_7;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;

    public admpfp(JPanel container, CardLayout cards) {
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
        panel.setBackground(new Color(192, 192, 192));
        
        
        String[] columnNames = {"Name", "1", "2", "3", "4"};

               

               DefaultTableModel model = new DefaultTableModel( columnNames, 0);
               
               textField_5 = new JTextField();
               textField_5.setBounds(93, 180, 156, 31);
               panel.add(textField_5);
               textField_5.setColumns(10);
               
               textField_6 = new JTextField();
               textField_6.setBounds(93, 235, 156, 31);
               panel.add(textField_6);
               textField_6.setColumns(10);
               
               JLabel lblNewLabel_1 = new JLabel("Name");
               lblNewLabel_1.setBounds(14, 179, 49, 33);
               panel.add(lblNewLabel_1);
               
               textField_4 = new JTextField();
               textField_4.setBounds(93, 296, 156, 31);
               panel.add(textField_4);
               textField_4.setColumns(10);
               
               JLabel lblNewLabel_2 = new JLabel("ID");
               lblNewLabel_2.setBounds(23, 239, 40, 23);
               panel.add(lblNewLabel_2);
               
               JLabel lblNewLabel_3 = new JLabel("Phone Number");
               lblNewLabel_3.setBounds(14, 300, 79, 23);
               panel.add(lblNewLabel_3);
               
               JLabel lblNewLabel_4 = new JLabel("State");
               lblNewLabel_4.setBounds(302, 131, 92, 23);
               panel.add(lblNewLabel_4);
               
               JCheckBox chckbxNewCheckBox = new JCheckBox("Active");
               chckbxNewCheckBox.setBounds(332, 157, 79, 31);
               panel.add(chckbxNewCheckBox);
               
               JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Not Active");
               chckbxNewCheckBox_1.setBounds(332, 197, 79, 31);
               panel.add(chckbxNewCheckBox_1);
               
               JButton btnNewButton_3 = new JButton("Edit");
               btnNewButton_3.setBounds(707, 245, 85, 21);
               panel.add(btnNewButton_3);
               
               JLabel lblNewLabel = new JLabel("Profile Picture");
               lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
               lblNewLabel.setBackground(new Color(255, 255, 255));
               lblNewLabel.setBounds(665, 34, 221, 177);
               panel.add(lblNewLabel);
               
               JLabel lblNewLabel_5 = new JLabel("Your Profile");
               lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
               lblNewLabel_5.setBounds(23, 31, 190, 48);
               panel.add(lblNewLabel_5);
               
               
               String[] shiftColumns = {"Shift ID", "Date", "Start Time", "End Time", "Status"};
               String[][] shiftData = {
                   {"1", "2025-02-14", "08:00", "16:00", "Completed"},
                   {"2", "2025-02-15", "09:00", "17:00", "Scheduled"},
                   {"3", "2025-02-16", "10:00", "18:00", "Pending"}
               };
               
               DefaultTableModel shiftModel = new DefaultTableModel(shiftData, shiftColumns);

               String[] orderColumns = {"Order ID", "Customer Name", "Item", "Quantity", "Total"};
               String[][] orderData = {
                   {"101", "John Doe", "Pizza", "2", "$20.00"},
                   {"102", "Jane Smith", "Pasta", "1", "$12.00"},
                   {"103", "Tom Lee", "Salad", "3", "$15.00"}
               };
               
               DefaultTableModel orderModel = new DefaultTableModel(orderData, orderColumns);

               JLabel lblNewLabel_61 = new JLabel("Position");
               lblNewLabel_61.setBounds(14, 124, 79, 36);
               panel.add(lblNewLabel_61);

               textField_7 = new JTextField();
               textField_7.setBounds(93, 127, 156, 31);
               panel.add(textField_7);
               textField_7.setColumns(10);
               
               JButton btnNewButton = new JButton("Change");
               btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
               btnNewButton.setBounds(80, 664, 92, 23);
               panel.add(btnNewButton);
               
               JButton btnNewButton_1 = new JButton("Forgot?");
               btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
               btnNewButton_1.setBounds(220, 664, 100, 23);
               panel.add(btnNewButton_1);
               
               JButton btnNewButton_2 = new JButton("<--Dashboard");
               btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
               btnNewButton_2.addActionListener(e -> cards.show(container, "admindashboard"));
               btnNewButton_2.setBounds(809, 597, 107, 33);
               panel.add(btnNewButton_2);
               
               JButton btnNewButton_4 = new JButton("Log Out");
               btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
               btnNewButton_4.setBounds(809, 659, 107, 33);
               btnNewButton_4.addActionListener(e -> cards.show(container, "log"));
               panel.add(btnNewButton_4);
               
               JButton btnNewButton_5 = new JButton("Edit");
               btnNewButton_5.setBounds(58, 365, 85, 21);
               panel.add(btnNewButton_5);
               
               JButton btnNewButton_6 = new JButton("Save");
               btnNewButton_6.setBounds(164, 365, 85, 21);
               panel.add(btnNewButton_6);
               
               JLabel lblNewLabel_6 = new JLabel("Password Settings");
               lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
               lblNewLabel_6.setBounds(144, 466, 148, 31);
               panel.add(lblNewLabel_6);
               
               passwordField = new JPasswordField();
               passwordField.setBounds(118, 526, 174, 23);
               panel.add(passwordField);
               
               passwordField_1 = new JPasswordField();
               passwordField_1.setBounds(118, 575, 174, 23);
               panel.add(passwordField_1);
               
               JLabel lblNewLabel_7 = new JLabel("Old Password");
               lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
               lblNewLabel_7.setBounds(14, 525, 107, 23);
               panel.add(lblNewLabel_7);
               
               JLabel lblNewLabel_8 = new JLabel("New Password");
               lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
               lblNewLabel_8.setBounds(14, 574, 79, 23);
               panel.add(lblNewLabel_8);
               
               JSeparator separator = new JSeparator();
               separator.setBounds(23, 437, 440, 23);
               panel.add(separator);
               
               JSeparator separator_1 = new JSeparator();
               separator_1.setOrientation(SwingConstants.VERTICAL);
               separator_1.setBounds(473, 450, 13, 290);
               panel.add(separator_1);
               
               JSeparator separator_2 = new JSeparator();
               separator_2.setOrientation(SwingConstants.VERTICAL);
               separator_2.setBounds(473, 94, 13, 333);
               panel.add(separator_2);
               
               JSeparator separator_3 = new JSeparator();
               separator_3.setBounds(484, 437, 451, 16);
               panel.add(separator_3);
        
        return panel;
    }
}
