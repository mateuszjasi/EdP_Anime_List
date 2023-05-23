package org.example.panels.OptionsPanel;

import static org.example.constants.AnimeSearchWindowTableColumns.*;
import static org.example.constants.Colors.colorLightGray;
import static org.example.constants.Colors.colorTeal;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import lombok.Getter;
import org.example.model.Anime;
import org.example.model.Status;
import org.example.panels.UserPanel.UserPanelController;
import org.example.service.MySqlConnection;

@Getter
public class OptionsPanelController {
    private final UserPanelController parentController;
    private final OptionsPanelView view;
    private final MySqlConnection database;
    private final List<Integer> myAnimeListIds;

    public OptionsPanelController(OptionsPanelView view, UserPanelController parentController) {
        this.parentController = parentController;
        this.view = view;
        this.database = new MySqlConnection();
        myAnimeListIds = database.getMyAnimeListIds();
    }

    public void buttonClicked(ActionEvent e) {
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
            parentController.searchAnime();
        }
        if (e.getSource() == addToWatchingButton || e.getSource() == addToPlanToWatchButton) {
            addToWatchingButton.setEnabled(false);
            addToPlanToWatchButton.setEnabled(false);
            JTable resultTable =
                    view.getUserPanel().getBodyPanel().getResultPanel().getResultTable();
            int selectedRow = resultTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = Integer.parseInt((String) resultTable.getValueAt(selectedRow, idColumnID));
                Anime anime = view.getUserPanel().getController().getAnime(id);
                database.addAnime(
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
        view.getNextPageButton().setEnabled(animeListSize >= 10);
    }

    public void setButtonsColor(ChangeEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground((!button.isEnabled()) ? colorLightGray : colorTeal);
    }

    public void enableAddButtons(int id) {
        if (!myAnimeListIds.contains(id)) {
            view.getAddToPlanToWatchButton().setEnabled(true);
            view.getAddToWatchingButton().setEnabled(true);
        } else {
            disableAddButtons();
        }
    }

    public void disableAddButtons() {
        view.getAddToPlanToWatchButton().setEnabled(false);
        view.getAddToWatchingButton().setEnabled(false);
    }

    public void disableButtons() {
        view.getAddToPlanToWatchButton().setEnabled(false);
        view.getAddToWatchingButton().setEnabled(false);
        view.getNextPageButton().setEnabled(false);
        view.getPreviousPageButton().setEnabled(false);
        view.getMyListButton().setEnabled(false);
    }

    public void enableButtons() {
        view.getPreviousPageButton().setEnabled(parentController.getOffset() > 0);
        view.getMyListButton().setEnabled(true);
    }
}
