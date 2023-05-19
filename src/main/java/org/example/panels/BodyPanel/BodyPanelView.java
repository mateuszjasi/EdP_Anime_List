package org.example.panels.BodyPanel;

import static org.example.constants.AnimeSearchWindowResolutions.*;
import static org.example.constants.Colors.colorWhite;

import java.awt.*;
import javax.swing.*;
import org.example.panels.ResultPanel.ResultPanelView;
import org.example.panels.UserPanel.UserPanelView;

public class BodyPanelView extends JPanel {
    private final JPanel marginPanel1;
    private final JPanel titlePanel;
    private final JPanel insideMarginPanel1 = new JPanel();
    private final JPanel insideMarginPanel2 = new JPanel();
    private final JPanel insideMarginPanel3 = new JPanel();

    public BodyPanelView(JPanel marginPanel1, JPanel titlePanel) {
        this.marginPanel1 = marginPanel1;
        this.titlePanel = titlePanel;

        initPanel();
        initMargins();

        ResultPanelView resultPanel = new ResultPanelView(this);
        UserPanelView userPanel = new UserPanelView(this, resultPanel);

        add(insideMarginPanel1, BorderLayout.WEST);
        add(insideMarginPanel2, BorderLayout.EAST);
        add(insideMarginPanel3, BorderLayout.SOUTH);
        add(userPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }

    private void initPanel() {
        setBackground(colorWhite);
        setPreferredSize(new Dimension(
                mainWindowWidth - marginPanel1.getWidth() * 2, mainWindowHeight - titlePanel.getHeight() - 15));
        setLayout(new BorderLayout());
    }

    private void initMargins() {
        insideMarginPanel1.setBackground(colorWhite);
        insideMarginPanel1.setPreferredSize(new Dimension(30, getHeight()));
        insideMarginPanel2.setBackground(colorWhite);
        insideMarginPanel2.setPreferredSize(new Dimension(30, getHeight()));
        insideMarginPanel3.setBackground(colorWhite);
        insideMarginPanel3.setPreferredSize(new Dimension(getWidth(), 30));
    }
}
