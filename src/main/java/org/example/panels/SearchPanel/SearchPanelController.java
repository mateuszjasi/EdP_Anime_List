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
        String searchAnimeTextField = view.getSearchAnimeTextField().getText();
        if (e.getSource() == searchButton
                && !(searchAnimeTextField.equals(idleSearchFieldText) || searchAnimeTextField.isEmpty())) {
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

    public void startProgressBar() {
        view.getSearchButton().setEnabled(false);
        JProgressBar progressBar = view.getProgressBar();
        JTextField searchAnimeTextField = view.getSearchAnimeTextField();
        progressBar.setValue(0);
        progressBar.setString("Looking for: " + searchAnimeTextField.getText());
        progressBar.setStringPainted(true);
        view.remove(searchAnimeTextField);
        view.add(progressBar, 0);
        view.revalidate();
        view.repaint();
    }

    public void updateProgressBar() {
        view.getProgressBar().setValue(view.getProgressBar().getValue() + 1);
    }

    public void stopProgressBar() {
        view.getSearchButton().setEnabled(true);
        view.remove(view.getProgressBar());
        view.add(view.getSearchAnimeTextField(), 0);
        view.revalidate();
        view.repaint();
    }
}
