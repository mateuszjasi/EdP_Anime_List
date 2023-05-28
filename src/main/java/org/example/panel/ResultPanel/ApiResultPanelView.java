package org.example.panel.ResultPanel;

import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.example.panel.BodyPanel.BodyPanelView;

public class ApiResultPanelView extends ResultPanelModel implements ListSelectionListener {
    public ApiResultPanelView(BodyPanelView bodyPanel) {
        this.bodyPanelView = bodyPanel;
        this.apiResultPanelController = new ApiResultPanelController(this);

        initResultPanel();
        initScrollApiResultTablePane();

        add(scrollResultTablePane, BorderLayout.CENTER);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        apiResultPanelController.rowSelected();
    }
}
