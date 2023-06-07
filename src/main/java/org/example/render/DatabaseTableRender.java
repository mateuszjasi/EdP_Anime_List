package org.example.render;

import static org.example.constants.Colors.*;
import static org.example.constants.DatabaseTableColumns.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import org.example.model.Status;

public class DatabaseTableRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ((DefaultTableCellRenderer) component).setHorizontalAlignment(SwingConstants.CENTER);
        Status status = Status.fromString((String) table.getValueAt(row, statusColumnID));
        if (isSelected) component.setBackground(table.getSelectionBackground());
        else if (status == Status.watching) component.setBackground(colorWatching);
        else if (status == Status.on_hold) component.setBackground(colorOnHold);
        else if (status == Status.dropped) component.setBackground(colorDropped);
        else if (status == Status.plan_to_watch) component.setBackground(colorPlanToWatch);
        else component.setBackground(colorCompleted);
        if (column == titleColumnID) setFont(getFont().deriveFont(Font.BOLD, 30));
        else if (column == scoreColumnID) setFont(getFont().deriveFont(Font.BOLD, 25));
        else setFont(getFont().deriveFont(Font.PLAIN, 20));
        if (table.getColumnModel().getColumn(column).getWidth()
                < getPreferredSize().getWidth()) setToolTipText(value.toString());
        else setToolTipText(null);
        return component;
    }
}
