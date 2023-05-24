package org.example.panels.ResultPanel;

import static org.example.constants.Resolutions.*;
import static org.example.constants.TableColumns.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import org.example.panels.BodyPanel.BodyPanelView;
import org.example.utility.ImageRenderer;
import org.example.utility.TooltipTableCellRenderer;

@Getter
public abstract class ResultPanelModel extends JPanel {
    protected ResultPanelController resultPanelController;
    protected DefaultTableModel tableModel;
    protected JTable resultTable;
    protected JScrollPane scrollTablePane;
    protected BodyPanelView bodyPanel;

    protected void initResultPanel() {
        setPreferredSize(new Dimension(bodyPanel.getWidth(), bodyPanel.getHeight() - userPanelHeight));
        setLayout(new BorderLayout());
    }

    protected void initScrollPaneTable() {
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
        resultTable.getSelectionModel().addListSelectionListener((ListSelectionListener) this);
        scrollTablePane = new JScrollPane(resultTable);
    }
}
