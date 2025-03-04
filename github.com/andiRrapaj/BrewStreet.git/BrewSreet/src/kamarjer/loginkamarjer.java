package kamarjer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import Maini.DbConn;
import Maini.log;
import Maini.maini;

public class loginkamarjer {
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JComboBox<String> usernameComboBox;
    private JPasswordField passwordField;

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
                String selectedUser = (String) usernameComboBox.getSelectedItem();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                if (validateLogin(selectedUser, password)) {
                    cards.show(container, "tables1");
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid login credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(btnNewButton);

        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(622, 155, 157, 173);
        ImageIcon icon = new ImageIcon(log.class.getResource("/img/lg1.png"));
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(157, 173, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        imageLabel.setIcon(resizedIcon);
        panel.add(imageLabel);

        usernameComboBox = new JComboBox<>();
        usernameComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        usernameComboBox.setBounds(643, 388, 136, 32);
        panel.add(usernameComboBox);
        loadWaiterUsers();

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
        imageLabel1.setBounds(0, 0, 495, 811);
        ImageIcon icon1 = new ImageIcon(log.class.getResource("/img/km.jpg"));
        Image image1 = icon1.getImage();
        Image resizedImage1 = image1.getScaledInstance(495, 811, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
        imageLabel1.setIcon(resizedIcon1);
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

        passwordField = new JPasswordField();
        passwordField.setBounds(643, 457, 136, 32);
        panel.add(passwordField);

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnNewButton.doClick();
                }
            }
        });
    }

    private void loadWaiterUsers() {
        try (Connection conn = DbConn.getConnection()) {
            String query = "SELECT username FROM users WHERE role = 'waiter'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            usernameComboBox.removeAllItems();
            while (rs.next()) {
                usernameComboBox.addItem(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validateLogin(String username, String password) {
        try (Connection conn = DbConn.getConnection()) {
            String query = "SELECT password FROM users WHERE username = ? AND role = 'waiter'";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public JPanel getPanel() {
        return panel;
    }
}
