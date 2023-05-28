package org.example.panel.ResultPanel;

import static org.example.constants.ApiTableColumns.*;
import static org.example.constants.DatabaseTableColumns.*;
import static org.example.constants.Resolutions.*;

import java.awt.*;
import java.awt.event.FocusListener;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import org.example.model.Status;
import org.example.panel.BodyPanel.BodyPanelView;
import org.example.render.ImageRenderer;
import org.example.render.TooltipTableCellRenderer;

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
                return column == scoreColumnID || column == noteColumnId || column == statusColumnID;
            }
        };
        DefaultCellEditor statusEditor = new DefaultCellEditor(new JComboBox<>(new String[] {
            Status.watching.getString(),
            Status.on_hold.getString(),
            Status.completed.getString(),
            Status.dropped.getString(),
            Status.plan_to_watch.getString()
        }));
        statusEditor.addCellEditorListener((CellEditorListener) this);
        DefaultCellEditor scoreEditor = new DefaultCellEditor(
                new JComboBox<>(IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new)));
        scoreEditor.addCellEditorListener((CellEditorListener) this);
        initScrollResultTablePane();
        resultTable.addFocusListener((FocusListener) this);
        resultTable.getColumnModel().getColumn(titleColumnID).setMinWidth(450);
        resultTable.getColumnModel().getColumn(statusColumnID).setMinWidth(60);
        resultTable.getColumnModel().getColumn(scoreColumnID).setMinWidth(30);
        resultTable.getColumnModel().getColumn(scoreColumnID).setCellEditor(scoreEditor);
        resultTable.getColumnModel().getColumn(progressColumnId).setMinWidth(30);
        resultTable.getColumnModel().getColumn(noteColumnId).setMinWidth(150);
        resultTable.getColumnModel().getColumn(statusColumnID).setCellEditor(statusEditor);
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
