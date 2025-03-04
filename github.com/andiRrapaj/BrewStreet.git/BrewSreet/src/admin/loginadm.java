package admin;

import javax.swing.*;

import Maini.DbConn;
import Maini.log;
import Maini.maini;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;


public class loginadm {
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField usernameField;
    private JPasswordField passwordField;


    public loginadm(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
       
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(0, 128, 128)); 
        
        
        JButton logInBTN = new JButton("Log-in");
        logInBTN.setBackground(new Color(255, 253, 232));
        logInBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
        logInBTN.setBounds(125, 502, 125, 34);
        logInBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Connection conn = DbConn.getConnection(); 
                    String query = "SELECT * FROM users WHERE username = ? AND password = ? AND role = 'admin'";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, username);
                    pstmt.setString(2, password); 

                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(panel, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        cards.show(container, "admindashboard");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    rs.close();
                    pstmt.close();
                    conn.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(logInBTN);
        
        
        usernameField = new JTextField();
        usernameField.setBounds(65, 347, 185, 34);
        panel.add(usernameField);
        usernameField.setColumns(10);
        

        passwordField = new JPasswordField();
        passwordField.setBounds(65, 415, 185, 34);
        panel.add(passwordField);
        
        JLabel lblNewLabel = new JLabel("Please Login to Admin Dashboard!");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 15));
        lblNewLabel.setBounds(50, 260, 309, 44);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("Welcome");
        lblNewLabel_2.setForeground(new Color(192, 192, 192));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblNewLabel_2.setBounds(96, 91, 159, 61);
        panel.add(lblNewLabel_2);
        
   
        ImageIcon imageIcon = new ImageIcon(log.class.getResource("/img/ic.png"));
        Image image = imageIcon.getImage().getScaledInstance(179, 161, Image.SCALE_SMOOTH); 
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(548,127,332,435); 
        panel.add(imageLabel);
        
        
        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setBackground(new Color(255, 253, 232));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(879, 30, 85, 21);
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "log");
            }

        });
        panel.add(btnNewButton_1);
        
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(428, 61, 16, 667);
        panel.add(separator);
        JLabel bPhoto = new JLabel();
        bPhoto.setSize(1000, 800);
        bPhoto.setLocation(0, 0);
        bPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon originalB = new ImageIcon(log.class.getResource("/img/background.png"));
        Image scaledB = originalB.getImage().getScaledInstance(bPhoto.getWidth(), bPhoto.getHeight(), Image.SCALE_SMOOTH);
        bPhoto.setIcon(new ImageIcon(scaledB));
        panel.add(bPhoto);
        
    }
    
    public JPanel getPanel() {
        return panel;
    }
}