package rezervime;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Maini.DbConn;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class rezervimeloged {
    private JPanel panel;
    private JPanel container;
    private CardLayout cards;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_3;
    private JTextField textField_2;
    private JTable tableRes;
    private ReservationForms reservationForms;


    public rezervimeloged(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
    
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800); 
        panel.setBackground(new Color(192, 192, 192)); 
        
        
        reservationForms = new ReservationForms();
        
        JButton refreshBtn = new JButton("");
        
        refreshBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		loadReservations();
        	}
        });
        refreshBtn.setBounds(629, 706, 30, 31);
        panel.add(refreshBtn);
   
        
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(890, 10, 100, 32);
       
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 cards.show(container, "rezervime");
            }

        });
        
        panel.add(backBtn);
        
        
//     
     
        JScrollPane tablescrollPane = new JScrollPane();
        tablescrollPane.setBounds(44, 91, 615, 646);
        panel.add(tablescrollPane);
        
        tableRes = new JTable();
        
        tablescrollPane.setViewportView(tableRes);
        tableRes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableRes.getSelectedRow();
                if (selectedRow != -1) {
                    // Get data from the selected row
                    String id = tableRes.getValueAt(selectedRow, 0).toString();
                    String name = tableRes.getValueAt(selectedRow, 1).toString();
                    String date = tableRes.getValueAt(selectedRow, 2).toString();
                    String time = tableRes.getValueAt(selectedRow, 3).toString();
                    String phone = tableRes.getValueAt(selectedRow, 4).toString();
                    String details = tableRes.getValueAt(selectedRow, 5).toString();

                    // Open the Edit Reservation form and pass data
                    reservationForms.showEditReservationForm(id, name, date, time, phone, details);
                }
            }
        });

       
        
        JButton addBtn = new JButton("Add +");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Add Reservation form
                reservationForms.showAddReservationForm();
            }
        });
        addBtn.setBounds(779, 368, 105, 105);
        panel.add(addBtn);
        
        
        
        loadReservations();
       
    }
    
    private void loadReservations() {
        try (Connection conn = DbConn.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery("SELECT id, name, date, time, phone, details FROM reservations")) {

            DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Date", "Time", "Phone", "Details"}, 0);
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getDate("date"), 
                    rs.getTime("time"), 
                    rs.getString("phone"), 
                    rs.getString("details")
                });
            }

            tableRes.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error loading reservations: " + ex.getMessage());
        }
    }

    
        public JPanel getPanel() {
            return panel;
        }
    }