package org.example.render;

import static org.example.constants.ApiTableColumns.*;
import static org.example.constants.Colors.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import lombok.RequiredArgsConstructor;
import org.example.panel.BodyPanel.BodyPanelController;

@RequiredArgsConstructor
public class ApiTableRender extends DefaultTableCellRenderer {
    private final BodyPanelController bodyPanelController;

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ((DefaultTableCellRenderer) component).setHorizontalAlignment(SwingConstants.CENTER);
        int id = Integer.parseInt((String) table.getValueAt(row, idColumnID));
        if (isSelected) component.setBackground(table.getSelectionBackground());
        else if (bodyPanelController.getMyAnimeListIds().contains(id)) component.setBackground(colorLightGray);
        else component.setBackground(table.getBackground());
        if (column == titleColumnID) setFont(getFont().deriveFont(Font.BOLD, 30));
        else if (column == meanColumnID) setFont(getFont().deriveFont(Font.BOLD, 25));
        else setFont(getFont().deriveFont(Font.PLAIN, 20));
        if (table.getColumnModel().getColumn(column).getWidth()
                < getPreferredSize().getWidth()) setToolTipText(value.toString());
        else setToolTipText(null);
        return component;
    }
}
