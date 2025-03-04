package rezervime;

import javax.swing.*;

import Maini.DbConn;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class rezervime {
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JComboBox<String> usernameComboBox;
    private JPasswordField passwordField;

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

        usernameComboBox = new JComboBox<>();
        usernameComboBox.setBounds(662, 304, 201, 30);
        panel.add(usernameComboBox);
        loadReceptionistUsers();

        passwordField = new JPasswordField();
        passwordField.setBounds(662, 395, 201, 30);
        panel.add(passwordField);

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
                String selectedUser = (String) usernameComboBox.getSelectedItem();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                if (validateLogin(selectedUser, password)) {
                    cards.show(container, "rezervimeloged");
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid login credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnNewButton.doClick();
                }
            }
        });
    }

    private void loadReceptionistUsers() {
        try (Connection conn = DbConn.getConnection()) {
            String query = "SELECT username FROM users WHERE role = 'receptionist'";
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
            String query = "SELECT password FROM users WHERE username = ? AND role = 'receptionist'";
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
