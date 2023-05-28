package org.example.panel.UserPanel;

import static org.example.constants.Resolutions.userPanelHeight;

import java.awt.*;
import javax.swing.*;
import lombok.Getter;
import org.example.panel.BodyPanel.BodyPanelView;
import org.example.panel.OptionsPanel.OptionsPanelView;
import org.example.panel.SearchPanel.SearchPanelView;

@Getter
public abstract class UserPanelModel extends JPanel {
    protected UserPanelController userPanelController;
    protected BodyPanelView bodyPanelView;
    protected SearchPanelView searchPanelView;
    protected OptionsPanelView optionsPanelView;

    protected void initPanel() {
        setPreferredSize(new Dimension(bodyPanelView.getWidth(), userPanelHeight));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
