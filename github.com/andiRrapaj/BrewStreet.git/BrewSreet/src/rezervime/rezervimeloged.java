package rezervime;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;



import java.awt.*;
import java.awt.event.*;


public class rezervimeloged {
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_3;
    private JTextField textField_2;
    private JTable tableRes;


    public rezervimeloged(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
    
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(192, 192, 192)); 
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.setBounds(890, 10, 100, 32);
       
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "rezervime");
            }

        });
        
        panel.add(btnNewButton);
        
        
      //--------------------------------------------------------------------------------------------------------------------------------------------  

        JFrame fr = new JFrame();
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fr.setTitle("Shto Rezervim");
		fr.setLocation(400, 150);
		fr.setSize(600,500);
		fr.getContentPane().setLayout(null);
		
		
		
		
      JDateChooser dataAdd = new JDateChooser();
      dataAdd.setBounds(99, 159, 167, 28);
      fr.getContentPane().add(dataAdd);
      
      JTextField emriAdd = new JTextField();
      emriAdd.setBounds(99, 68, 167, 34);
      fr.getContentPane().add(emriAdd);
      emriAdd.setColumns(10);
      
      JTextField telAdd = new JTextField();
      telAdd.setColumns(10);
      telAdd.setBounds(400, 68, 176, 34);
      fr.getContentPane().add(telAdd);
      
      JTextArea detajeAdd = new JTextArea();
      detajeAdd.setLineWrap(true);
      detajeAdd.setWrapStyleWord(true);

      JScrollPane scrollPane = new JScrollPane(detajeAdd);
      scrollPane.setBounds(409, 159, 167, 173);

      fr.getContentPane().add(scrollPane);

      
      JLabel lblNewLabel = new JLabel("Emri");
      lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 21));
      lblNewLabel.setBounds(25, 68, 64, 34);
      fr.getContentPane().add(lblNewLabel);
      
      JLabel lblData = new JLabel("Data");
      lblData.setFont(new Font("Times New Roman", Font.ITALIC, 21));
      lblData.setBounds(25, 159, 64, 34);
      fr.getContentPane().add(lblData);
      
      JLabel lblNrTel = new JLabel("Nr. Tel");
      lblNrTel.setFont(new Font("Times New Roman", Font.ITALIC, 21));
      lblNrTel.setBounds(311, 68, 79, 34);
      fr.getContentPane().add(lblNrTel);
      
      JLabel lblDetaje = new JLabel("Detaje");
      lblDetaje.setFont(new Font("Times New Roman", Font.ITALIC, 21));
      lblDetaje.setBounds(335, 159, 64, 34);
      fr.getContentPane().add(lblDetaje);
      
      JButton applyBtn = new JButton("Apply");
      applyBtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      	}
      });
      applyBtn.setBackground(Color.WHITE);
      applyBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
      applyBtn.setBounds(85, 380, 121, 34);
      fr.getContentPane().add(applyBtn);
      
      JButton btnCancel = new JButton("Cancel");
      btnCancel.setBackground(Color.WHITE);
      btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
      btnCancel.setBounds(356, 380, 121, 34);
      fr.getContentPane().add(btnCancel); 
      
      
      
        JButton btnNewButton_1 = new JButton("Shto");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 fr.setVisible(true);
        	}
        });
        btnNewButton_1.setBackground(Color.WHITE);
        btnNewButton_1.setBounds(757, 205, 120, 109);
        panel.add(btnNewButton_1);
        JButton btnNewButton_2 = new JButton("New button");
        btnNewButton_2.setBounds(637, 716, 22, 21);
        panel.add(btnNewButton_2);
        
        tableRes = new JTable();
        tableRes.setBounds(44, 91, 615, 646);
        panel.add(tableRes);
        
       
        
        
       
        JButton btnNewButton_1_1 = new JButton("Shiko Rezervimin");
        btnNewButton_1_1.setBackground(Color.WHITE);
        btnNewButton_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
               
        	}
        });
        btnNewButton_1_1.setBounds(757, 434, 120, 109);
        panel.add(btnNewButton_1_1);
        

       
    }
    
        public JPanel getPanel() {
            return panel;
        }
    }