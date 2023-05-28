package org.example.panel.UserPanel;

import org.example.panel.BodyPanel.BodyPanelView;
import org.example.panel.OptionsPanel.OptionsPanelView;
import org.example.panel.SearchPanel.SearchPanelView;

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
