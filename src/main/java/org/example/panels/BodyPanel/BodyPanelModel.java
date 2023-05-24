package org.example.panels.BodyPanel;

import static org.example.constants.Colors.colorWhite;
import static org.example.constants.Resolutions.*;
import static org.example.constants.Resolutions.marginHeight;

import java.awt.*;
import javax.swing.*;
import lombok.Getter;
import org.example.panels.ResultPanel.ResultPanelView;
import org.example.panels.TitlePanel.TitlePanelView;
import org.example.panels.UserPanel.UserPanelView;

@Getter
public abstract class BodyPanelModel extends JPanel {
    protected UserPanelView userPanel;
    protected ResultPanelView resultPanel;
    protected TitlePanelView titlePanel;
    protected JPanel insideMarginPanel1;
    protected JPanel insideMarginPanel2;
    protected JPanel insideMarginPanel3;

    protected void initBodyPanel() {
        setBackground(colorWhite);
        setPreferredSize(new Dimension(
                mainWindowWidth - marginWidth * 2, mainWindowHeight - titlePanel.getHeight() - marginHeight));
        setLayout(new BorderLayout());
    }

    protected JPanel initInsideMargin(int width, int height) {
        JPanel marginPanel = new JPanel();
        marginPanel.setBackground(colorWhite);
        marginPanel.setPreferredSize(new Dimension(width, height));
        return marginPanel;
    }
}
