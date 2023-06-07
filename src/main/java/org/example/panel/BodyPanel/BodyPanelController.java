package org.example.panel.BodyPanel;

import java.awt.*;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.model.Controllers;
import org.example.model.MyAnime;
import org.example.model.Views;
import org.example.panel.OptionsPanel.OptionsPanelController;
import org.example.panel.ResultPanel.ApiResultPanelView;
import org.example.panel.ResultPanel.DatabaseResultPanelView;
import org.example.service.MySqlConnection;

@Getter
@Setter
@RequiredArgsConstructor
public class BodyPanelController {
    private final BodyPanelView bodyPanelView;
    private final MySqlConnection mySqlConnection = new MySqlConnection();
    private final List<Integer> myAnimeListIds = mySqlConnection.getMyAnimeListIds();
    public int mySqlConnectionOffset;
    public boolean dataFromApiMode = true;

    public void changeMode() {
        ApiResultPanelView apiResultPanelView = Views.apiResultPanelView;
        DatabaseResultPanelView databaseResultPanelView = Views.databaseResultPanelView;
        if (dataFromApiMode) {
            dataFromApiMode = false;
            bodyPanelView.remove(apiResultPanelView);
            bodyPanelView.add(databaseResultPanelView, BorderLayout.CENTER);
            searchAnimeDatabase();
        } else {
            dataFromApiMode = true;
            bodyPanelView.remove(databaseResultPanelView);
            bodyPanelView.add(apiResultPanelView, BorderLayout.CENTER);
        }
        bodyPanelView.revalidate();
        bodyPanelView.repaint();
    }

    public void searchAnimeDatabase() {
        OptionsPanelController optionsPanelController = Controllers.optionsPanelController;
        String title = Controllers.searchPanelController.getDatabaseSearchTitle();
        if (title.equals("Search Anime...")) title = "";
        List<MyAnime> animeList = mySqlConnection.getMyAnimeList(title, mySqlConnectionOffset);
        optionsPanelController.enableNextPageButton(animeList.size() >= 10
                && !mySqlConnection
                        .getMyAnimeList(title, mySqlConnectionOffset + 10, 1)
                        .isEmpty());
        optionsPanelController.enablePreviousPageButton(mySqlConnectionOffset > 0);
        Controllers.databaseResultPanelController.addDataToTable(animeList);
    }

    public void removeAnime(int removedId) {
        mySqlConnection.removeAnime(removedId);
        myAnimeListIds.remove(Integer.valueOf(removedId));
        searchAnimeDatabase();
    }
}
