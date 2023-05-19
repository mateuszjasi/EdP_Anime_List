package org.example.panels.OptionsPanel;

import static org.example.constants.Colors.colorLightGray;
import static org.example.constants.Colors.colorTeal;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import org.example.panels.UserPanel.UserPanelController;

public class OptionsPanelController {
    private final UserPanelController parentController;
    private final OptionsPanelView view;

    public OptionsPanelController(OptionsPanelView view, UserPanelController parentController) {
        this.parentController = parentController;
        this.view = view;
    }

    public void newPage(ActionEvent e) {
        JButton nextPageButton = view.getNextPageButton();
        JButton previousPageButton = view.getPreviousPageButton();
        JButton addToWatchingButton = view.getAddToWatchingButton();
        JButton addToPlanToWatchButton = view.getAddToPlanToWatchButton();
        JButton myListButton = view.getMyListButton();
        if (e.getSource() == nextPageButton || e.getSource() == previousPageButton) {
            if (e.getSource() == nextPageButton) {
                parentController.setOffset(parentController.getOffset() + 10);
            } else {
                parentController.setOffset(parentController.getOffset() - 10);
            }
            previousPageButton.setEnabled(parentController.getOffset() > 0);
            nextPageButton.setEnabled(parentController.searchAnime() >= 10);
        }
        if (e.getSource() == addToWatchingButton) {
            addToWatchingButton.setEnabled(false);
        }
        if (e.getSource() == addToPlanToWatchButton) {
            addToPlanToWatchButton.setEnabled(false);
        }
        if (e.getSource() == myListButton) {
            myListButton.setEnabled(false);
        }
    }

    public void setPagingButtonColor(ChangeEvent e) {
        JButton button = (JButton) e.getSource();
        if (!button.isEnabled()) {
            button.setBackground(colorLightGray);
        } else {
            button.setBackground(colorTeal);
        }
    }
}
