package org.example.panel.ResultPanel;

import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import org.example.model.Controllers;
import org.example.model.Views;
import org.example.panel.BodyPanel.BodyPanelView;

public class ApiResultPanelView extends ResultPanelModel {
    public ApiResultPanelView(BodyPanelView bodyPanel) {
        this.bodyPanelView = bodyPanel;
        this.apiResultPanelController = new ApiResultPanelController(this);
        Views.apiResultPanelView = this;
        Controllers.apiResultPanelController = apiResultPanelController;

        initResultPanel();
        initScrollApiResultTablePane();

        add(scrollResultTablePane, BorderLayout.CENTER);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        apiResultPanelController.rowSelected();
    }
}
