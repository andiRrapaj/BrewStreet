package admin;

import javax.swing.*;
import Maini.maini;

import java.awt.*;
import java.awt.event.*;
import java.awt.*;

public class loginadm {
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;


    public loginadm(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
       
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(0, 128, 128)); 
        
        JButton btnNewButton = new JButton("Log-in");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(602, 473, 211, 34);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "admindashboard");
            }

        });
        panel.add(btnNewButton);
        
        
        
        textField = new JTextField();
        textField.setBounds(574, 286, 251, 34);
        panel.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(574, 366, 251, 34);
        panel.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Please Login to Admin Dashboard!");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 15));
        lblNewLabel.setBounds(558, 169, 251, 34);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("Welcome");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 29));
        lblNewLabel_2.setBounds(602, 115, 145, 44);
        panel.add(lblNewLabel_2);
        
   
        ImageIcon imageIcon = new ImageIcon("ic.png");
        Image image = imageIcon.getImage().getScaledInstance(179, 161, Image.SCALE_SMOOTH); 
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(59,191,332,323); 
        panel.add(imageLabel);
        
        
        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setBackground(new Color(128, 128, 128));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(795, 61, 85, 21);
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "log");
            }

        });
        panel.add(btnNewButton_1);
        
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(444, 62, 16, 667);
        panel.add(separator);
    }
    
    public JPanel getPanel() {
        return panel;
    }
}