package org.example.panel.UserPanel;

import java.util.List;
import javax.swing.*;
import lombok.*;
import org.example.model.Anime;
import org.example.model.Controllers;
import org.example.panel.OptionsPanel.OptionsPanelController;
import org.example.panel.SearchPanel.SearchPanelController;
import org.example.service.AnimeService;

@Getter
@Setter
@RequiredArgsConstructor
public class UserPanelController {
    private final UserPanelView userPanelView;
    private final AnimeService animeService = new AnimeService();
    private int offset;

    public void searchAnime() {
        OptionsPanelController optionsPanelController = Controllers.optionsPanelController;
        SearchPanelController searchPanelController = Controllers.searchPanelController;
        String animeTitle = searchPanelController.getApiSearchTitle();
        SwingWorker<List<Anime>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Anime> doInBackground() {
                return animeService.getAnimeFromTitle(animeTitle, offset);
            }

            @Override
            @SneakyThrows
            protected void done() {
                List<Anime> animeList = get();
                searchPanelController.stopProgressBar();
                optionsPanelController.enableButtons();
                optionsPanelController.enableNextPageButton(animeList.size() >= 10
                        && !animeService
                                .getAnimeFromTitle(animeTitle, offset + 10, 1)
                                .isEmpty());
                Controllers.apiResultPanelController.addDataToTable(animeList);
            }
        };
        searchPanelController.startProgressBar();
        worker.execute();
        optionsPanelController.disableButtons();
    }
}
