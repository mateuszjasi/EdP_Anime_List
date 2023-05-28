package org.example.render;

import static org.example.constants.ApiTableColumns.idColumnID;
import static org.example.constants.Colors.colorLightGray;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import lombok.RequiredArgsConstructor;
import org.example.panel.BodyPanel.BodyPanelController;

@RequiredArgsConstructor
public class AddedAnimeRender extends DefaultTableCellRenderer {
    private final BodyPanelController bodyPanelController;

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        int id = Integer.parseInt((String) table.getValueAt(row, idColumnID));
        if (bodyPanelController.getMyAnimeListIds().contains(id)) {
            component.setBackground(colorLightGray);
        } else {
            component.setBackground(table.getBackground());
        }
        return component;
    }
}
