package org.example.panels.TitlePanel;

import static org.example.constants.Resolutions.*;
import static org.example.constants.Resolutions.marginWidth;
import static org.example.constants.Colors.colorBlue;
import static org.example.constants.Colors.colorWhite;
import static org.example.constants.Fonts.titleFont;
import static org.example.constants.Strings.titlePanelTitle;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import lombok.Getter;

@Getter
public abstract class TitlePanelModel extends JPanel {
    protected JLabel titleLabel;

    protected void initTitlePanel() {
        setPreferredSize(new Dimension(mainWindowWidth, titlePanelHeight));
        setBackground(colorBlue);
        setLayout(new BorderLayout());
    }

    protected JLabel initTitleLabel() {
        JLabel label = new JLabel();
        label.setText(titlePanelTitle);
        label.setForeground(colorWhite);
        label.setFont(titleFont);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setBorder(new CompoundBorder(
                label.getBorder(), new EmptyBorder(marginHeight, marginWidth, marginHeight, marginWidth)));
        return label;
    }
}
