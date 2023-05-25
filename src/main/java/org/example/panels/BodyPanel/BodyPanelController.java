package org.example.panels.BodyPanel;

import java.awt.*;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.panels.ResultPanel.DatabaseResultPanelController;
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
        if (dataFromApiMode) {
            dataFromApiMode = false;
            bodyPanelView.remove(bodyPanelView.getApiResultPanelView());
            bodyPanelView.add(bodyPanelView.getDatabaseResultPanelView(), BorderLayout.CENTER);
        } else {
            dataFromApiMode = true;
            bodyPanelView.remove(bodyPanelView.getDatabaseResultPanelView());
            bodyPanelView.add(bodyPanelView.getApiResultPanelView(), BorderLayout.CENTER);
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
                .getSearchAnimeTextField()
                .getText();
        databaseResultPanelController.addDataToTable(mySqlConnection.getMyAnimeList(title, mySqlConnectionOffset));
    }
}
