package rezervime;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import Maini.DbConn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//
public class ReservationForms {
    JFrame fr, edd;
    JTextField emriAdd, telAdd, nameField, telEdit, idField;
    JDateChooser dataAdd, dataEdit;
    JTextArea detajeAdd, detajeFieldEDT;
    private JLabel lblData;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel;
    private JLabel lblData_1;
    private JLabel lblOra;
    private JLabel lblNrTel;
    private JLabel lblDetaj;
    private JFormattedTextField timeField;
    private  JFormattedTextField timeFieldEdt;
   

    public ReservationForms() {
        createAddReservationFrame();
        createEditReservationFrame();
    }

    private void createAddReservationFrame() {
        fr = new JFrame();
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setTitle("Shto Rezervim");
        fr.setLocation(400, 150);
        fr.setSize(600, 500);
        fr.getContentPane().setLayout(null);

        dataAdd = new JDateChooser();
        dataAdd.setBounds(99, 159, 167, 28);
        fr.getContentPane().add(dataAdd);

        emriAdd = new JTextField();
        emriAdd.setBounds(99, 90, 167, 34);
        fr.getContentPane().add(emriAdd);

        telAdd = new JTextField();
        telAdd.setBounds(400, 90, 176, 34);
        fr.getContentPane().add(telAdd);

        detajeAdd = new JTextArea();
        detajeAdd.setFont(new Font("Cambria Math", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(detajeAdd);
        scrollPane.setBounds(399, 150, 180, 173);
        fr.getContentPane().add(scrollPane);

        JButton applyBtn = new JButton("Apply");
        applyBtn.setBounds(85, 380, 121, 34);
        applyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String emri = emriAdd.getText().trim();
                String tel = telAdd.getText().trim();
                String ora = timeField.getText().trim();
                String detaje = detajeAdd.getText().trim();
                java.util.Date data = dataAdd.getDate();

                // Validate required fields
                if (emri.isEmpty() || tel.isEmpty() || ora.isEmpty() || data == null) {
                    JOptionPane.showMessageDialog(fr, "All fields except 'Detaje' are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Convert date to SQL format
                java.sql.Date sqlDate = new java.sql.Date(data.getTime());

                try (Connection conn = DbConn.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement("INSERT INTO reservations (name, date, time, phone, details) VALUES (?, ?, ?, ?, ?)")) {

                    pstmt.setString(1, emri);
                    pstmt.setDate(2, sqlDate);
                    pstmt.setString(3, ora);
                    pstmt.setString(4, tel);
                    pstmt.setString(5, detaje.isEmpty() ? null : detaje); // Set NULL if empty

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(fr, "Reservation added successfully!");
                        fr.dispose(); // Close the frame after insertion
                    } else {
                        JOptionPane.showMessageDialog(fr, "Failed to add reservation.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(fr, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        fr.getContentPane().add(applyBtn);
        
      try {
      MaskFormatter timeFormatter = new MaskFormatter("##:##");
      timeFormatter.setPlaceholderCharacter('_'); // Shows underscores for missing input
      timeField = new JFormattedTextField(timeFormatter);
      timeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
      timeField.setHorizontalAlignment(SwingConstants.CENTER);
      timeField.setText(" :  ");
      timeField.setBounds(99, 223, 66, 34);
      timeField.setColumns(5);

      fr.getContentPane().add(new JLabel("Enter Time (HH:MM):"));
      fr.getContentPane().add(timeField);
  } catch (Exception e) {
      e.printStackTrace();
  }

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(356, 380, 121, 34);
        fr.getContentPane().add(btnCancel);
        
        lblNewLabel = new JLabel("Emri");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
        lblNewLabel.setBounds(23, 90, 66, 34);
        fr.getContentPane().add(lblNewLabel);
        
        lblData_1 = new JLabel("Data");
        lblData_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
        lblData_1.setBounds(23, 153, 66, 34);
        fr.getContentPane().add(lblData_1);
        
        lblOra = new JLabel("Ora");
        lblOra.setFont(new Font("Tahoma", Font.PLAIN, 21));
        lblOra.setBounds(23, 221, 66, 34);
        fr.getContentPane().add(lblOra);
        
        lblNrTel = new JLabel("Nr Tel");
        lblNrTel.setFont(new Font("Tahoma", Font.PLAIN, 21));
        lblNrTel.setBounds(324, 90, 66, 34);
        fr.getContentPane().add(lblNrTel);
        
        lblDetaj = new JLabel("Detaje");
        lblDetaj.setFont(new Font("Tahoma", Font.PLAIN, 21));
        lblDetaj.setBounds(324, 153, 66, 34);
        fr.getContentPane().add(lblDetaj);
    }

    private void createEditReservationFrame() {
        edd = new JFrame();
        edd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        edd.setTitle("Ndrysho Rezervimin");
        edd.setLocation(400, 150);
        edd.setSize(600, 500);
        edd.getContentPane().setLayout(null);
        
        // Add a formatted text field for time input
        try {
            MaskFormatter timeFormatter = new MaskFormatter("##:##");
            timeFormatter.setPlaceholderCharacter('_'); // Shows underscores for missing input
            timeFieldEdt = new JFormattedTextField(timeFormatter);
            timeFieldEdt.setHorizontalAlignment(SwingConstants.CENTER);
            timeFieldEdt.setText(" :  ");
            timeFieldEdt.setBounds(99, 220, 66, 28);
            timeFieldEdt.setColumns(5);
            edd.getContentPane().add(new JLabel("Enter Time (HH:MM):"));
            edd.getContentPane().add(timeFieldEdt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add Date Chooser
        dataEdit = new JDateChooser();
        dataEdit.setBounds(99, 159, 167, 28);
        edd.getContentPane().add(dataEdit);

        // Add Name field
        nameField = new JTextField();
        nameField.setBounds(99, 83, 167, 34);
        edd.getContentPane().add(nameField);

        // Add Phone field
        telEdit = new JTextField();
        telEdit.setBounds(400, 83, 176, 34);
        edd.getContentPane().add(telEdit);

        // Add Details text area
        detajeFieldEDT = new JTextArea();
        JScrollPane scrollPaneEdt = new JScrollPane(detajeFieldEDT);
        scrollPaneEdt.setBounds(409, 159, 167, 173);
        edd.getContentPane().add(scrollPaneEdt);

        // Add ID field (hidden or readonly)
        idField = new JTextField();
        idField.setBounds(99, 281, 33, 34);
        edd.getContentPane().add(idField);

        // Create Update button
        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(85, 380, 121, 34);
        edd.getContentPane().add(updateBtn);

        // Create Delete button
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(356, 380, 121, 34);
        edd.getContentPane().add(deleteBtn);
        
        // Labels for fields
        JLabel lblNewLabel = new JLabel("Emri");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(20, 83, 62, 34);
        edd.getContentPane().add(lblNewLabel);

        lblData = new JLabel("Data");
        lblData.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblData.setBounds(20, 153, 62, 34);
        edd.getContentPane().add(lblData);

        lblNewLabel_2 = new JLabel("Ora");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(20, 214, 62, 34);
        edd.getContentPane().add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("Nr Tel");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(328, 83, 62, 34);
        edd.getContentPane().add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("Detaje");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_4.setBounds(328, 159, 62, 34);
        edd.getContentPane().add(lblNewLabel_4);

        // Add ActionListener for Update Button
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                java.util.Date date = dataEdit.getDate();
                String time = timeFieldEdt.getText();
                String phone = telEdit.getText();
                String details = detajeFieldEDT.getText();

                // Validate input (optional)
                if (name.isEmpty() || phone.isEmpty() || details.isEmpty() || date == null || time.isEmpty()) {
                    JOptionPane.showMessageDialog(edd, "Please fill in all fields.");
                    return;
                }

                // Convert date to String format (if necessary)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = sdf.format(date);

                // Update the reservation in the database
                try (Connection conn = DbConn.getConnection()) {
                    String updateQuery = "UPDATE reservations SET name = ?, date = ?, time = ?, phone = ?, details = ? WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                        pstmt.setString(1, name);
                        pstmt.setString(2, dateStr);
                        pstmt.setString(3, time);
                        pstmt.setString(4, phone);
                        pstmt.setString(5, details);
                        pstmt.setInt(6, Integer.parseInt(id));
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(edd, "Reservation updated successfully.");
                        edd.dispose();  // Close the frame after update
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(edd, "Error updating reservation: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        // Add ActionListener for Delete Button
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();

                // Validate ID field
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(edd, "ID is missing, unable to delete.");
                    return;
                }

                // Ask for confirmation
                int confirm = JOptionPane.showConfirmDialog(edd, "Are you sure you want to delete this reservation?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try (Connection conn = DbConn.getConnection()) {
                        String deleteQuery = "DELETE FROM reservations WHERE id = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                            pstmt.setInt(1, Integer.parseInt(id));
                            pstmt.executeUpdate();
                            JOptionPane.showMessageDialog(edd, "Reservation deleted successfully.");
                            edd.dispose();  // Close the frame after delete
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(edd, "Error deleting reservation: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });
    }


    public void showAddReservationForm() {
        fr.setVisible(true);
    }

    public void showEditReservationForm(String id, String name, String date, String time, String phone, String details) {
        idField.setText(id);
        nameField.setText(name);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            dataEdit.setDate(format.parse(date));  // Assuming date is in "yyyy-MM-dd" format
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        timeFieldEdt.setText(time);
        telEdit.setText(phone);
        detajeFieldEDT.setText(details);
        edd.setVisible(true);
    }

}
