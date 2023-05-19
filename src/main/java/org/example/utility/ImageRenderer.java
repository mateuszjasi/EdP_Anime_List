package org.example.utility;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {
    JLabel label = new JLabel();

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            label.setIcon((ImageIcon) value);
            label.setHorizontalAlignment(JLabel.CENTER);
        }
        return label;
    }
}
