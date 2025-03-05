package kamarjer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Maini.ImageRenderer;

import java.sql.Blob;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
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

    private int tableId; 
 
    public kamarjerframe(JPanel container, CardLayout cards,int tableId) {
        this.container = container;
        this.cards = cards;
        this.tableId = tableId; // Store the button ID
      
        System.out.println("Button ID set to: " + tableId);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800);
        panel.setBackground(new Color(234, 228, 210));

        String[] columnNames = {"Image"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        table = new JTable(model);
        
       
        table.getColumn("Image").setCellRenderer(new ImageRenderer());


        
    
        JScrollPane scrollPane11 = new JScrollPane(table);
        scrollPane11.setBounds(363, 105, 297, 430);
        
        panel.add(scrollPane11);
        loadImagesFromDatabase(container);


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
    
    panel.add(btnPay);
    JPanel imagePanel = new JPanel();
    imagePanel.setLayout(new GridLayout(0, 2, 10, 10)); // 2 images per row with gaps
    imagePanel.setPreferredSize(new Dimension(2 * 150 + 1 * 10, 430)); // Adjust width for 2 images per row

    panel.add(imagePanel);

    JScrollPane scrollPane = new JScrollPane(imagePanel);
    scrollPane.setBounds(22, 105, 2 * 150 + 1 * 10, 430); // Same width as imagePanel
    panel.add(scrollPane);



    loadImagesFromDatabase(imagePanel);
    
        JButton btnNewButton_4 = new JButton("Print");
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_4.setBounds(833, 585, 112, 124);
        panel.add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("Delete");
        btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
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
   
    private void loadImagesFromDatabase(JPanel imagePanel) {
        System.out.println("Loading images from database...");
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/brewstreet2", "root", "root");
            String sql = "SELECT image FROM products";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Clear previous images
            imagePanel.removeAll();

            while (rs.next()) {
                Blob blob = rs.getBlob("image");
                byte[] imageBytes = null;
                if (blob != null) {
                    imageBytes = blob.getBytes(1, (int) blob.length());
                } else {
                    imageBytes = getDefaultImage();  // Use a default image if null
                }

                // Create ImageIcon and add it to a JLabel
                ImageIcon imageIcon = new ImageIcon(imageBytes);
                Image img = imageIcon.getImage();
                Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);  // Resize the image
                imageIcon = new ImageIcon(scaledImg);
                
                JLabel imageLabel = new JLabel(imageIcon);
                imagePanel.add(imageLabel);  // Add the image to the panel
            }

            // Refresh the panel after loading all images
            imagePanel.revalidate();
            imagePanel.repaint();

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] getDefaultImage() {
        // Return a default image byte array (replace with your own logic)
        return new byte[0];
    }

    
    public JPanel getPanel() {
        return panel;
    }
}