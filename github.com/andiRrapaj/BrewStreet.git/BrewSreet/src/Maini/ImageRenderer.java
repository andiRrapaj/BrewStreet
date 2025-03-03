package Maini;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.Blob;
import java.sql.SQLException;

public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Blob) {
            Blob blob = (Blob) value;
            try {
                byte[] imgBytes = blob.getBytes(1, (int) blob.length());
                ImageIcon imageIcon = new ImageIcon(imgBytes);
                JLabel label = new JLabel(imageIcon);
                return label;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
