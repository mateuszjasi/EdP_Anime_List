package org.example.panels.UserPanel;

import java.util.List;
import javax.swing.*;
import lombok.SneakyThrows;
import org.example.model.Anime;
import org.example.panels.OptionsPanel.OptionsPanelController;
import org.example.panels.ResultPanel.ResultPanelController;
import org.example.panels.SearchPanel.SearchPanelController;
import org.example.service.AnimeService;

public class UserPanelController {
    private final UserPanelView view;
    private final AnimeService animeService;
    private int offset;

    public UserPanelController(UserPanelView view) {
        animeService = new AnimeService();
        offset = 0;
        this.view = view;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void searchAnime() {
        ResultPanelController resultPanelController =
                view.getUserParent().getResultPanel().getController();
        SearchPanelController searchPanelController = view.getSearchPanel().getController();
        OptionsPanelController optionsPanelController = view.getOptionsPanel().getController();
        SwingWorker<List<Anime>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Anime> doInBackground() {
                String animeTitle =
                        view.getSearchPanel().getSearchAnimeTextField().getText();
                return animeService.getAnimeFromTitle(
                        animeTitle, offset, view.getSearchPanel().getController());
            }

            @Override
            @SneakyThrows
            protected void done() {
                List<Anime> animeList = get();
                resultPanelController.addDataToTable(animeList);
                searchPanelController.stopProgressBar();
                optionsPanelController.enableButtons();
            }
        };
        worker.execute();
        searchPanelController.startProgressBar();
        optionsPanelController.disableButtons();
    }

    public Anime getAnime(int id) {
        return animeService.getAnimeFromId(id);
    }
}
