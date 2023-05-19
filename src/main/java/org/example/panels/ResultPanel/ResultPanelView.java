package org.example.panels.ResultPanel;

import static org.example.constants.AnimeSearchWindowResolutions.animeImageHeight;
import static org.example.constants.AnimeSearchWindowResolutions.animeImageWidth;
import static org.example.constants.AnimeSearchWindowTableColumns.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.example.panels.BodyPanel.BodyPanelView;
import org.example.utility.ImageRenderer;
import org.example.utility.TooltipTableCellRenderer;

public class ResultPanelView extends JPanel {
    private DefaultTableModel tableModel;
    private JTable resultTable;
    private JScrollPane scrollPane;
    private final BodyPanelView parent;

    public ResultPanelView(BodyPanelView parent) {
        this.parent = parent;
        initPanel();
        initScrollPaneTable();
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initPanel() {
        setPreferredSize(new Dimension(parent.getWidth(), 785));
        setLayout(new BorderLayout());
    }

    private void initScrollPaneTable() {
        tableModel = new DefaultTableModel(null, tableHeaders) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultTable = new JTable(tableModel);
        resultTable.getColumnModel().getColumn(idColumnID).setMinWidth(0);
        resultTable.getColumnModel().getColumn(idColumnID).setMaxWidth(0);
        resultTable.getColumnModel().getColumn(idColumnID).setWidth(0);
        resultTable.getColumnModel().getColumn(imageColumnID).setCellRenderer(new ImageRenderer());
        resultTable.getColumnModel().getColumn(imageColumnID).setMinWidth(animeImageWidth);
        resultTable.getColumnModel().getColumn(imageColumnID).setMaxWidth(animeImageWidth);
        resultTable.getColumnModel().getColumn(imageColumnID).setWidth(animeImageWidth);
        resultTable.getColumnModel().getColumn(titleColumnID).setCellRenderer(new TooltipTableCellRenderer());
        resultTable.getColumnModel().getColumn(titleColumnID).setMinWidth(600);
        resultTable.getColumnModel().getColumn(statusColumnID).setMinWidth(60);
        resultTable.getColumnModel().getColumn(numEpisodesColumnID).setMinWidth(30);
        resultTable.getColumnModel().getColumn(meanColumnID).setMinWidth(30);
        resultTable.setRowHeight(animeImageHeight);
        resultTable.getTableHeader().setResizingAllowed(false);
        resultTable.getTableHeader().setReorderingAllowed(false);
        resultTable.setRowSelectionAllowed(true);
        resultTable.setColumnSelectionAllowed(false);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(resultTable);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getResultTable() {
        return resultTable;
    }
}
