package org.example.utility;

import static org.example.constants.AnimeSearchWindowTableColumns.idColumnID;
import static org.example.constants.Colors.colorLightGray;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import org.example.panels.OptionsPanel.OptionsPanelController;

public class AddedAnimeRender extends DefaultTableCellRenderer {
    private final OptionsPanelController optionsPanelController;

    public AddedAnimeRender(OptionsPanelController optionsPanelController) {
        this.optionsPanelController = optionsPanelController;
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        int id = Integer.parseInt((String) table.getValueAt(row, idColumnID));
        if (optionsPanelController.getMyAnimeListIds().contains(id)) {
            component.setBackground(colorLightGray);
        } else {
            component.setBackground(table.getBackground());
        }
        return component;
    }
}
