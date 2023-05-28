package org.example.render;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TooltipTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public java.awt.Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        java.awt.Component component =
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (table.getColumnModel().getColumn(column).getWidth()
                < getPreferredSize().getWidth()) {
            setToolTipText(value.toString());
        } else {
            setToolTipText(null);
        }
        return component;
    }
}
