package org.example.panels.UserPanel;

import org.example.panels.BodyPanel.BodyPanelView;
import org.example.panels.OptionsPanel.OptionsPanelView;
import org.example.panels.SearchPanel.SearchPanelView;

public class UserPanelView extends UserPanelModel {
    public UserPanelView(BodyPanelView bodyPanel) {
        this.bodyPanel = bodyPanel;
        controller = new UserPanelController(this);
        searchPanel = new SearchPanelView(this);
        optionsPanel = new OptionsPanelView(this);

        initPanel();

        add(searchPanel);
        add(optionsPanel);
    }
}
