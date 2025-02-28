package kamarjer;

import javax.swing.*;
import Maini.maini;

import java.awt.*;
import java.awt.event.*;
// kleo

public class loginkamarjer{
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    private  JPanel panel_1 = new JPanel();


    public loginkamarjer(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(192, 192, 192)); 
        
        JButton btnNewButton = new JButton("Log-in");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(659, 437, 107, 34);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "tables1");
            }

        });
      
    
    
        panel.add(btnNewButton);
        
        textField = new JTextField();
        textField.setBounds(630, 268, 136, 34);
        panel.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(630, 368, 136, 34);
        panel.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Waiter Login");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(630, 136, 154, 34);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(538, 265, 82, 34);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(546, 370, 82, 24);
        panel.add(lblNewLabel_2);
        panel_1.setBounds(0, 0, 411, 800);
        panel.add(panel_1);
        
        panel_1.setPreferredSize(new Dimension(800, 1000)); // Set the preferred size of panel
       
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 800, 600); 
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        panel_1.add(scrollPane);
        
        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setBackground(new Color(128, 128, 128));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(887, 27, 85, 21);
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "log");
            }

        });
        panel.add(btnNewButton_1);
    
    }
    
    public JPanel getPanel() {
        return panel;
    }
}