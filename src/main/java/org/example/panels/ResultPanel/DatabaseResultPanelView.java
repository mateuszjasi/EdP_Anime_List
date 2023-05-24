package org.example.panels.ResultPanel;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.event.*;
import org.example.panels.BodyPanel.BodyPanelView;

public class DatabaseResultPanelView extends ResultPanelModel implements ListSelectionListener, FocusListener {
    public DatabaseResultPanelView(BodyPanelView bodyPanel) {
        this.bodyPanelView = bodyPanel;
        this.databaseResultPanelController = new DatabaseResultPanelController(this);

        initResultPanel();
        initScrollDatabaseResultTablePane();

        add(scrollResultTablePane, BorderLayout.CENTER);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        databaseResultPanelController.rowSelected();
    }

    @Override
    public void focusGained(FocusEvent e) {
        databaseResultPanelController.columnValueChanged(e);
    }

    @Override
    public void focusLost(FocusEvent e) {}
}
