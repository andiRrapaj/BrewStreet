package kamarjer;

import javax.swing.*;

import Maini.log;
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
//

    public loginkamarjer(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(102, 102, 102)); 
        
        JButton btnNewButton = new JButton("Log-in");
        btnNewButton.setBackground(new Color(148, 148, 148));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(672, 529, 107, 24);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "tables1");
            }

        });
      
    
    
        panel.add(btnNewButton);
        
        JLabel imageLabel = new JLabel();

        // Set the bounds for the JLabel (x, y, width, height)
        imageLabel.setBounds(622, 155, 157, 173); // Adjusted bounds to match the resized image

        // Load the image
        ImageIcon icon = new ImageIcon(log.class.getResource("/img/lg1.png")); // Replace with your image path
        Image image = icon.getImage(); // Get the image from the icon

        // Resize the image to 800x300
        Image resizedImage = image.getScaledInstance(157, 173, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create a new ImageIcon with the resized image

        // Set the resized image to the JLabel
        imageLabel.setIcon(resizedIcon);

        // Add the JLabel to the panel
        panel.add(imageLabel);
        
        textField = new JTextField();
        textField.setBounds(643, 388, 136, 32);
        panel.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(643, 454, 136, 32);
        panel.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Waiter Login");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblNewLabel.setBounds(554, 32, 171, 61);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Username:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(554, 388, 82, 34);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Password:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(554, 458, 75, 24);
        panel.add(lblNewLabel_2);
       
        
        JLabel imageLabel1 = new JLabel();

        // Set the bounds for the JLabel (x, y, width, height)
        imageLabel1.setBounds(0, 0, 495, 811); // Adjusted bounds to match the resized image

        // Load the image
        ImageIcon icon1 = new ImageIcon(log.class.getResource("/img/km.jpg")); // Replace with your image path
        Image image1 = icon1.getImage(); // Get the image from the icon

        // Resize the image to 800x300
        Image resizedImage1 = image1.getScaledInstance(495, 811, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(resizedImage1); // Create a new ImageIcon with the resized image

        // Set the resized image to the JLabel
        imageLabel1.setIcon(resizedIcon1);

        // Add the JLabel to the panel
        panel.add(imageLabel1);
        
        JButton btnNewButton_1 = new JButton("<-Back");
        btnNewButton_1.setBackground(new Color(148, 148, 148));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(872, 10, 85, 21);
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