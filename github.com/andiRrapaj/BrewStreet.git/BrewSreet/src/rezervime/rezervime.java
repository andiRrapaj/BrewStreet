package rezervime;


import javax.swing.*;
import Maini.maini;

import java.awt.*;
import java.awt.event.*;


public class rezervime{
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    private  JPanel panel_1 = new JPanel();
    private JTextField textField_2;
    private JTextField textField_3;


    public rezervime(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(192, 192, 192)); 

    
    
        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setBackground(new Color(128, 128, 128));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(801, 90, 85, 21);
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "log");
            }

        });
        panel.add(btnNewButton_1);
        
        textField_2 = new JTextField();
        textField_2.setBounds(343, 265, 148, 30);
        panel.add(textField_2);
        textField_2.setColumns(10);
        
        textField_3 = new JTextField();
        textField_3.setBounds(343, 345, 148, 30);
        panel.add(textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblNewLabel.setBounds(237, 260, 96, 30);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Password:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblNewLabel_1.setBounds(237, 345, 96, 30);
        panel.add(lblNewLabel_1);
        
        JButton btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setBounds(406, 418, 85, 21);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "rezervimeloged");
            }

        });
        panel.add(btnNewButton);
    }
    
    


    public JPanel getPanel() {
        return panel;
    }
}