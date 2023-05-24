package org.example.panels.ResultPanel;

import static org.example.constants.ApiTableColumns.*;
import static org.example.constants.DatabaseTableColumns.*;
import static org.example.constants.Resolutions.*;

import java.awt.*;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import org.example.panels.BodyPanel.BodyPanelView;
import org.example.utility.ImageRenderer;
import org.example.utility.TooltipTableCellRenderer;

@Getter
public abstract class ResultPanelModel extends JPanel {
    protected ApiResultPanelController apiResultPanelController;
    protected DatabaseResultPanelController databaseResultPanelController;
    protected DefaultTableModel tableModel;
    protected JTable resultTable;
    protected JScrollPane scrollResultTablePane;
    protected BodyPanelView bodyPanelView;

    protected void initResultPanel() {
        setPreferredSize(new Dimension(bodyPanelView.getWidth(), bodyPanelView.getHeight() - userPanelHeight));
        setLayout(new BorderLayout());
    }

    protected void initScrollApiResultTablePane() {
        tableModel = new DefaultTableModel(null, apiTableHeaders) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initScrollResultTablePane();
        resultTable.getColumnModel().getColumn(titleColumnID).setMinWidth(600);
        resultTable.getColumnModel().getColumn(statusColumnID).setMinWidth(60);
        resultTable.getColumnModel().getColumn(numEpisodesColumnID).setMinWidth(30);
        resultTable.getColumnModel().getColumn(meanColumnID).setMinWidth(30);
    }

    protected void initScrollDatabaseResultTablePane() {
        tableModel = new DefaultTableModel(null, databaseTableHeaders) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == scoreColumnID || column == noteColumnId;
            }
        };
        initScrollResultTablePane();
        resultTable.putClientProperty("terminateEditOnFocusLost", true);
        resultTable.addFocusListener((FocusListener) this);
        resultTable.getColumnModel().getColumn(titleColumnID).setMinWidth(450);
        resultTable.getColumnModel().getColumn(statusColumnID).setMinWidth(60);
        resultTable.getColumnModel().getColumn(scoreColumnID).setMinWidth(30);
        resultTable.getColumnModel().getColumn(progressColumnId).setMinWidth(30);
        resultTable.getColumnModel().getColumn(noteColumnId).setMinWidth(150);
    }

    private void initScrollResultTablePane() {
        resultTable = new JTable(tableModel);
        resultTable.getColumnModel().getColumn(idColumnID).setMinWidth(0);
        resultTable.getColumnModel().getColumn(idColumnID).setMaxWidth(0);
        resultTable.getColumnModel().getColumn(idColumnID).setWidth(0);
        resultTable.getColumnModel().getColumn(imageColumnID).setCellRenderer(new ImageRenderer());
        resultTable.getColumnModel().getColumn(imageColumnID).setMinWidth(animeImageWidth);
        resultTable.getColumnModel().getColumn(imageColumnID).setMaxWidth(animeImageWidth);
        resultTable.getColumnModel().getColumn(imageColumnID).setWidth(animeImageWidth);
        resultTable.getColumnModel().getColumn(titleColumnID).setCellRenderer(new TooltipTableCellRenderer());
        resultTable.setRowHeight(animeImageHeight);
        resultTable.getTableHeader().setResizingAllowed(false);
        resultTable.getTableHeader().setReorderingAllowed(false);
        resultTable.setRowSelectionAllowed(true);
        resultTable.setColumnSelectionAllowed(false);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultTable.getSelectionModel().addListSelectionListener((ListSelectionListener) this);
        scrollResultTablePane = new JScrollPane(resultTable);
    }
}
