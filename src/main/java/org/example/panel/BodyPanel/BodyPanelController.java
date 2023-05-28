package org.example.panel.BodyPanel;

import java.awt.*;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.panel.ResultPanel.ApiResultPanelView;
import org.example.panel.ResultPanel.DatabaseResultPanelController;
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
        DatabaseResultPanelView databaseResultPanelView = bodyPanelView.getDatabaseResultPanelView();
        ApiResultPanelView apiResultPanelView = bodyPanelView.getApiResultPanelView();
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
        DatabaseResultPanelController databaseResultPanelController =
                bodyPanelView.getDatabaseResultPanelView().getDatabaseResultPanelController();
        String title = bodyPanelView
                .getUserPanelView()
                .getSearchPanelView()
                .getSearchPanelController()
                .getDatabaseSearchTitle();
        if (title.equals("Search Anime...")) title = "";
        databaseResultPanelController.addDataToTable(mySqlConnection.getMyAnimeList(title, mySqlConnectionOffset));
    }

    public void removeAnime(int removedId) {
        mySqlConnection.removeAnime(removedId);
        myAnimeListIds.remove(Integer.valueOf(removedId));
        searchAnimeDatabase();
    }
}
