package org.example.panels.BodyPanel;

import static org.example.constants.Resolutions.*;

import java.awt.*;
import org.example.panels.ResultPanel.ResultPanelView;
import org.example.panels.TitlePanel.TitlePanelView;
import org.example.panels.UserPanel.UserPanelView;

public class BodyPanelView extends BodyPanelModel {
    public BodyPanelView(TitlePanelView titlePanel) {
        this.titlePanel = titlePanel;

        initBodyPanel();
        insideMarginPanel1 = initInsideMargin(marginWidth * 2, getHeight());
        insideMarginPanel2 = initInsideMargin(marginWidth * 2, getHeight());
        insideMarginPanel3 = initInsideMargin(getWidth(), marginHeight * 2);

        resultPanel = new ResultPanelView(this);
        userPanel = new UserPanelView(this);

        add(insideMarginPanel1, BorderLayout.WEST);
        add(insideMarginPanel2, BorderLayout.EAST);
        add(insideMarginPanel3, BorderLayout.SOUTH);
        add(userPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }
}
