package org.example.panels.UserPanel;

import static org.example.constants.AnimeSearchWindowResolutions.userPanelHeight;

import java.awt.*;
import javax.swing.*;
import org.example.panels.BodyPanel.BodyPanelView;
import org.example.panels.OptionsPanel.OptionsPanelView;
import org.example.panels.SearchPanel.SearchPanelView;

public class UserPanelView extends JPanel {
    private final UserPanelController controller;
    private final BodyPanelView parent;
    private final SearchPanelView searchPanel;
    private final OptionsPanelView optionsPanel;

    public UserPanelView(BodyPanelView parent) {
        this.parent = parent;
        controller = new UserPanelController(this);
        searchPanel = new SearchPanelView(this, controller);
        optionsPanel = new OptionsPanelView(this, controller);

        initPanel();

        add(searchPanel);
        add(optionsPanel);
    }

    private void initPanel() {
        setPreferredSize(new Dimension(parent.getWidth(), userPanelHeight));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public SearchPanelView getSearchPanel() {
        return searchPanel;
    }

    public OptionsPanelView getOptionsPanel() {
        return optionsPanel;
    }

    public UserPanelController getController() {
        return controller;
    }

    public BodyPanelView getUserParent() {
        return parent;
    }
}
