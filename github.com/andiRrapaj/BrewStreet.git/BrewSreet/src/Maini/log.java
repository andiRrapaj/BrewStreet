package Maini;

import javax.swing.*;
import Maini.maini;
//mk kl
import java.awt.*;
import java.awt.event.*;
import java.awt.*;

public class log {
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;

//
    public log(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(255, 254, 242)); 
            
    JButton btnNewButton = new JButton("Admin");
    btnNewButton.setBackground(new Color(255, 255, 249));
    btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btnNewButton.setBounds(141, 474, 123, 41);
    btnNewButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	 cards.show(container, "loginadm");
        }

    });


    panel.add(btnNewButton);
    
    JButton btnNewButton_1 = new JButton("Rezervimet");
    btnNewButton_1.setBackground(new Color(255, 255, 249));
    btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnNewButton_1.setBounds(390, 474, 123, 41);
    btnNewButton_1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	 cards.show(container, "rezervime");
        }

    });
    panel.add(btnNewButton_1);
    
    JButton btnNewButton_2 = new JButton("Kamarier");
    btnNewButton_2.setBackground(new Color(255, 255, 249));
    btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
    btnNewButton_2.setBounds(640, 474, 123, 41);
    btnNewButton_2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	 cards.show(container, "loginkamarjer");
        }

    });
    panel.add(btnNewButton_2);
   
  
    ImageIcon imageIcon = new ImageIcon(log.class.getResource("/img/admin.png"));
    Image image = imageIcon.getImage().getScaledInstance(179, 161, Image.SCALE_SMOOTH); 
    JLabel imageLabel = new JLabel(new ImageIcon(image));
    imageLabel.setBounds(108,203,202,181); 
    panel.add(imageLabel);
 
    ImageIcon imageIcon1 = new ImageIcon(log.class.getResource("/img/bk.jpg"));
    Image image1 = imageIcon1.getImage().getScaledInstance(179, 161, Image.SCALE_SMOOTH); 
    JLabel imageLabel1 = new JLabel(new ImageIcon(image1));
    imageLabel1.setBounds(362,212,195,172); 
    panel.add(imageLabel1);
    

    ImageIcon imageIcon2 = new ImageIcon(log.class.getResource("/img/Waiter.png"));
    Image image2 = imageIcon2.getImage().getScaledInstance(179, 161, Image.SCALE_SMOOTH); 
    JLabel imageLabel2 = new JLabel(new ImageIcon(image2));
    imageLabel2.setBounds(616,203,202,181); 
    panel.add(imageLabel2);
    
    JSeparator separator = new JSeparator();
    separator.setBounds(94, 421, 788, 14);
    panel.add(separator);
    
    JLabel lblNewLabel = new JLabel("BrewStreet");
    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
    lblNewLabel.setBounds(390, 49, 157, 41);
    panel.add(lblNewLabel);

    
    
    }
    
    public JPanel getPanel() {
        return panel;
    }

}