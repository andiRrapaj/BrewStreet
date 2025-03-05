package Maini;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);

        if (value instanceof byte[]) {
            try {
                byte[] imageBytes = (byte[]) value;
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage img = ImageIO.read(bis);
                if (img != null) {
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                    label.setIcon(icon);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return label;
    }
}
