package org.example.panels.OptionsPanel;

import static org.example.constants.ApiTableColumns.*;
import static org.example.constants.Colors.colorLightGray;
import static org.example.constants.Colors.colorTeal;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.model.Anime;
import org.example.model.Status;
import org.example.panels.BodyPanel.BodyPanelController;
import org.example.panels.UserPanel.UserPanelController;

@Getter
@RequiredArgsConstructor
public class OptionsPanelController {
    private final OptionsPanelView optionsPanelView;
    private boolean searching;

    public void buttonClicked(ActionEvent e) {
        JButton nextPageButton = optionsPanelView.getNextPageButton();
        JButton previousPageButton = optionsPanelView.getPreviousPageButton();
        JButton addToWatchingButton = optionsPanelView.getAddToWatchingButton();
        JButton addToPlanToWatchButton = optionsPanelView.getAddToPlanToWatchButton();
        JButton myListButton = optionsPanelView.getMyListButton();
        if (e.getSource() == nextPageButton || e.getSource() == previousPageButton) {
            pagingButtonClicked(e);
        }
        if (e.getSource() == addToWatchingButton || e.getSource() == addToPlanToWatchButton) {
            addToDatabaseButtonClicked(e);
        }
        if (e.getSource() == myListButton) {
            changeModeButtonClicked();
        }
    }

    private void pagingButtonClicked(ActionEvent e) {
        JButton nextPageButton = optionsPanelView.getNextPageButton();
        JButton previousPageButton = optionsPanelView.getPreviousPageButton();
        UserPanelController userPanelController =
                optionsPanelView.getUserPanelView().getUserPanelController();
        if (e.getSource() == nextPageButton) {
            userPanelController.setOffset(userPanelController.getOffset() + 10);
        } else {
            userPanelController.setOffset(userPanelController.getOffset() - 10);
        }
        previousPageButton.setEnabled(userPanelController.getOffset() > 0);
        userPanelController.searchAnime();
    }

    private void addToDatabaseButtonClicked(ActionEvent e) {
        JButton addToWatchingButton = optionsPanelView.getAddToWatchingButton();
        JButton addToPlanToWatchButton = optionsPanelView.getAddToPlanToWatchButton();
        addToWatchingButton.setEnabled(false);
        addToPlanToWatchButton.setEnabled(false);
        JTable resultTable = optionsPanelView
                .getUserPanelView()
                .getBodyPanelView()
                .getApiResultPanelView()
                .getResultTable();
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow != -1) {
            BodyPanelController bodyPanelController =
                    optionsPanelView.getUserPanelView().getBodyPanelView().getBodyPanelController();
            int id = Integer.parseInt((String) resultTable.getValueAt(selectedRow, idColumnID));
            Anime anime = optionsPanelView
                    .getUserPanelView()
                    .getUserPanelController()
                    .getAnimeService()
                    .getAnimeFromId(id);
            bodyPanelController
                    .getMySqlConnection()
                    .addAnime(
                            id,
                            anime.getTitle(),
                            anime.getImageUrl(),
                            Integer.parseInt(anime.getNumEpisodes()),
                            (e.getSource() == addToWatchingButton)
                                    ? String.valueOf(Status.watching)
                                    : String.valueOf(Status.plan_to_watch));
            bodyPanelController.getMyAnimeListIds().add(id);
        }
    }

    public void changeModeButtonClicked() {
        BodyPanelController bodyPanelController =
                optionsPanelView.getUserPanelView().getBodyPanelView().getBodyPanelController();
        bodyPanelController.changeMode();
    }

    public void enableNextPageButton(int animeListSize) {
        optionsPanelView.getNextPageButton().setEnabled(animeListSize >= 10);
    }

    public void setButtonsColor(ChangeEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground((!button.isEnabled()) ? colorLightGray : colorTeal);
    }

    public void enableAddButtons(int id) {
        BodyPanelController bodyPanelController =
                optionsPanelView.getUserPanelView().getBodyPanelView().getBodyPanelController();
        if (!searching) {
            if (!bodyPanelController.getMyAnimeListIds().contains(id)) {
                optionsPanelView.getAddToPlanToWatchButton().setEnabled(true);
                optionsPanelView.getAddToWatchingButton().setEnabled(true);
            } else {
                disableAddButtons();
            }
        }
    }

    public void disableAddButtons() {
        optionsPanelView.getAddToPlanToWatchButton().setEnabled(false);
        optionsPanelView.getAddToWatchingButton().setEnabled(false);
    }

    public void enableButtons() {
        searching = false;
        UserPanelController userPanelController =
                getOptionsPanelView().getUserPanelView().getUserPanelController();
        optionsPanelView.getPreviousPageButton().setEnabled(userPanelController.getOffset() > 0);
        optionsPanelView.getMyListButton().setEnabled(true);
    }

    public void disableButtons() {
        searching = true;
        optionsPanelView.getAddToPlanToWatchButton().setEnabled(false);
        optionsPanelView.getAddToWatchingButton().setEnabled(false);
        optionsPanelView.getNextPageButton().setEnabled(false);
        optionsPanelView.getPreviousPageButton().setEnabled(false);
        optionsPanelView.getMyListButton().setEnabled(false);
    }
}
