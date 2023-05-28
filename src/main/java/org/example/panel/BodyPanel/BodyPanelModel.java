package org.example.panel.BodyPanel;

import static org.example.constants.Colors.colorWhite;
import static org.example.constants.Resolutions.*;
import static org.example.constants.Resolutions.marginHeight;

import java.awt.*;
import javax.swing.*;
import lombok.Getter;
import org.example.panel.ResultPanel.ApiResultPanelView;
import org.example.panel.ResultPanel.DatabaseResultPanelView;
import org.example.panel.TitlePanel.TitlePanelView;
import org.example.panel.UserPanel.UserPanelView;

@Getter
public abstract class BodyPanelModel extends JPanel {
    protected BodyPanelController bodyPanelController;
    protected UserPanelView userPanelView;
    protected ApiResultPanelView apiResultPanelView;
    protected DatabaseResultPanelView databaseResultPanelView;
    protected TitlePanelView titlePanelView;
    protected JPanel insideMarginPanel1;
    protected JPanel insideMarginPanel2;
    protected JPanel insideMarginPanel3;

    protected void initBodyPanel() {
        setBackground(colorWhite);
        setPreferredSize(new Dimension(
                mainWindowWidth - marginWidth * 2, mainWindowHeight - titlePanelView.getHeight() - marginHeight));
        setLayout(new BorderLayout());
    }

    protected JPanel initInsideMargin(int width, int height) {
        JPanel marginPanel = new JPanel();
        marginPanel.setBackground(colorWhite);
        marginPanel.setPreferredSize(new Dimension(width, height));
        return marginPanel;
    }
}
