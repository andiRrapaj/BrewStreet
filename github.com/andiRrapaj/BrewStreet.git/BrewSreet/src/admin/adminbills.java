package admin;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;


public class adminbills{
    
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    
    private JTextField textField_2;
    private JTextField textField_3;
    
    private JTable table;
    private JTextField textField_4;
    private JTextField textField_5;

    /**
     * @wbp.parser.entryPoint
     */
    public JPanel getPanel() {
       
    	
    	JPanel panel = new JPanel();
    	panel.setBounds(200, 2000, 1136, 670);
    	panel.setBackground(Color.LIGHT_GRAY);
    	panel.setLayout(null);
		

        String[] columnNames = {"Bill", "Date", "Person"};

        

        DefaultTableModel model = new DefaultTableModel( columnNames, 0);
        JTable table = new JTable(model);

       
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 134, 486, 494);
        panel.add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("Bills&Orders");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(25, 20, 120, 24);
        panel.add(lblNewLabel);
        
        
        
        JButton btnNewButton_1 = new JButton("Delete");
        btnNewButton_1.setBounds(534, 597, 138, 31);
        panel.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Print");
        btnNewButton_2.setBounds(534, 550, 138, 31);
        panel.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("Edit");
        btnNewButton_3.setBounds(604, 509, 68, 31);
        panel.add(btnNewButton_3);

        JComboBox comboBox = new JComboBox(new String[]{"Category ", "Item 2", "Item 3", "Item 4"});
        comboBox.setBounds(534, 452, 138, 31); // Adjusted size for better visibility
        panel.add(comboBox);
        
        JButton btnNewButton = new JButton("Save");
        btnNewButton.setBounds(534, 509, 68, 31);
        panel.add(btnNewButton);
        
        textField_4 = new JTextField();
        textField_4.setBounds(534, 224, 183, 191);
        panel.add(textField_4);
        textField_4.setColumns(10);
        
        JButton btnNewButton_4 = new JButton("View Details");
        btnNewButton_4.setBounds(534, 156, 115, 31);
        panel.add(btnNewButton_4);
        
        JLabel lblNewLabel_1 = new JLabel("Filter");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(25, 62, 73, 24);
        panel.add(lblNewLabel_1);
        
        textField_5 = new JTextField();
        textField_5.setBounds(25, 90, 96, 19);
        panel.add(textField_5);
        textField_5.setColumns(10);
        
        return panel;
        
    }
}

