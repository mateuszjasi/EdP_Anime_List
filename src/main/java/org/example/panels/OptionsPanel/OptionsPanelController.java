package org.example.panels.OptionsPanel;

import static org.example.constants.TableColumns.*;
import static org.example.constants.Colors.colorLightGray;
import static org.example.constants.Colors.colorTeal;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.model.Anime;
import org.example.model.Status;
import org.example.panels.UserPanel.UserPanelController;
import org.example.service.MySqlConnection;

@Getter
@RequiredArgsConstructor
public class OptionsPanelController {
    private final OptionsPanelView optionsPanelView;
    private final UserPanelController userPanelController;
    private final MySqlConnection mySqlConnection = new MySqlConnection();
    private final List<Integer> myAnimeListIds = mySqlConnection.getMyAnimeListIds();
    private boolean searching;

    public void buttonClicked(ActionEvent e) {
        JButton nextPageButton = optionsPanelView.getNextPageButton();
        JButton previousPageButton = optionsPanelView.getPreviousPageButton();
        JButton addToWatchingButton = optionsPanelView.getAddToWatchingButton();
        JButton addToPlanToWatchButton = optionsPanelView.getAddToPlanToWatchButton();
        JButton myListButton = optionsPanelView.getMyListButton();
        if (e.getSource() == nextPageButton || e.getSource() == previousPageButton) {
            if (e.getSource() == nextPageButton) {
                userPanelController.setOffset(userPanelController.getOffset() + 10);
            } else {
                userPanelController.setOffset(userPanelController.getOffset() - 10);
            }
            previousPageButton.setEnabled(userPanelController.getOffset() > 0);
            userPanelController.searchAnime();
        }
        if (e.getSource() == addToWatchingButton || e.getSource() == addToPlanToWatchButton) {
            addToWatchingButton.setEnabled(false);
            addToPlanToWatchButton.setEnabled(false);
            JTable resultTable = optionsPanelView
                    .getUserPanel()
                    .getBodyPanel()
                    .getResultPanel()
                    .getResultTable();
            int selectedRow = resultTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = Integer.parseInt((String) resultTable.getValueAt(selectedRow, idColumnID));
                Anime anime = optionsPanelView
                        .getUserPanel()
                        .getController()
                        .getAnimeService()
                        .getAnimeFromId(id);
                mySqlConnection.addAnime(
                        id,
                        anime.getTitle(),
                        anime.getImageUrl(),
                        Integer.parseInt(anime.getNumEpisodes()),
                        (e.getSource() == addToWatchingButton)
                                ? String.valueOf(Status.watching)
                                : String.valueOf(Status.plan_to_watch));
                myAnimeListIds.add(id);
            }
        }
        if (e.getSource() == myListButton) {
            myListButton.setEnabled(false);
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
        if (!searching) {
            if (!myAnimeListIds.contains(id)) {
                optionsPanelView.getAddToPlanToWatchButton().setEnabled(true);
                optionsPanelView.getAddToWatchingButton().setEnabled(true);
            } else {
                optionsPanelView.getAddToPlanToWatchButton().setEnabled(false);
                optionsPanelView.getAddToWatchingButton().setEnabled(false);
            }
        }
    }

    public void disableAddButtons() {
        optionsPanelView.getAddToPlanToWatchButton().setEnabled(false);
        optionsPanelView.getAddToWatchingButton().setEnabled(false);
    }

    public void disableButtons() {
        searching = true;
        optionsPanelView.getAddToPlanToWatchButton().setEnabled(false);
        optionsPanelView.getAddToWatchingButton().setEnabled(false);
        optionsPanelView.getNextPageButton().setEnabled(false);
        optionsPanelView.getPreviousPageButton().setEnabled(false);
        optionsPanelView.getMyListButton().setEnabled(false);
    }

    public void enableButtons() {
        searching = false;
        optionsPanelView.getPreviousPageButton().setEnabled(userPanelController.getOffset() > 0);
        optionsPanelView.getMyListButton().setEnabled(true);
    }
}
