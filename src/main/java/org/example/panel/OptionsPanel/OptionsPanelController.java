package org.example.panel.OptionsPanel;

import static org.example.constants.ApiTableColumns.*;
import static org.example.constants.Colors.colorLightGray;
import static org.example.constants.Colors.colorTeal;
import static org.example.constants.DatabaseTableColumns.progressColumnId;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.dialog.ChangeProgressDialog;
import org.example.model.Anime;
import org.example.model.Status;
import org.example.panel.BodyPanel.BodyPanelController;
import org.example.panel.UserPanel.UserPanelController;

@Getter
@RequiredArgsConstructor
public class OptionsPanelController {
    private final OptionsPanelView optionsPanelView;
    private boolean searching;
    private boolean nextButtonStatus;

    public void buttonClicked(ActionEvent e) {
        JButton nextPageButton = optionsPanelView.getNextPageButton();
        JButton previousPageButton = optionsPanelView.getPreviousPageButton();
        JButton addToWatchingButton = optionsPanelView.getAddToWatchingButton();
        JButton addToPlanToWatchButton = optionsPanelView.getAddToPlanToWatchButton();
        JButton myListButton = optionsPanelView.getMyListButton();
        JButton changeProgressButton = optionsPanelView.getChangeProgressButton();
        JButton removeFromListButton = optionsPanelView.getRemoveFromListButton();
        if (e.getSource() == nextPageButton || e.getSource() == previousPageButton) {
            pagingButtonClicked(e);
        }
        if (e.getSource() == addToWatchingButton || e.getSource() == addToPlanToWatchButton) {
            addToDatabaseButtonClicked(e);
        }
        if (e.getSource() == myListButton) {
            changeModeButtonClicked();
        }
        if (e.getSource() == changeProgressButton) {
            changeProgressButtonClicked();
        }
        if (e.getSource() == removeFromListButton) {
            removeFromListButtonClicked();
        }
    }

    private void pagingButtonClicked(ActionEvent e) {
        JButton nextPageButton = optionsPanelView.getNextPageButton();
        JButton previousPageButton = optionsPanelView.getPreviousPageButton();
        UserPanelController userPanelController =
                optionsPanelView.getUserPanelView().getUserPanelController();
        BodyPanelController bodyPanelController =
                optionsPanelView.getUserPanelView().getBodyPanelView().getBodyPanelController();
        boolean isDataFromApiMode = bodyPanelController.isDataFromApiMode();
        if (e.getSource() == nextPageButton) {
            if (isDataFromApiMode) {
                userPanelController.setOffset(userPanelController.getOffset() + 10);
            } else {
                bodyPanelController.setMySqlConnectionOffset(bodyPanelController.getMySqlConnectionOffset() + 10);
            }
        } else {
            if (isDataFromApiMode) {
                userPanelController.setOffset(userPanelController.getOffset() - 10);
            } else {
                bodyPanelController.setMySqlConnectionOffset(bodyPanelController.getMySqlConnectionOffset() - 10);
            }
        }
        int offset =
                isDataFromApiMode ? userPanelController.getOffset() : bodyPanelController.getMySqlConnectionOffset();
        previousPageButton.setEnabled(offset > 0);
        if (isDataFromApiMode) {
            userPanelController.searchAnime();
        } else {
            bodyPanelController.searchAnimeDatabase();
        }
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
                            String.valueOf(
                                    (e.getSource() == addToWatchingButton) ? Status.watching : Status.plan_to_watch));
            bodyPanelController.getMyAnimeListIds().add(id);
        }
    }

    private void changeModeButtonClicked() {
        BodyPanelController bodyPanelController =
                optionsPanelView.getUserPanelView().getBodyPanelView().getBodyPanelController();
        UserPanelController userPanelController =
                optionsPanelView.getUserPanelView().getUserPanelController();
        JButton addToWatchingButton = optionsPanelView.getAddToWatchingButton();
        JButton addToPlanToWatchButton = optionsPanelView.getAddToPlanToWatchButton();
        JButton changeProgressButton = optionsPanelView.getChangeProgressButton();
        JButton removeFromListButton = optionsPanelView.getRemoveFromListButton();
        JButton nextPageButton = optionsPanelView.getNextPageButton();
        JButton previousPageButton = optionsPanelView.getPreviousPageButton();
        if (bodyPanelController.isDataFromApiMode()) {
            optionsPanelView.remove(addToWatchingButton);
            optionsPanelView.remove(addToPlanToWatchButton);
            optionsPanelView.add(changeProgressButton, 1);
            optionsPanelView.add(removeFromListButton, 2);
            nextButtonStatus = nextPageButton.isEnabled();
        } else {
            optionsPanelView.remove(changeProgressButton);
            optionsPanelView.remove(removeFromListButton);
            optionsPanelView.add(addToWatchingButton, 1);
            optionsPanelView.add(addToPlanToWatchButton, 2);
            nextPageButton.setEnabled(nextButtonStatus);
            previousPageButton.setEnabled(userPanelController.getOffset() > 0);
        }
        bodyPanelController.changeMode();
    }

    private void changeProgressButtonClicked() {
        JTable resultTable = optionsPanelView
                .getUserPanelView()
                .getBodyPanelView()
                .getDatabaseResultPanelView()
                .getResultTable();
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow != -1) {
            BodyPanelController bodyPanelController =
                    optionsPanelView.getUserPanelView().getBodyPanelView().getBodyPanelController();
            String[] progressValues = resultTable
                    .getValueAt(selectedRow, progressColumnId)
                    .toString()
                    .split(" / ");
            int progress = Integer.parseInt(progressValues[0]);
            int progressMax = Integer.parseInt(progressValues[1]);
            ChangeProgressDialog dialog =
                    new ChangeProgressDialog((JFrame) optionsPanelView.getTopLevelAncestor(), progress, progressMax);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                int id = Integer.parseInt((String) resultTable.getValueAt(selectedRow, idColumnID));
                int newProgress = dialog.getNewProgress();
                bodyPanelController.getMySqlConnection().updateProgress(id, newProgress);
                bodyPanelController.searchAnimeDatabase();
            }
        }
    }

    private void removeFromListButtonClicked() {
        BodyPanelController bodyPanelController =
                optionsPanelView.getUserPanelView().getBodyPanelView().getBodyPanelController();
        JTable resultTable = optionsPanelView
                .getUserPanelView()
                .getBodyPanelView()
                .getDatabaseResultPanelView()
                .getResultTable();
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow != -1) {
            int removedId = Integer.parseInt((String) resultTable.getValueAt(selectedRow, idColumnID));
            bodyPanelController.removeAnime(removedId);
        }
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
                optionsPanelView.getUserPanelView().getUserPanelController();
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

    public void enableChangeButtons(int id) {
        BodyPanelController bodyPanelController =
                optionsPanelView.getUserPanelView().getBodyPanelView().getBodyPanelController();
        if (bodyPanelController.getMyAnimeListIds().contains(id)) {
            optionsPanelView.getChangeProgressButton().setEnabled(true);
            optionsPanelView.getRemoveFromListButton().setEnabled(true);
        } else {
            disableChangeButtons();
        }
    }

    public void disableChangeButtons() {
        optionsPanelView.getChangeProgressButton().setEnabled(false);
        optionsPanelView.getRemoveFromListButton().setEnabled(false);
    }
}
