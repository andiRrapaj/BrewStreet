package rezervime;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;

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
        
        
//      //--------------------------------------------------------------------------------------------------------------------------------------------  

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
      
//-------------------------------------------------------------------------------------------------------------------------------------------------------
      
      
      JFrame edd = new JFrame();
      edd.getContentPane().setBackground(new Color(143, 188, 143));
		edd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		edd.setTitle("Shto Rezervim");
		edd.setLocation(400, 150);
		edd.setSize(600,500);
		edd.getContentPane().setLayout(null);
		
		
		
		
    JDateChooser dataEdit = new JDateChooser();
    dataEdit.setBounds(99, 159, 167, 28);
    edd.getContentPane().add(dataEdit);
    
    JTextField nameField = new JTextField();
    nameField.setBounds(99, 68, 167, 34);
    edd.getContentPane().add(nameField);
    nameField.setColumns(10);
    
    JTextField telEdit = new JTextField();
    telEdit.setColumns(10);
    telEdit.setBounds(400, 68, 176, 34);
    edd.getContentPane().add(telEdit);
    
    JTextArea detajeFieldEDT =new JTextArea();
    detajeFieldEDT.setLineWrap(true);
    detajeFieldEDT.setWrapStyleWord(true);

    JScrollPane scrollPaneEdt = new JScrollPane(detajeFieldEDT);
    scrollPaneEdt.setBounds(409, 159, 167, 173);

    edd.getContentPane().add(scrollPaneEdt);

    
    JLabel em = new JLabel("Emri");
    em.setFont(new Font("Times New Roman", Font.ITALIC, 21));
    em.setBounds(25, 68, 64, 34);
    edd.getContentPane().add(em);
    
    JLabel lblDat = new JLabel("Data");
    lblDat.setFont(new Font("Times New Roman", Font.ITALIC, 21));
    lblDat.setBounds(25, 159, 64, 34);
    edd.getContentPane().add(lblDat);
    
    JLabel lblNr = new JLabel("Nr. Tel");
    lblNr.setFont(new Font("Times New Roman", Font.ITALIC, 21));
    lblNr.setBounds(311, 68, 79, 34);
    edd.getContentPane().add(lblNr);
    
    JLabel lblDet = new JLabel("Detaje");
    lblDet.setFont(new Font("Times New Roman", Font.ITALIC, 21));
    lblDet.setBounds(335, 159, 64, 34);
    edd.getContentPane().add(lblDet);
    
    JButton updateBtn = new JButton("Update");
   
 
    updateBtn.setBackground(Color.WHITE);
    updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
    updateBtn.setBounds(85, 380, 121, 34);
    edd.getContentPane().add(updateBtn);
    
    JButton deleteBtn = new JButton("Delete");
    deleteBtn.setBackground(Color.WHITE);
    deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
    deleteBtn.setBounds(356, 380, 121, 34);
    edd.getContentPane().add(deleteBtn);
    
    JLabel lblId = new JLabel("ID");
    lblId.setFont(new Font("Times New Roman", Font.ITALIC, 21));
    lblId.setBounds(25, 233, 64, 34);
    edd.getContentPane().add(lblId);
    
    JTextField idField = new JTextField();
    idField.setColumns(10);
    idField.setBounds(99, 233, 33, 34);
    edd.getContentPane().add(idField);
      
      
      
      
      
        JButton btnNewButton_1 = new JButton("Shto");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 try {
					fr.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButton_1.setBackground(Color.WHITE);
        btnNewButton_1.setBounds(757, 205, 120, 109);
        panel.add(btnNewButton_1);
        JButton refreshBtn = new JButton(".............");
        refreshBtn.setBounds(637, 716, 22, 21);
        panel.add(refreshBtn);
        
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