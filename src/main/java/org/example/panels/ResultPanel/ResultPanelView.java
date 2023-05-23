package org.example.panels.ResultPanel;

import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.example.panels.BodyPanel.BodyPanelView;

public class ResultPanelView extends ResultPanelModel implements ListSelectionListener {
    public ResultPanelView(BodyPanelView bodyPanel) {
        this.bodyPanel = bodyPanel;
        this.resultPanelController = new ResultPanelController(this);

        initResultPanel();
        initScrollPaneTable();

        add(scrollTablePane, BorderLayout.CENTER);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        resultPanelController.rowSelected();
    }
}
