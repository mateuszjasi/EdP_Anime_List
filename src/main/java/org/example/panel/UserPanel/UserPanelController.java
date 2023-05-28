package org.example.panel.UserPanel;

import java.util.List;
import javax.swing.*;
import lombok.*;
import org.example.model.Anime;
import org.example.panel.OptionsPanel.OptionsPanelController;
import org.example.panel.ResultPanel.ApiResultPanelController;
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
        ApiResultPanelController apiResultPanelController =
                userPanelView.getBodyPanelView().getApiResultPanelView().getApiResultPanelController();
        SearchPanelController searchPanelController =
                userPanelView.getSearchPanelView().getSearchPanelController();
        OptionsPanelController optionsPanelController =
                userPanelView.getOptionsPanelView().getOptionsPanelController();
        String animeTitle = searchPanelController.getApiSearchTitle();

        SwingWorker<List<Anime>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Anime> doInBackground() {
                return animeService.getAnimeFromTitle(
                        animeTitle, offset, userPanelView.getSearchPanelView().getSearchPanelController());
            }

            @Override
            @SneakyThrows
            protected void done() {
                List<Anime> animeList = get();
                apiResultPanelController.addDataToTable(animeList);
                searchPanelController.stopProgressBar();
                optionsPanelController.enableButtons();
            }
        };
        searchPanelController.startProgressBar();
        worker.execute();
        optionsPanelController.disableButtons();
    }
}
