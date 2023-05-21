package org.example.panels.BodyPanel;

import static org.example.constants.AnimeSearchWindowResolutions.*;
import static org.example.constants.Colors.colorWhite;

import java.awt.*;
import javax.swing.*;
import org.example.panels.ResultPanel.ResultPanelView;
import org.example.panels.TitlePanel.TitlePanelView;
import org.example.panels.UserPanel.UserPanelView;

public class BodyPanelView extends JPanel {
    private final UserPanelView userPanel;
    private final ResultPanelView resultPanel;
    private final TitlePanelView titlePanel;
    private final JPanel insideMarginPanel1 = new JPanel();
    private final JPanel insideMarginPanel2 = new JPanel();
    private final JPanel insideMarginPanel3 = new JPanel();

    public BodyPanelView(TitlePanelView titlePanel) {
        this.titlePanel = titlePanel;

        initPanel();
        initMargins();

        resultPanel = new ResultPanelView(this);
        userPanel = new UserPanelView(this);

        add(insideMarginPanel1, BorderLayout.WEST);
        add(insideMarginPanel2, BorderLayout.EAST);
        add(insideMarginPanel3, BorderLayout.SOUTH);
        add(userPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }

    private void initPanel() {
        setBackground(colorWhite);
        setPreferredSize(new Dimension(
                mainWindowWidth - marginWidth * 2, mainWindowHeight - titlePanel.getHeight() - marginHeight));
        setLayout(new BorderLayout());
    }

    private void initMargins() {
        insideMarginPanel1.setBackground(colorWhite);
        insideMarginPanel1.setPreferredSize(new Dimension(marginWidth * 2, getHeight()));
        insideMarginPanel2.setBackground(colorWhite);
        insideMarginPanel2.setPreferredSize(new Dimension(marginWidth * 2, getHeight()));
        insideMarginPanel3.setBackground(colorWhite);
        insideMarginPanel3.setPreferredSize(new Dimension(getWidth(), marginHeight * 2));
    }

    public UserPanelView getUserPanel() {
        return userPanel;
    }

    public ResultPanelView getResultPanel() {
        return resultPanel;
    }
}
