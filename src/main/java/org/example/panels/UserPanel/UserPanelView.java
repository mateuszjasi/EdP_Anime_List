package org.example.panels.UserPanel;

import org.example.panels.BodyPanel.BodyPanelView;
import org.example.panels.OptionsPanel.OptionsPanelView;
import org.example.panels.SearchPanel.SearchPanelView;

public class UserPanelView extends UserPanelModel {
    public UserPanelView(BodyPanelView bodyPanel) {
        this.bodyPanelView = bodyPanel;
        userPanelController = new UserPanelController(this);
        searchPanelView = new SearchPanelView(this);
        optionsPanelView = new OptionsPanelView(this);

        initPanel();

        add(searchPanelView);
        add(optionsPanelView);
    }
}
