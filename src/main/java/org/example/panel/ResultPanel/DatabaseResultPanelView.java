package org.example.panel.ResultPanel;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.event.*;
import org.example.model.Controllers;
import org.example.model.Views;
import org.example.panel.BodyPanel.BodyPanelView;

public class DatabaseResultPanelView extends ResultPanelModel implements FocusListener, CellEditorListener {
    public DatabaseResultPanelView(BodyPanelView bodyPanel) {
        this.bodyPanelView = bodyPanel;
        this.databaseResultPanelController = new DatabaseResultPanelController(this);
        Views.databaseResultPanelView = this;
        Controllers.databaseResultPanelController = databaseResultPanelController;

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
        databaseResultPanelController.noteChanged(e);
    }

    @Override
    public void focusLost(FocusEvent e) {}

    @Override
    public void editingStopped(ChangeEvent e) {
        databaseResultPanelController.listColumnChanged();
    }

    @Override
    public void editingCanceled(ChangeEvent e) {}
}
