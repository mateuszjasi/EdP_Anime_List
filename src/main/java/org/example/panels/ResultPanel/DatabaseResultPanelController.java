package org.example.panels.ResultPanel;

import static org.example.constants.DatabaseTableColumns.noteColumnId;
import static org.example.constants.Resolutions.animeImageHeight;
import static org.example.constants.Resolutions.animeImageWidth;
import static org.example.constants.TableColumns.imageColumnID;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.net.URL;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.model.MyAnime;
import org.example.panels.OptionsPanel.OptionsPanelController;
import org.example.service.MySqlConnection;

@RequiredArgsConstructor
public class DatabaseResultPanelController {
    private final DatabaseResultPanelView databaseResultPanelView;

    public void rowSelected() {
        // do options panel first
    }

    public void columnValueChanged(FocusEvent e) {
        JTable table = databaseResultPanelView.getResultTable();
        if (e.getSource() != table) return;
        int selectedColumn = table.getSelectedColumn();
        int selectedRow = table.getSelectedRow();
        MySqlConnection mySqlConnection = databaseResultPanelView
                .getBodyPanelView()
                .getBodyPanelController()
                .getMySqlConnection();
        if (selectedRow != -1) {
            if (selectedColumn == noteColumnId) {
                mySqlConnection.updateNote(Integer.parseInt((String) table.getValueAt(selectedRow, 0)), (String)
                        table.getValueAt(selectedRow, selectedColumn));
            }
        }
    }

    @SneakyThrows
    public void addDataToTable(List<MyAnime> animeList) {
        OptionsPanelController optionsPanelController = databaseResultPanelView
                .getBodyPanelView()
                .getUserPanelView()
                .getOptionsPanelView()
                .getOptionsPanelController();
        DefaultTableModel tableModel = databaseResultPanelView.getTableModel();
        JTable resultTable = databaseResultPanelView.getResultTable();
        tableModel.setRowCount(0);
        for (int i = 0; i < animeList.size(); i++) {
            MyAnime anime = animeList.get(i);
            String progressString = anime.getProgress() + " / " + anime.getProgressMax();
            tableModel.addRow(new String[] {
                String.valueOf(anime.getId()),
                null,
                anime.getTitle(),
                anime.getStatus().getString(),
                String.valueOf(anime.getScore()),
                progressString,
                anime.getNote()
            });
            ImageIcon image = new ImageIcon(new URL(anime.getImageUrl()));
            Image scaledImage =
                    image.getImage().getScaledInstance(animeImageWidth, animeImageHeight, Image.SCALE_DEFAULT);
            resultTable.setValueAt(new ImageIcon(scaledImage), i, imageColumnID);
        }
        optionsPanelController.enableNextPageButton(animeList.size());
    }
}
