package org.example.panels.UserPanel;

import static org.example.constants.Resolutions.userPanelHeight;

import java.awt.*;
import javax.swing.*;
import lombok.Getter;
import org.example.panels.BodyPanel.BodyPanelView;
import org.example.panels.OptionsPanel.OptionsPanelView;
import org.example.panels.SearchPanel.SearchPanelView;

@Getter
public abstract class UserPanelModel extends JPanel {
    protected UserPanelController controller;
    protected BodyPanelView bodyPanel;
    protected SearchPanelView searchPanel;
    protected OptionsPanelView optionsPanel;

    protected void initPanel() {
        setPreferredSize(new Dimension(bodyPanel.getWidth(), userPanelHeight));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
