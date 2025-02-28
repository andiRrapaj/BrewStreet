		package kamarjer;
		
		import javax.swing.*;
		import javax.swing.table.DefaultTableModel;

import Maini.log;

import java.sql.Blob;
		import java.awt.*;
		import java.awt.event.ActionListener;
		import java.awt.event.MouseAdapter;
		import java.awt.event.MouseEvent;
		import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;
		import java.awt.event.ActionEvent;
		import kamarjer.kamarjerdashboard;
		import java.awt.*;
		public class tables1 {
		    private JPanel panel;
		    private static JTable table;
		    private static JTextField txtSearch;
		    private static JPanel imagePanel;  
		    private kamarjerframe kamarjerFrame;
		    public tables1(JPanel container, CardLayout cards) {
		        panel = new JPanel();
		        panel.setBounds(0, 0, 1000, 800);
		        panel.setBackground(new Color(247, 244, 215));
		        panel.setLayout(null);
		
		        kamarjerframe kamarjerFrame = new kamarjerframe(container, cards, 1);
		        container.add(kamarjerFrame.getPanel(), "kamarjerframe");
		     
	
		        JButton button1 = new JButton();
		        button1.setBounds(805, 108, 85, 86);
		        button1.setBackground(new Color(255, 200, 100));
		        button1.setForeground(Color.BLACK);
		        button1.setFont(new Font("Arial", Font.BOLD, 12));
		        button1.setOpaque(false);
		        button1.setContentAreaFilled(false);
		       // button1.setBorderPainted(false);
		        button1.addActionListener(e -> {
		            kamarjerFrame.setButtonId(1);
		            cards.show(container, "kamarjerframe");
		        });
		        panel.add(button1);
		
		         
		        JButton button2 = new JButton();
		        button2.setBounds(439, 331, 79, 134);
		        button2.setBackground(new Color(255, 200, 100));
		        button2.setForeground(Color.BLACK);
		        button2.setFont(new Font("Arial", Font.BOLD, 12));
		        button2.setOpaque(false);
		        button2.setContentAreaFilled(false);
		        button2.setBorderPainted(false);
		        button2.addActionListener(e -> {
		            kamarjerFrame.setButtonId(2);  
		            cards.show(container, "kamarjerframe");
		        });
		        panel.add(button2);
		
		         
		        JButton button3 = new JButton();
		        button3.setBounds(747, 608, 107, 86);
		        button3.setBackground(new Color(255, 200, 100));
		        button3.setForeground(Color.BLACK);
		        button3.setFont(new Font("Arial", Font.BOLD, 12));
		        button3.setOpaque(false);
		        button3.setContentAreaFilled(false);
		        button3.setBorderPainted(false);
		        button3.addActionListener(e -> {
		            kamarjerFrame.setButtonId(3);  
		            cards.show(container, "kamarjerframe");
		        });
		        panel.add(button3);
		
		        
		        JButton button4 = new JButton();
		        button4.setBounds(80, 636, 101, 66);
		        button4.setBackground(new Color(255, 200, 100));
		        button4.setForeground(Color.BLACK);
		        button4.setFont(new Font("Arial", Font.BOLD, 12));
		        button4.setOpaque(false);
		        button4.setContentAreaFilled(false);
		        button4.setBorderPainted(false);
		        button4.addActionListener(e -> {
		            kamarjerFrame.setButtonId(4);  
		            cards.show(container, "kamarjerframe");
		        });
		        panel.add(button4);
		
		         
		        JButton button5 = new JButton();
		        button5.setBounds(103, 94, 55, 62);
		        button5.setBackground(new Color(255, 200, 100));
		        button5.setOpaque(false);
		        button5.setContentAreaFilled(false);
		        button5.setBorderPainted(false);
		        button5.addActionListener(e -> {
		            kamarjerFrame.setButtonId(5);  
		            cards.show(container, "kamarjerframe");
		        });
		        panel.add(button5);
		
		         
		        JButton button6 = new JButton();
		        button6.setBounds(80, 275, 101, 56);
		        button6.setBackground(new Color(255, 200, 100));
		        button6.setForeground(Color.BLACK);
		        button6.setFont(new Font("Arial", Font.BOLD, 12));
		        button6.setOpaque(false);
		        button6.setContentAreaFilled(false);
		        button6.setBorderPainted(false);
		        button6.addActionListener(e -> {
		            kamarjerFrame.setButtonId(6);  
		            cards.show(container, "kamarjerframe");
		        });
		        panel.add(button6);
		
		         
		        JButton button7 = new JButton();
		        button7.setBounds(421, 108, 136, 74);
		        button7.setBackground(new Color(255, 200, 100));
		        button7.setForeground(Color.BLACK);
		        button7.setFont(new Font("Arial", Font.BOLD, 12));
		        button7.setOpaque(false);
		        button7.setContentAreaFilled(false);
		        button7.setBorderPainted(false);
		        button7.addActionListener(e -> {
		            kamarjerFrame.setButtonId(7);  
		            cards.show(container, "kamarjerframe");
		        });
		        panel.add(button7);
		
		         
		        JButton button8 = new JButton();
		        button8.setBounds(788, 349, 101, 95);
		        button8.setBackground(new Color(255, 200, 100));
		        button8.setForeground(Color.BLACK);
		        button8.setFont(new Font("Arial", Font.BOLD, 12));
		        button8.setOpaque(false);
		        button8.setContentAreaFilled(false);
		        button8.setBorderPainted(false);
		        button8.addActionListener(e -> {
		            kamarjerFrame.setButtonId(8);  
		            cards.show(container, "kamarjerframe");
		        });
		        panel.add(button8);
		
		         
		        JButton button9 = new JButton();
		        button9.setBounds(183, 459, 107, 86);
		        button9.setBackground(new Color(255, 200, 100));
		        button9.setForeground(Color.BLACK);
		        button9.setFont(new Font("Arial", Font.BOLD, 12));
		        button9.setOpaque(false);
		        button9.setContentAreaFilled(false);
		        button9.setBorderPainted(false);
		        button9.addActionListener(e -> {
		            kamarjerFrame.setButtonId(9);  
		            cards.show(container, "kamarjerframe");
		        });
		        panel.add(button9);
		
		         
		        JButton button10 = new JButton();
		        button10.setBounds(439, 608, 107, 86);
		        button10.setBackground(new Color(255, 200, 100));
		        button10.setForeground(Color.BLACK);
		        button10.setFont(new Font("Arial", Font.BOLD, 12));
		        button10.setOpaque(false);
		        button10.setContentAreaFilled(false);
		        button10.setBorderPainted(false);
		        button10.addActionListener(e -> {
		            kamarjerFrame.setButtonId(10);  
		            cards.show(container, "kamarjerframe");
		        });
		        panel.add(button10);
		
		         
		        JPanel panel_1 = new JPanel();
		        panel_1.setBackground(new Color(252, 251, 239));
		        panel_1.setBounds(0, 0, 1000, 38);
		        panel.add(panel_1);
		        panel_1.setLayout(null);
		        ImageIcon originalIcon = new ImageIcon(log.class.getResource("/img/sd.png")); // Replace with your image path

		     // Resize the image to 100x100
		     Image image = originalIcon.getImage(); // Get the image from the icon
		     Image resizedImage = image.getScaledInstance(121, 68, Image.SCALE_SMOOTH); // Resize the image
		     ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create a new ImageIcon with the resized image

		     // Create a JLabel with the resized image
		     JLabel imageLabel = new JLabel(resizedIcon);
		     imageLabel.setBounds(10, 3, 121, 28); // Set bounds for the label (x, y, width, height)

		     // Add the label to panel_1
		     panel_1.add(imageLabel);
		        
		        JButton btnProfile = new JButton("Profile");
		        btnProfile.setBackground(new Color(249, 247, 225));
		        btnProfile.setBounds(768, 10, 85, 21);
		        btnProfile.setBorderPainted(false);
		        btnProfile.addActionListener(e -> cards.show(container, "kamarjerprofil"));
		        panel_1.add(btnProfile);
		
		        JButton btnLogout = new JButton("Log-Out");
		        btnLogout.setBackground(new Color(249, 247, 225));
		        btnLogout.setBounds(879, 10, 85, 21);
		        btnLogout.setBorderPainted(false);
		        btnLogout.addActionListener(e -> cards.show(container, "loginkamarjer"));
		        panel_1.add(btnLogout);
		        
		        JButton btnNewButton = new JButton("Quick Pay");
		        btnNewButton.setBounds(647, 10, 111, 21);
		        btnNewButton.setBorderPainted(false);
		        btnNewButton.setBackground(new Color(249, 247, 225));
		        btnNewButton.addActionListener(e -> cards.show(container, "kamarjerdashboard"));
		        panel_1.add(btnNewButton);
		
		        ImageIcon icon1 = new ImageIcon(log.class.getResource("/img/tavolin.png"));
		        JLabel label1 = new JLabel(resizeImage(icon1, 250, 225));
		        label1.setBounds(720, 23, 250, 225);
		        panel.add(label1);
		
		        ImageIcon icon10 = new ImageIcon(log.class.getResource("/img/tavolin3.png"));
		        JLabel label10 = new JLabel(resizeImage(icon10, 250, 225));
		        label10.setBounds(370, 552, 250, 193);
		        panel.add(label10);
		
		        ImageIcon icon9 = new ImageIcon(log.class.getResource("/img/tavolin3.png"));
		        JLabel label9 = new JLabel(resizeImage(icon9, 250, 225));
		        label9.setBounds(114, 381, 250, 225);
		        panel.add(label9);
		
		        ImageIcon icon7 = new ImageIcon(log.class.getResource("/img/tavolin4.png"));
		        JLabel label7 = new JLabel(resizeImage(icon7, 250, 200));
		        label7.setBounds(370, 48, 250, 200);
		        panel.add(label7);
		
		        ImageIcon icon8 = new ImageIcon(log.class.getResource("/img/tavolin3.png"));
		        JLabel label8 = new JLabel(resizeImage(icon8, 250, 225));
		        label8.setBounds(720, 275, 250, 225);
		        panel.add(label8);
		
		        ImageIcon icon6 = new ImageIcon(log.class.getResource("/img/tavolin4.png"));
		        JLabel label6 = new JLabel(resizeImage(icon6, 160, 160));
		        label6.setBounds(50, 221, 160, 160);
		        panel.add(label6);
		
		        ImageIcon icon5 = new ImageIcon(log.class.getResource("/img/tavolin.png"));
		        JLabel label5 = new JLabel(resizeImage(icon5, 160, 160));
		        label5.setBounds(50, 34, 160, 160);
		        panel.add(label5);
		
		        ImageIcon icon2 = new ImageIcon(log.class.getResource("/img/tavolin2.png"));
		        JLabel label2 = new JLabel(resizeImage(icon2, 255, 225));
		        label2.setBounds(353, 289, 255, 225);
		        panel.add(label2);
		
		        ImageIcon icon4 = new ImageIcon(log.class.getResource("/img/tavolin4.png"));
		        JLabel label4 = new JLabel(resizeImage(icon4, 200, 180));
		        label4.setBounds(30, 582, 200, 180);
		        panel.add(label4);
		
		        ImageIcon icon3 = new ImageIcon(log.class.getResource("/img/tavolin3.png"));
		        JLabel label3 = new JLabel(resizeImage(icon3, 250, 225));
		        label3.setBounds(673, 540, 250, 193);
		        panel.add(label3);
		    }
		
		    
		    private static ImageIcon resizeImage(ImageIcon icon, int width, int height) {
		        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		        return new ImageIcon(img);
		    }
		
		    public JPanel getPanel() {
		        return panel;
		    }
		}