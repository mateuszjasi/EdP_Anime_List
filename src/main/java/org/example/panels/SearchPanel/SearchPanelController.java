package org.example.panels.SearchPanel;

import static org.example.constants.Colors.colorLightGray;
import static org.example.constants.Strings.idleSearchFieldText;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import javax.swing.*;
import org.example.panels.UserPanel.UserPanelController;

public class SearchPanelController {
    private final SearchPanelView view;
    private final UserPanelController parentController;

    public SearchPanelController(SearchPanelView view, UserPanelController parentController) {
        this.parentController = parentController;
        this.view = view;
    }

    public void searchAnime(ActionEvent e) {
        JButton searchButton = view.getSearchButton();
        JTextField searchAnimeTextField = view.getSearchAnimeTextField();
        if (e.getSource() == searchButton
                && !(searchAnimeTextField.getText().equals(idleSearchFieldText)
                        || searchAnimeTextField.getText().isEmpty())) {
            parentController.setOffset(0);
            parentController.searchAnime();
        }
    }

    public void fillTextField(FocusEvent e, JTextField searchAnimeTextField) {
        if (e.getSource() == searchAnimeTextField
                && searchAnimeTextField.getText().isEmpty()) {
            searchAnimeTextField.setText(idleSearchFieldText);
            searchAnimeTextField.setForeground(colorLightGray);
        }
    }

    public void clearTextField(FocusEvent e, JTextField searchAnimeTextField) {
        if (e.getSource() == searchAnimeTextField
                && searchAnimeTextField.getText().equals(idleSearchFieldText)) {
            searchAnimeTextField.setText("");
            searchAnimeTextField.setForeground(Color.BLACK);
        }
    }
}
