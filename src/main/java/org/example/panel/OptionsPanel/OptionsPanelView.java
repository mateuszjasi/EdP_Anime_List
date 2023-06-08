package org.example.panel.OptionsPanel;

import static org.example.constants.Strings.*;

import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import org.example.model.Controllers;
import org.example.model.Views;
import org.example.panel.UserPanel.UserPanelView;

public class OptionsPanelView extends OptionsPanelModel {
    public OptionsPanelView(UserPanelView userPanel) {
        this.userPanelView = userPanel;
        optionsPanelController = new OptionsPanelController(this);
        Views.optionsPanelView = this;
        Controllers.optionsPanelController = optionsPanelController;

        initOptionsPanel();
        nextPageButton = initButton(nextPageIconPath);
        previousPageButton = initButton(previousPageIconPath);
        addToWatchingButton = initButton(watchingIconPath);
        addToPlanToWatchButton = initButton(planToWatchIconPath);
        myListButton = initButton(myListIconPath);
        changeProgressButton = initButton(changeProgressPath);
        removeFromListButton = initButton(removeFromListPath);

        add(previousPageButton);
        add(addToWatchingButton);
        add(addToPlanToWatchButton);
        add(myListButton);
        add(nextPageButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        optionsPanelController.buttonClicked(e);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        optionsPanelController.setButtonsColor(e);
    }
}
