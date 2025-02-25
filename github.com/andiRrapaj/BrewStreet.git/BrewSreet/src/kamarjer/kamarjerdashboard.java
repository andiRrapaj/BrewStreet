package kamarjer;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



import Maini.maini;

import java.awt.*;
import java.awt.event.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class kamarjerdashboard{
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    private JTable table;  // Declare the table as a class-level variable

    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField txtSearch;


    public kamarjerdashboard(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(192, 192, 192)); 
        
        String[] columnNames = {"ID", "Price", "Quantity", "Item"};
        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);  // This ensures the table uses DefaultTableModel
 // This makes table accessible to all methods

        JScrollPane scrollPane1 = new JScrollPane(table);
        scrollPane1.setBounds(363, 105, 297, 430);
        panel.add(scrollPane1);

        // Now call loadImagesPanel
        loadImagesPanel(panel);
        

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
        lblNewLabel_1.setBounds(66, 10, 200, 60);
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

        
        JButton btnNewButton = new JButton("Profile");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton.setBounds(828, 10, 112, 36);
        btnNewButton.addActionListener(e -> cards.show(container, "kamarjerprofil"));
        panel.add(btnNewButton);
        
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
        
       

        
        JButton btnNewButton_3 = new JButton("Pay");
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_3.setBounds(696, 585, 112, 124);
        panel.add(btnNewButton_3);
        
        JButton btnNewButton_4 = new JButton("Print");
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_4.setBounds(833, 585, 112, 124);
        panel.add(btnNewButton_4);
        
        JButton btnNewButton_5 = new JButton("Delete");
        btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row from the table
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow != -1) {  // Check if a row is selected
                    // Get the model of the table
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    
                    // Remove the selected row
                    model.removeRow(selectedRow);
                } else {
                    // Show a message if no row is selected
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
 
    
    
    
    }
    
    
    private void loadImagesPanel(JPanel panel) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet", "root", "root")) {
            String sql = "SELECT id, image, name, price, quantity FROM products"; // Fetching necessary columns
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            // Create a panel with a GridLayout of 3 columns (adjust if you need more/less columns)
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new GridLayout(0, 3, 10, 20));  // 3 columns, 10px gap

            // Populate the panel with images from the database
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
                    Image resizedImg = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);  // Resize the image
                    JLabel imageLabel = new JLabel(new ImageIcon(resizedImg));

                    imageLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // Ensure the table model is of type DefaultTableModel
                            if (table.getModel() instanceof DefaultTableModel) {
                                DefaultTableModel model = (DefaultTableModel) table.getModel();
                                
                                // Add a new row without clearing the existing rows
                                model.addRow(new Object[]{id, price, quantity, name});
                            } else {
                                JOptionPane.showMessageDialog(panel, "Table model is not of type DefaultTableModel");
                            }
                        }
                    });




                    imagePanel.add(imageLabel);  // Add the image label to the panel
                }
            }

            // Scroll pane for the image panel (to make it scrollable if there are many images)
            JScrollPane scrollPane = new JScrollPane(imagePanel);
            scrollPane.setBounds(10, 105, 343, 555);  // Adjust the size and position as needed
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
        