package kamarjer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Blob;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class kamarjerframe {
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    private static JTable table;
    private JTextField textField_2;
    private JTextField textField_3;
    private static JTextField txtSearch;

    private int buttonId; 
 
    public kamarjerframe(JPanel container, CardLayout cards,int buttonId) {
        this.container = container;
        this.cards = cards;
        this.buttonId = buttonId; // Store the button ID
      
       

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800);
        panel.setBackground(new Color(234, 228, 210));

        String[] columnNames = {"ID", "Price", "Quantity", "Item"};
        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);

        JScrollPane scrollPane1 = new JScrollPane(table);
        scrollPane1.setBounds(363, 105, 297, 430);
        panel.add(scrollPane1);

        loadImagesPanel1(panel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 2, 10, 10));

        for (int i = 0; i < 9; i++) {
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new BorderLayout());

            JLabel imageLabel = new JLabel(new ImageIcon("bk.jpg"));
            imageLabel.setPreferredSize(new java.awt.Dimension(150, 150));
            labelPanel.add(imageLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));

            for (int j = 0; j < 2; j++) {
                JButton button = new JButton("Button " + (j + 1));
                buttonPanel.add(button);
            }

            labelPanel.add(buttonPanel, BorderLayout.SOUTH);
            contentPanel.add(labelPanel);
        }

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(672, 105, 318, 430);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("<html><center>BrewStreet Café<br>123 Main Street, City, Country<br>"
                + "Phone: +XXX-XXX-XXXX<br>Email: contact@brewstreet.com<br>"
                + "</center></html>");
        lblNewLabel_1.setBounds(68, 10, 200, 60);
        panel_2.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JLabel lblNewLabel_4 = new JLabel("*****************************************************************************************************");
        lblNewLabel_4.setBounds(10, 85, 298, 13);
        panel_2.add(lblNewLabel_4);

        JLabel lblNewLabel_4_1 = new JLabel("*****************************************************************************************************");
        lblNewLabel_4_1.setBounds(10, 126, 298, 13);
        panel_2.add(lblNewLabel_4_1);

        JLabel lblNewLabel_5 = new JLabel("Item");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_5.setBounds(10, 97, 70, 19);
        panel_2.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Quantity");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_6.setBounds(123, 97, 70, 19);
        panel_2.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Price");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_7.setBounds(223, 97, 64, 19);
        panel_2.add(lblNewLabel_7);

        JButton btnNewButton_1 = new JButton("Bar");
        btnNewButton_1.setBounds(24, 67, 85, 28);
        panel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Restorant");
        btnNewButton_2.setBounds(119, 67, 85, 28);
        panel.add(btnNewButton_2);

        txtSearch = new JTextField();
        txtSearch.setText("Search....");
        txtSearch.setBounds(36, 16, 168, 28);
        panel.add(txtSearch);
        txtSearch.setColumns(10);

    
    JButton btnPay = new JButton("Pay");
    btnPay.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnPay.setBounds(696, 585, 112, 124);
    btnPay.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveDataToPayedTable();
        }
    });
    panel.add(btnPay);

    // Other UI components...


        JButton btnNewButton_4 = new JButton("Print");
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_4.setBounds(833, 585, 112, 124);
        panel.add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("Delete");
        btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(panel, "Please select a row to delete.");
                }
            }
        });
        btnNewButton_5.setBounds(559, 555, 101, 36);
        panel.add(btnNewButton_5);

        JLabel lblNewLabel = new JLabel("Total:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(382, 625, 85, 28);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("Cash:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_2.setBounds(382, 675, 57, 28);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Balance:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_3.setBounds(382, 719, 57, 28);
        panel.add(lblNewLabel_3);

        JButton btnNewButton = new JButton("X");
        btnNewButton.setBounds(880, 19, 45, 28);
        btnNewButton.addActionListener(e -> cards.show(container, "tables1"));
        panel.add(btnNewButton);
    }
    private void saveDataToPayedTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();

        String sql = "INSERT INTO payed (button_id, item_id, price, quantity, item_name) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < rowCount; i++) {
                int itemId = (int) model.getValueAt(i, 0); // ID
                double price = (double) model.getValueAt(i, 1); // Price
                int quantity = (int) model.getValueAt(i, 2); // Quantity
                String itemName = (String) model.getValueAt(i, 3); // Item Name
                int btnId = buttonId; 

                pstmt.setInt(1, buttonId); // Use the button ID
                pstmt.setInt(2, itemId);
                pstmt.setDouble(3, price);
                pstmt.setInt(4, quantity);
                pstmt.setString(5, itemName);

                pstmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(panel, "Data saved to 'payed' table successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error saving data to the 'payed' table.");
        }
    }
    // Method to set the button ID
    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
        System.out.println("Button ID set to: " + buttonId); // Debugging: Print the button ID
    }

    // Rest of your existing methods...
    private static void loadImagesPanel1(JPanel panel) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
            String sql = "SELECT id, image, name, price, quantity FROM products";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new GridLayout(0, 3, 10, 20));

            while (rs.next()) {
                final int id = rs.getInt("id");
                final String name = rs.getString("name");
                final double price = rs.getDouble("price");
                final int quantity = rs.getInt("quantity");
                Blob imageBlob = (Blob) rs.getBlob("image");
                byte[] imageBytes = imageBlob != null ? imageBlob.getBytes(1, (int) imageBlob.length()) : null;

                if (imageBytes != null) {
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image img = imageIcon.getImage();
                    Image resizedImg = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
                    JLabel imageLabel = new JLabel(new ImageIcon(resizedImg));

                    imageLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (table.getModel() instanceof DefaultTableModel) {
                                DefaultTableModel model = (DefaultTableModel) table.getModel();
                                model.addRow(new Object[]{id, price, quantity, name});
                            } else {
                                JOptionPane.showMessageDialog(panel, "Table model is not of type DefaultTableModel");
                            }
                        }
                    });

                    imagePanel.add(imageLabel);
                }
            }

            JScrollPane scrollPane = new JScrollPane(imagePanel);
            scrollPane.setBounds(10, 105, 343, 555);
            panel.add(scrollPane);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error loading images.");
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}