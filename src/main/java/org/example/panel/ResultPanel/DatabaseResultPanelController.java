package org.example.panel.ResultPanel;

import static org.example.constants.DatabaseTableColumns.noteColumnId;
import static org.example.constants.DatabaseTableColumns.scoreColumnID;
import static org.example.constants.Resolutions.animeImageHeight;
import static org.example.constants.Resolutions.animeImageWidth;
import static org.example.constants.TableColumns.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.model.Controllers;
import org.example.model.MyAnime;
import org.example.model.Status;
import org.example.panel.OptionsPanel.OptionsPanelController;
import org.example.service.MySqlConnection;

@RequiredArgsConstructor
public class DatabaseResultPanelController {
    private final DatabaseResultPanelView databaseResultPanelView;

    public void rowSelected() {
        OptionsPanelController optionsPanelController = Controllers.optionsPanelController;
        JTable resultTable = databaseResultPanelView.getResultTable();
        if (!resultTable.getSelectionModel().isSelectionEmpty()) {
            int id = Integer.parseInt((String) resultTable.getValueAt(resultTable.getSelectedRow(), idColumnID));
            optionsPanelController.enableChangeButtons(id);
        } else {
            optionsPanelController.disableChangeButtons();
        }
    }

    public void noteChanged(FocusEvent e) {
        JTable table = databaseResultPanelView.getResultTable();
        if (e.getSource() == table) {
            int selectedColumn = table.getSelectedColumn();
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                if (selectedColumn == noteColumnId) {
                    Controllers.bodyPanelController
                            .getMySqlConnection()
                            .updateNote(Integer.parseInt((String) table.getValueAt(selectedRow, 0)), (String)
                                    table.getValueAt(selectedRow, selectedColumn));
                }
            }
        }
    }

    @SneakyThrows
    public void addDataToTable(List<MyAnime> animeList) {
        OptionsPanelController optionsPanelController = Controllers.optionsPanelController;
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

    public void listColumnChanged() {
        MySqlConnection mySqlConnection = Controllers.bodyPanelController.getMySqlConnection();
        JTable resultTable = databaseResultPanelView.getResultTable();
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow >= 0) {
            int selectedColumn = resultTable.getSelectedColumn();
            int id = Integer.parseInt((String) resultTable.getValueAt(selectedRow, idColumnID));
            if (selectedColumn == statusColumnID) {
                Status status = Status.fromString((String) resultTable.getValueAt(selectedRow, statusColumnID));
                mySqlConnection.updateStatus(id, Objects.requireNonNull(status));
            } else if (selectedColumn == scoreColumnID) {
                int score = (int) resultTable.getValueAt(selectedRow, scoreColumnID);
                mySqlConnection.updateScore(id, score);
            }
        }
    }
}
