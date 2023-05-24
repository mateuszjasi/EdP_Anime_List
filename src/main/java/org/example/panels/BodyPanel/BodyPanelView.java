package org.example.panels.BodyPanel;

import static org.example.constants.Resolutions.*;

import java.awt.*;
import org.example.panels.ResultPanel.ApiResultPanelView;
import org.example.panels.ResultPanel.DatabaseResultPanelView;
import org.example.panels.TitlePanel.TitlePanelView;
import org.example.panels.UserPanel.UserPanelView;

public class BodyPanelView extends BodyPanelModel {
    public BodyPanelView(TitlePanelView titlePanel) {
        this.titlePanelView = titlePanel;
        bodyPanelController = new BodyPanelController(this);

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
