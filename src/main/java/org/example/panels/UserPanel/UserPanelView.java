package org.example.panels.UserPanel;

import java.awt.*;
import javax.swing.*;
import org.example.panels.BodyPanel.BodyPanelView;
import org.example.panels.OptionsPanel.OptionsPanelView;
import org.example.panels.ResultPanel.ResultPanelView;
import org.example.panels.SearchPanel.SearchPanelView;

public class UserPanelView extends JPanel {
    private final BodyPanelView parent;

    public UserPanelView(BodyPanelView parent, ResultPanelView resultPanel) {
        this.parent = parent;
        SearchPanelView searchPanel = new SearchPanelView(this);
        OptionsPanelView optionsPanel = new OptionsPanelView(this);
        UserPanelController controller = new UserPanelController(searchPanel, resultPanel);
        searchPanel.setParentController(controller);
        optionsPanel.setParentController(controller);

        initPanel();

        add(searchPanel);
        add(optionsPanel);
    }

    private void initPanel() {
        setPreferredSize(new Dimension(parent.getWidth(), 150));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
