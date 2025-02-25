package kamarjer;

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
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_4;
    private JTextField textField_7;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;

    public kamarjerprofil(JPanel container, CardLayout cards) {
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
               textField_5.setBounds(125, 147, 125, 23);
               panel.add(textField_5);
               textField_5.setColumns(10);
               
               textField_6 = new JTextField();
               textField_6.setBounds(125, 180, 125, 23);
               panel.add(textField_6);
               textField_6.setColumns(10);
               
               JLabel lblNewLabel_1 = new JLabel("Name");
               lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
               lblNewLabel_1.setBounds(42, 142, 49, 33);
               panel.add(lblNewLabel_1);
               
               textField_4 = new JTextField();
               textField_4.setBounds(125, 213, 125, 23);
               panel.add(textField_4);
               textField_4.setColumns(10);
               
               JLabel lblNewLabel_2 = new JLabel("ID");
               lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
               lblNewLabel_2.setBounds(43, 180, 40, 23);
               panel.add(lblNewLabel_2);
               
               JLabel lblNewLabel_3 = new JLabel("Phone Number");
               lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
               lblNewLabel_3.setBounds(18, 213, 97, 23);
               panel.add(lblNewLabel_3);
               
               JLabel lblNewLabel_4 = new JLabel("State");
               lblNewLabel_4.setBounds(506, 344, 92, 23);
               panel.add(lblNewLabel_4);
               
               JCheckBox chckbxNewCheckBox = new JCheckBox("Active");
               chckbxNewCheckBox.setBounds(516, 373, 79, 31);
               panel.add(chckbxNewCheckBox);
               
               JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Not Active");
               chckbxNewCheckBox_1.setBounds(607, 373, 79, 31);
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
               JTable tableShifts = new JTable(shiftModel);
               JScrollPane shiftScrollPane = new JScrollPane(tableShifts);
               shiftScrollPane.setBounds(481, 462, 489, 200);
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
               orderScrollPane.setBounds(23, 462, 434, 200);
               panel.add(orderScrollPane);

               JLabel lblNewLabel_61 = new JLabel("Position");
               lblNewLabel_61.setFont(new Font("Tahoma", Font.PLAIN, 13));
               lblNewLabel_61.setBounds(4, 89, 55, 36);
               panel.add(lblNewLabel_61);

               textField_7 = new JTextField();
               textField_7.setBounds(71, 96, 125, 23);
               panel.add(textField_7);
               textField_7.setColumns(10);
               
               JButton btnNewButton = new JButton("Edit");
               btnNewButton.setBounds(39, 685, 92, 23);
               panel.add(btnNewButton);
               
               JButton btnNewButton_1 = new JButton("Delete");
               btnNewButton_1.setBounds(167, 685, 92, 23);
               panel.add(btnNewButton_1);
               
               JButton btnNewButton_2 = new JButton("Add");
               btnNewButton_2.setBounds(304, 685, 92, 23);
               panel.add(btnNewButton_2);
               
               JButton btnNewButton_4 = new JButton("Log Out");
               btnNewButton_4.setBounds(870, 47, 98, 23);
               btnNewButton_4.addActionListener(e -> cards.show(container, "kamarjerdashboard"));
               panel.add(btnNewButton_4);
               
               JButton btnNewButton_5 = new JButton("Edit");
               btnNewButton_5.setBounds(14, 269, 85, 21);
               panel.add(btnNewButton_5);
               
               JButton btnNewButton_6 = new JButton("Save");
               btnNewButton_6.setBounds(125, 269, 85, 21);
               panel.add(btnNewButton_6);
               
               passwordField = new JPasswordField();
               passwordField.setBounds(132, 360, 106, 23);
               panel.add(passwordField);
               
               passwordField_1 = new JPasswordField();
               passwordField_1.setBounds(132, 393, 106, 23);
               panel.add(passwordField_1);
               
               JLabel lblNewLabel_6 = new JLabel("Old Password");
               lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
               lblNewLabel_6.setBounds(23, 365, 99, 18);
               panel.add(lblNewLabel_6);
               
               JLabel lblNewLabel_7 = new JLabel("New Password");
               lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
               lblNewLabel_7.setBounds(23, 393, 99, 23);
               panel.add(lblNewLabel_7);
               
               JButton btnNewButton_7 = new JButton("Save");
               btnNewButton_7.setBounds(266, 394, 85, 21);
               panel.add(btnNewButton_7);
               
               JButton btnNewButton_8 = new JButton("Forgot");
               btnNewButton_8.setBounds(266, 361, 85, 21);
               panel.add(btnNewButton_8);
               
               JLabel lblNewLabel_8 = new JLabel("Password Configuration");
               lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
               lblNewLabel_8.setBounds(14, 325, 153, 25);
               panel.add(lblNewLabel_8);
               
               JSeparator separator = new JSeparator();
               separator.setOrientation(SwingConstants.VERTICAL);
               separator.setBounds(466, 62, 9, 240);
               panel.add(separator);
               
               JSeparator separator_1 = new JSeparator();
               separator_1.setBounds(23, 313, 439, 18);
               panel.add(separator_1);
               
               JSeparator separator_2 = new JSeparator();
               separator_2.setBounds(473, 313, 497, 21);
               panel.add(separator_2);
               
               JSeparator separator_3 = new JSeparator();
               separator_3.setOrientation(SwingConstants.VERTICAL);
               separator_3.setBounds(467, 324, 9, 352);
               panel.add(separator_3);
        
        return panel;
    }
}
