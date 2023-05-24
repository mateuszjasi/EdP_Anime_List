package org.example.panels.SearchPanel;

import static org.example.constants.Colors.colorLightGray;
import static org.example.constants.Strings.idleSearchFieldText;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import javax.swing.*;
import lombok.AllArgsConstructor;
import org.example.panels.BodyPanel.BodyPanelController;
import org.example.panels.UserPanel.UserPanelController;

@AllArgsConstructor
public class SearchPanelController {
    private final SearchPanelView searchPanelView;

    public void searchAnime(ActionEvent e) {
        BodyPanelController bodyPanelController =
                searchPanelView.getUserPanelView().getBodyPanelView().getBodyPanelController();
        JButton searchButton = searchPanelView.getSearchButton();
        JTextField searchAnimeTextField = searchPanelView.getSearchAnimeTextField();
        if (e.getSource() == searchButton || e.getSource() == searchAnimeTextField) {
            if (!(searchAnimeTextField.getText().equals(idleSearchFieldText)
                    || searchAnimeTextField.getText().isEmpty())) {
                if (bodyPanelController.isDataFromApiMode()) {
                    UserPanelController userPanelController =
                            searchPanelView.getUserPanelView().getUserPanelController();
                    userPanelController.setOffset(0);
                    userPanelController.searchAnime();
                } else {
                    bodyPanelController.setMySqlConnectionOffset(0);
                    bodyPanelController.searchAnimeDatabase();
                }
            }
        }
    }

    public void startProgressBar() {
        searchPanelView.getSearchButton().setEnabled(false);
        JProgressBar progressBar = searchPanelView.getSearchProgressBar();
        JTextField searchAnimeTextField = searchPanelView.getSearchAnimeTextField();
        progressBar.setValue(0);
        progressBar.setString("Looking for: " + searchAnimeTextField.getText());
        progressBar.setStringPainted(true);
        searchPanelView.remove(searchAnimeTextField);
        searchPanelView.add(progressBar, 0);
        searchPanelView.revalidate();
        searchPanelView.repaint();
    }

    public void updateProgressBar() {
        searchPanelView
                .getSearchProgressBar()
                .setValue(searchPanelView.getSearchProgressBar().getValue() + 1);
    }

    public void stopProgressBar() {
        searchPanelView.getSearchButton().setEnabled(true);
        searchPanelView.remove(searchPanelView.getSearchProgressBar());
        searchPanelView.add(searchPanelView.getSearchAnimeTextField(), 0);
        searchPanelView.revalidate();
        searchPanelView.repaint();
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
