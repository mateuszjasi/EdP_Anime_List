package org.example.panels.UserPanel;

import static org.example.constants.AnimeSearchWindowResolutions.animeImageHeight;
import static org.example.constants.AnimeSearchWindowResolutions.animeImageWidth;
import static org.example.constants.AnimeSearchWindowTableColumns.imageColumnID;

import java.awt.*;
import java.net.URL;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import lombok.SneakyThrows;
import org.example.model.Anime;
import org.example.panels.ResultPanel.ResultPanelView;
import org.example.panels.SearchPanel.SearchPanelView;
import org.example.service.AnimeService;

public class UserPanelController {
    private final SearchPanelView searchPanel;
    private final AnimeService animeService;
    private final ResultPanelView resultPanel;
    private int offset;

    public UserPanelController(
            SearchPanelView searchPanel, ResultPanelView resultPanel) {
        animeService = new AnimeService();
        offset = 0;
        this.searchPanel = searchPanel;
        this.resultPanel = resultPanel;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @SneakyThrows
    public int searchAnime() {
        String animeTitle = searchPanel.getSearchAnimeTextField().toString();
        DefaultTableModel tableModel = resultPanel.getTableModel();
        JTable resultTable = resultPanel.getResultTable();
        tableModel.setRowCount(0);
        List<Anime> animeList = animeService.getAnimeFromTitle(animeTitle, offset);
        for (int i = 0; i < animeList.size(); i++) {
            Anime anime = animeList.get(i);
            tableModel.addRow(new String[] {
                String.valueOf(anime.getId()),
                null,
                anime.getTitle(),
                anime.getStatus().getString(),
                anime.getNumEpisodes(),
                anime.getMean()
            });
            ImageIcon image = new ImageIcon(new URL(anime.getImageUrl()));
            Image scaledImage =
                    image.getImage().getScaledInstance(animeImageWidth, animeImageHeight, Image.SCALE_DEFAULT);
            resultTable.setValueAt(new ImageIcon(scaledImage), i, imageColumnID);
        }
        return animeList.size();
    }
}
