package org.example.panels.OptionsPanel;

import static org.example.constants.Strings.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.example.panels.UserPanel.UserPanelView;

public class OptionsPanelView extends OptionsPanelModel implements ActionListener, ChangeListener {
    public OptionsPanelView(UserPanelView userPanel) {
        this.userPanel = userPanel;
        optionsPanelController = new OptionsPanelController(this, userPanel.getController());

        initOptionsPanel();
        nextPageButton = initButton(nextPageIconPath);
        previousPageButton = initButton(previousPageIconPath);
        addToWatchingButton = initButton(watchingIconPath);
        addToPlanToWatchButton = initButton(planToWatchIconPath);
        myListButton = initButton(myListIconPath);

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
