package org.example.panels.BodyPanel;

import static org.example.constants.Colors.colorWhite;
import static org.example.constants.Resolutions.*;
import static org.example.constants.Resolutions.marginHeight;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import lombok.Getter;
import org.example.panels.ResultPanel.ApiResultPanelView;
import org.example.panels.ResultPanel.DatabaseResultPanelView;
import org.example.panels.TitlePanel.TitlePanelView;
import org.example.panels.UserPanel.UserPanelView;

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
        WindowAdapter windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bodyPanelController.getMySqlConnection().closeConnection();
            }
        };
        JFrame frame = (JFrame) getTopLevelAncestor();
        frame.addWindowListener(windowAdapter);
    }

    protected JPanel initInsideMargin(int width, int height) {
        JPanel marginPanel = new JPanel();
        marginPanel.setBackground(colorWhite);
        marginPanel.setPreferredSize(new Dimension(width, height));
        return marginPanel;
    }
}
