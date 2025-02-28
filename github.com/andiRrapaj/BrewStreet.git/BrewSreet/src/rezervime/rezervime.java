package rezervime;


import javax.swing.*;
import Maini.maini;
import javax.swing.*;
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
        panel.setBackground(new Color(74, 87, 89)); 

    
    
        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setBackground(new Color(128, 128, 128));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(905, 10, 85, 21);
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "log");
            }

        });
        panel.add(btnNewButton_1);
        
        textField_2 = new JTextField();
        textField_2.setBounds(662, 304, 201, 30);
        panel.add(textField_2);
        textField_2.setColumns(10);
        
        JLabel logo = new JLabel("");
        logo.setBounds(93, 127, 191, 182);
        panel.add(logo);
        
        textField_3 = new JTextField();
        textField_3.setBounds(662, 395, 201, 30);
        panel.add(textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setForeground(new Color(202, 186, 200));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblNewLabel.setBounds(513, 304, 96, 30);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Password:");
        lblNewLabel_1.setBackground(new Color(202, 186, 200));
        lblNewLabel_1.setForeground(new Color(192, 192, 192));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblNewLabel_1.setBounds(513, 395, 96, 30);
        panel.add(lblNewLabel_1);
        
        JButton btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setBounds(630, 488, 104, 30);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "rezervimeloged");
            }

        });
        panel.add(btnNewButton);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBackground(new Color(93, 162, 113));
        lblNewLabel_2.setBounds(0, 0, 375, 800);
        panel.add(lblNewLabel_2);
        lblNewLabel_2.setOpaque(true);
        
        JLabel lblWelcome = new JLabel("WELCOME");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setForeground(new Color(202, 186, 200));
        lblWelcome.setFont(new Font("Mongolian Baiti", Font.BOLD | Font.ITALIC, 35));
        lblWelcome.setBounds(476, 55, 466, 63);
        panel.add(lblWelcome);
        
        
    }
    
    


    public JPanel getPanel() {
        return panel;
    }
}