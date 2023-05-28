package org.example.panel.UserPanel;

import org.example.model.Controllers;
import org.example.model.Views;
import org.example.panel.BodyPanel.BodyPanelView;
import org.example.panel.OptionsPanel.OptionsPanelView;
import org.example.panel.SearchPanel.SearchPanelView;

public class UserPanelView extends UserPanelModel {
    public UserPanelView(BodyPanelView bodyPanel) {
        this.bodyPanelView = bodyPanel;
        userPanelController = new UserPanelController(this);
        searchPanelView = new SearchPanelView(this);
        optionsPanelView = new OptionsPanelView(this);
        Views.userPanelView = this;
        Controllers.userPanelController = userPanelController;

        initPanel();

        add(searchPanelView);
        add(optionsPanelView);
    }
}
