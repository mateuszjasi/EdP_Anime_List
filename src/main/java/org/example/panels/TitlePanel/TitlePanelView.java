package org.example.panels.TitlePanel;

import static org.example.constants.AnimeSearchWindowResolutions.mainWindowWidth;
import static org.example.constants.Colors.colorBlue;
import static org.example.constants.Colors.colorWhite;
import static org.example.constants.Fonts.titleFont;
import static org.example.constants.Strings.titlePanelTitle;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class TitlePanelView extends JPanel {
    private JLabel titleLabel;

    public TitlePanelView() {
        initTitlePanel();
        initTitleLabel();

        add(titleLabel);
    }

    private void initTitlePanel() {
        setPreferredSize(new Dimension(mainWindowWidth, 80));
        setBackground(colorBlue);
        setLayout(new BorderLayout());
    }

    private void initTitleLabel() {
        titleLabel = new JLabel();
        titleLabel.setText(titlePanelTitle);
        titleLabel.setForeground(colorWhite);
        titleLabel.setFont(titleFont);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.LEFT);
        titleLabel.setBorder(new CompoundBorder(titleLabel.getBorder(), new EmptyBorder(15, 15, 15, 15)));
    }
}
