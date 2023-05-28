package org.example.panel.BodyPanel;

import static org.example.constants.Resolutions.*;

import java.awt.*;
import org.example.model.Controllers;
import org.example.model.Views;
import org.example.panel.ResultPanel.ApiResultPanelView;
import org.example.panel.ResultPanel.DatabaseResultPanelView;
import org.example.panel.TitlePanel.TitlePanelView;
import org.example.panel.UserPanel.UserPanelView;

public class BodyPanelView extends BodyPanelModel {
    public BodyPanelView(TitlePanelView titlePanel) {
        this.titlePanelView = titlePanel;
        bodyPanelController = new BodyPanelController(this);
        Views.bodyPanelView = this;
        Controllers.bodyPanelController = bodyPanelController;

        initBodyPanel();
        insideMarginPanel1 = initInsideMargin(marginWidth * 2, getHeight());
        insideMarginPanel2 = initInsideMargin(marginWidth * 2, getHeight());
        insideMarginPanel3 = initInsideMargin(getWidth(), marginHeight * 2);

        apiResultPanelView = new ApiResultPanelView(this);
        databaseResultPanelView = new DatabaseResultPanelView(this);
        userPanelView = new UserPanelView(this);

        add(insideMarginPanel1, BorderLayout.WEST);
        add(insideMarginPanel2, BorderLayout.EAST);
        add(insideMarginPanel3, BorderLayout.SOUTH);
        add(userPanelView, BorderLayout.NORTH);
        add(apiResultPanelView, BorderLayout.CENTER);
    }
}
