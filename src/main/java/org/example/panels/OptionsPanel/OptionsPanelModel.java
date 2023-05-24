package org.example.panels.OptionsPanel;

import static org.example.constants.Colors.*;
import static org.example.constants.Resolutions.buttonHeight;
import static org.example.constants.Resolutions.buttonWidth;
import static org.example.constants.Strings.myListIconPath;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import lombok.Getter;
import org.example.panels.UserPanel.UserPanelView;

@Getter
public abstract class OptionsPanelModel extends JPanel {
    protected UserPanelView userPanelView;
    protected OptionsPanelController optionsPanelController;
    protected JButton nextPageButton, previousPageButton, addToWatchingButton, addToPlanToWatchButton, myListButton;

    protected void initOptionsPanel() {
        setPreferredSize(new Dimension(userPanelView.getWidth(), userPanelView.getHeight() / 2));
        setBackground(colorWhite);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
    }

    protected JButton initButton(String graphicPath) {
        JButton button = new JButton();
        button.setBackground(colorTeal);
        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        button.setIcon(new ImageIcon(graphicPath));
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorLightGray));
        button.addActionListener((ActionListener) this);
        button.setFocusable(false);
        button.addChangeListener((ChangeListener) this);
        if (!Objects.equals(graphicPath, myListIconPath)) {
            button.setEnabled(false);
        }
        return button;
    }
}
