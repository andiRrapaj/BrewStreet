package admin;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Maini.maini;

import java.awt.*;
import java.awt.event.*;


public class tabels{
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    
    private JTextField textField_2;
    private JTextField textField_3;
    private JTable table;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;


    /**
     * @wbp.parser.entryPoint
     */
    public JPanel getPanel() {
        
    	JPanel panel = new JPanel();
    	panel.setLayout(null);
    	panel.setBounds(0, 0, 800, 800);
    	panel.setBackground(new Color(128, 128, 128));
      
        String[] columnNames = {"Name", "1", "2", "3", "4"};

        

        DefaultTableModel model = new DefaultTableModel( columnNames, 0);
        JTable table = new JTable(model);

       
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 156, 407, 472);
        panel.add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("Tabels\r\n");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(25, 33, 162, 24);
        panel.add(lblNewLabel);
        
        textField_4 = new JTextField();
        textField_4.setBounds(493, 255, 148, 35);
        panel.add(textField_4);
        textField_4.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setBounds(493, 336, 156, 35);
        panel.add(textField_5);
        textField_5.setColumns(10);
        
        textField_6 = new JTextField();
        textField_6.setBounds(493, 415, 156, 35);
        panel.add(textField_6);
        textField_6.setColumns(10);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(473, 517, 85, 21);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("New button");
        btnNewButton_1.setBounds(473, 579, 85, 21);
        panel.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("New button");
        btnNewButton_2.setBounds(618, 517, 85, 21);
        panel.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("New button");
        btnNewButton_3.setBounds(618, 579, 85, 21);
        panel.add(btnNewButton_3);
        
        JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setBounds(493, 215, 103, 30);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Price");
        lblNewLabel_2.setBounds(493, 300, 85, 26);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Quantity");
        lblNewLabel_3.setBounds(493, 381, 85, 26);
        panel.add(lblNewLabel_3);


        
        return panel;
        
    }
}