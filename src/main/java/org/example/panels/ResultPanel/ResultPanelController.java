package org.example.panels.ResultPanel;

import static org.example.constants.Resolutions.animeImageHeight;
import static org.example.constants.Resolutions.animeImageWidth;
import static org.example.constants.TableColumns.idColumnID;
import static org.example.constants.TableColumns.imageColumnID;

import java.awt.*;
import java.net.URL;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.model.Anime;
import org.example.panels.OptionsPanel.OptionsPanelController;

@RequiredArgsConstructor
public class ResultPanelController {
    private final ResultPanelView resultPanelView;

    public void rowSelected() {
        OptionsPanelController optionsPanelController =
                resultPanelView.getBodyPanel().getUserPanel().getOptionsPanel().getOptionsPanelController();
        JTable resultTable = resultPanelView.getResultTable();
        if (!resultPanelView.getResultTable().getSelectionModel().isSelectionEmpty()) {
            int id = Integer.parseInt((String) resultTable.getValueAt(resultTable.getSelectedRow(), idColumnID));
            optionsPanelController.enableAddButtons(id);
        } else {
            optionsPanelController.disableAddButtons();
        }
    }

    @SneakyThrows
    public void addDataToTable(List<Anime> animeList) {
        OptionsPanelController optionsPanelController =
                resultPanelView.getBodyPanel().getUserPanel().getOptionsPanel().getOptionsPanelController();
        DefaultTableModel tableModel = resultPanelView.getTableModel();
        JTable resultTable = resultPanelView.getResultTable();
        // change color of anime row in database - Problem: can't highlight selected row
        //        DefaultTableCellRenderer renderer = new AddedAnimeRender(optionsPanelController);
        //        for (int j = 2; j < resultTable.getColumnCount(); j++) {
        //            resultTable.getColumnModel().getColumn(j).setCellRenderer(renderer);
        //        }
        tableModel.setRowCount(0);
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
        optionsPanelController.enableNextPageButton(animeList.size());
    }
}
