package admin;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Maini.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.*;

public class admpfp {
    private static final String CONFIG_FILE = null;
	private JPanel container;
    private CardLayout cards;
    private JPanel layeredPane; 
    private CardLayout layeredCards;
    private JTextField textField;
    private JTextField textField_1;
    
    private JTextField textField_2;
    private JTextField textField_3;
   
    private JTable table;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_4;
    private JTextField textField_7;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;

    public admpfp(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
    }
    /**
     * @wbp.parser.entryPoint
     */
    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(200, 50, 999, 750);
        panel.setBackground(new Color(237, 223, 201));
        
        
        String[] columnNames = {"Name", "1", "2", "3", "4"};

               

               DefaultTableModel model = new DefaultTableModel( columnNames, 0);
               
               textField_5 = new JTextField();
               textField_5.setBounds(94, 501, 156, 31);
               panel.add(textField_5);
               textField_5.setColumns(10);
               
               textField_6 = new JTextField();
               textField_6.setBounds(94, 551, 156, 31);
               panel.add(textField_6);
               textField_6.setColumns(10);
               
               JLabel lblNewLabel_1 = new JLabel("Name");
               lblNewLabel_1.setBounds(21, 500, 49, 33);
               panel.add(lblNewLabel_1);
               
               textField_4 = new JTextField();
               textField_4.setBounds(94, 602, 156, 31);
               panel.add(textField_4);
               textField_4.setColumns(10);
               
               JLabel lblNewLabel_2 = new JLabel("ID");
               lblNewLabel_2.setBounds(21, 555, 40, 23);
               panel.add(lblNewLabel_2);
               
               JLabel lblNewLabel_3 = new JLabel("Phone Number");
               lblNewLabel_3.setBounds(5, 606, 79, 23);
               panel.add(lblNewLabel_3);
               
               JLabel lblNewLabel_4 = new JLabel("State");
               lblNewLabel_4.setBounds(291, 469, 92, 23);
               panel.add(lblNewLabel_4);
               
               JCheckBox chckbxNewCheckBox = new JCheckBox("Active");
               chckbxNewCheckBox.setBounds(301, 551, 79, 31);
               panel.add(chckbxNewCheckBox);
               
               JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Not Active");
               chckbxNewCheckBox_1.setBounds(301, 501, 79, 31);
               panel.add(chckbxNewCheckBox_1);
               
            // Create a JLabel for the profile picture
               JLabel lblNewLabel = new JLabel();
               lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
               lblNewLabel.setBackground(new Color(255, 255, 255));
               lblNewLabel.setBounds(359, 247, 228, 189);
               lblNewLabel.setHorizontalAlignment(JLabel.CENTER); // Center the content
               lblNewLabel.setOpaque(true); // Make the background visible
               panel.add(lblNewLabel);

//               // Load the initial image (smm.jpg) or the last saved image
//               String savedImagePath = loadImagePath(); // Load the saved image path
//               if (savedImagePath != null && !savedImagePath.isEmpty()) {
//                   setImageToLabel(lblNewLabel, savedImagePath); // Set the saved image
//               } else {
//                   // If no saved image, load the default image (smm.jpg)
//                   setImageToLabel(lblNewLabel, "smm.jpg"); // Replace with the path to your initial image
//               }

               // Create a JButton to change the image
               JButton btnNewButton_3 = new JButton("Edit");
               btnNewButton_3.setBounds(426, 452, 85, 21);
               panel.add(btnNewButton_3);

               // Add an ActionListener to the button
               btnNewButton_3.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       // Open a file chooser dialog
                       JFileChooser fileChooser = new JFileChooser();
                       fileChooser.setDialogTitle("Select an Image"); // Set the dialog title

                       // Add a file filter to restrict to image files
                       fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

                       int result = fileChooser.showOpenDialog(panel); // Show the file chooser

//                       // Check if the user selected a file
//                       if (result == JFileChooser.APPROVE_OPTION) {
//                           File selectedFile = fileChooser.getSelectedFile(); // Get the selected file
//                           String imagePath = selectedFile.getAbsolutePath(); // Get the file path
//
//                           try {
//                               // Set the new image to the JLabel
//                               setImageToLabel(lblNewLabel, imagePath);
//
//                               // Save the selected image path to the config file
//                               saveImagePath(imagePath);
//                           } catch (Exception ex) {
//                               JOptionPane.showMessageDialog(panel, "Invalid image file!", "Error", JOptionPane.ERROR_MESSAGE);
//                           }
//                       }
                   }
               });
               
               
               String[] shiftColumns = {"Shift ID", "Date", "Start Time", "End Time", "Status"};
               String[][] shiftData = {
                   {"1", "2025-02-14", "08:00", "16:00", "Completed"},
                   {"2", "2025-02-15", "09:00", "17:00", "Scheduled"},
                   {"3", "2025-02-16", "10:00", "18:00", "Pending"}
               };
               
               DefaultTableModel shiftModel = new DefaultTableModel(shiftData, shiftColumns);

               String[] orderColumns = {"Order ID", "Customer Name", "Item", "Quantity", "Total"};
               String[][] orderData = {
                   {"101", "John Doe", "Pizza", "2", "$20.00"},
                   {"102", "Jane Smith", "Pasta", "1", "$12.00"},
                   {"103", "Tom Lee", "Salad", "3", "$15.00"}
               };
               
               DefaultTableModel orderModel = new DefaultTableModel(orderData, orderColumns);

               JLabel lblNewLabel_61 = new JLabel("Position");
               lblNewLabel_61.setBounds(21, 444, 79, 36);
               panel.add(lblNewLabel_61);

               textField_7 = new JTextField();
               textField_7.setBounds(94, 447, 156, 31);
               panel.add(textField_7);
               textField_7.setColumns(10);
               
               JButton btnNewButton = new JButton("Change");
               btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
               btnNewButton.setBounds(535, 620, 92, 23);
               panel.add(btnNewButton);
               
               JButton btnNewButton_1 = new JButton("Forgot?");
               btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
               btnNewButton_1.setBounds(665, 620, 100, 23);
               panel.add(btnNewButton_1);
               
               JButton btnNewButton_2 = new JButton("<--Dashboard");
               btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
               btnNewButton_2.addActionListener(e -> cards.show(container, "admindashboard"));
               btnNewButton_2.setBounds(847, 641, 107, 23);
               panel.add(btnNewButton_2);
               
            // Create a JLabel
               JLabel imageLabel = new JLabel();

               // Set the bounds for the JLabel (x, y, width, height)
               imageLabel.setBounds(0, 0, 999, 408); // Adjusted bounds to match the resized image

               // Load the image
               ImageIcon icon = new ImageIcon(log.class.getResource("/img/sm.jpg")); // Replace with your image path
               Image image = icon.getImage(); // Get the image from the icon

               // Resize the image to 800x300
               Image resizedImage = image.getScaledInstance(1000, 346, Image.SCALE_SMOOTH);
               ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create a new ImageIcon with the resized image

               // Set the resized image to the JLabel
               imageLabel.setIcon(resizedIcon);

               // Add the JLabel to the panel
               panel.add(imageLabel);
               
               JButton btnNewButton_4 = new JButton("Log Out");
               btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
               btnNewButton_4.setBounds(847, 686, 107, 21);
               btnNewButton_4.addActionListener(e -> cards.show(container, "log"));
               panel.add(btnNewButton_4);
               
               JButton btnNewButton_5 = new JButton("Edit");
               btnNewButton_5.setBounds(41, 669, 85, 21);
               panel.add(btnNewButton_5);
               
               JButton btnNewButton_6 = new JButton("Save");
               btnNewButton_6.setBounds(162, 669, 85, 21);
               panel.add(btnNewButton_6);
               
               JLabel lblNewLabel_6 = new JLabel("Password Settings");
               lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
               lblNewLabel_6.setBounds(535, 463, 148, 31);
               panel.add(lblNewLabel_6);
               
               passwordField = new JPasswordField();
               passwordField.setBounds(618, 526, 174, 23);
               panel.add(passwordField);
               
               passwordField_1 = new JPasswordField();
               passwordField_1.setBounds(618, 559, 174, 23);
               panel.add(passwordField_1);
               
               JLabel lblNewLabel_7 = new JLabel("Old Password");
               lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
               lblNewLabel_7.setBounds(501, 525, 107, 23);
               panel.add(lblNewLabel_7);
               
               JLabel lblNewLabel_8 = new JLabel("New Password");
               lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
               lblNewLabel_8.setBounds(501, 558, 79, 23);
               panel.add(lblNewLabel_8);
               
               JSeparator separator = new JSeparator();
               separator.setBounds(21, 411, 335, 23);
               panel.add(separator);
               
               JSeparator separator_1 = new JSeparator();
               separator_1.setOrientation(SwingConstants.VERTICAL);
               separator_1.setBounds(473, 489, 13, 251);
               panel.add(separator_1);
               
               JSeparator separator_3 = new JSeparator();
               separator_3.setBounds(591, 411, 378, 16);
               panel.add(separator_3);
			
               return panel;
               }
               
    
//
//    private static void setImageToLabel(JLabel label, String imagePath) {
//        ImageIcon icon = new ImageIcon(imagePath); // Load the image
//        Image image = icon.getImage(); // Get the image from the icon
//
//        // Resize the image to fit the JLabel
//        Image resizedImage = image.getScaledInstance(228, 189, Image.SCALE_SMOOTH);
//        ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create a new ImageIcon with the resized image
//
//        // Set the image to the JLabel
//        label.setIcon(resizedIcon);
//    }
//
//    // Method to save the image path to a file
//    private static void saveImagePath(String imagePath) {
//        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
//            writer.write(imagePath); // Write the image path to the file
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to load the saved image path from the file
//    private static String loadImagePath() {
//        File configFile = new File(CONFIG_FILE);
//        if (!configFile.exists()) {
//            return null; // Return null if the file does not exist
//        }
//        try {
//            return new String(Files.readAllBytes(Paths.get(CONFIG_FILE))); // Read the image path from the file
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null; // Return null if there's an error reading the file
//        }
//    }
}