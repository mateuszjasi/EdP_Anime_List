package org.example.panels.UserPanel;

import java.util.List;
import javax.swing.*;
import lombok.*;
import org.example.model.Anime;
import org.example.panels.OptionsPanel.OptionsPanelController;
import org.example.panels.ResultPanel.ResultPanelController;
import org.example.panels.SearchPanel.SearchPanelController;
import org.example.service.AnimeService;

@Getter
@Setter
@RequiredArgsConstructor
public class UserPanelController {
    private final UserPanelView userPanelView;
    private final AnimeService animeService = new AnimeService();
    private int offset;

    public void searchAnime() {
        ResultPanelController resultPanelController =
                userPanelView.getBodyPanel().getResultPanel().getResultPanelController();
        SearchPanelController searchPanelController =
                userPanelView.getSearchPanel().getSearchPanelController();
        OptionsPanelController optionsPanelController =
                userPanelView.getOptionsPanel().getOptionsPanelController();
        String animeTitle =
                userPanelView.getSearchPanel().getSearchAnimeTextField().getText();

        SwingWorker<List<Anime>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Anime> doInBackground() {
                return animeService.getAnimeFromTitle(
                        animeTitle, offset, userPanelView.getSearchPanel().getSearchPanelController());
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

        searchPanelController.startProgressBar();
        optionsPanelController.disableButtons();
        worker.execute();
    }
}
