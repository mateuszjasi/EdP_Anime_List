package org.example.panel.SearchPanel;

import static org.example.constants.Colors.colorLightGray;
import static org.example.constants.Strings.idleSearchFieldText;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import javax.swing.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.panel.BodyPanel.BodyPanelController;
import org.example.panel.UserPanel.UserPanelController;

@RequiredArgsConstructor
@Getter
public class SearchPanelController {
    private final SearchPanelView searchPanelView;
    private String apiSearchTitle = "";
    private String databaseSearchTitle = "";

    public void searchAnime(ActionEvent e) {
        UserPanelController userPanelController =
                searchPanelView.getUserPanelView().getUserPanelController();
        BodyPanelController bodyPanelController =
                searchPanelView.getUserPanelView().getBodyPanelView().getBodyPanelController();
        JButton searchButton = searchPanelView.getSearchButton();
        JTextField searchAnimeTextField = searchPanelView.getSearchAnimeTextField();
        if (e.getSource() == searchButton || e.getSource() == searchAnimeTextField) {
            if (!bodyPanelController.isDataFromApiMode()) {
                databaseSearchTitle = searchAnimeTextField.getText();
                bodyPanelController.setMySqlConnectionOffset(0);
                bodyPanelController.searchAnimeDatabase();
            } else if (!(searchAnimeTextField.getText().equals(idleSearchFieldText)
                    || searchAnimeTextField.getText().isEmpty())) {
                apiSearchTitle = searchAnimeTextField.getText();
                userPanelController.setOffset(0);
                userPanelController.searchAnime();
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
