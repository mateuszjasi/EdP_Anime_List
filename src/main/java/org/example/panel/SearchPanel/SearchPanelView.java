package org.example.panel.SearchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import org.example.model.Controllers;
import org.example.model.Views;
import org.example.panel.UserPanel.UserPanelView;

public class SearchPanelView extends SearchPanelModel implements ActionListener, FocusListener {
    public SearchPanelView(UserPanelView userPanel) {
        this.userPanelView = userPanel;
        searchPanelController = new SearchPanelController(this);
        Views.searchPanelView = this;
        Controllers.searchPanelController = searchPanelController;

        searchButton = initSearchButton();
        searchProgressBar = initProgressBar();
        searchAnimeTextField = initSearchField();

        initPanel();

        add(searchAnimeTextField);
        add(searchButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchPanelController.searchAnime(e);
    }

    @Override
    public void focusGained(FocusEvent e) {
        searchPanelController.clearTextField(e, searchAnimeTextField);
    }

    @Override
    public void focusLost(FocusEvent e) {
        searchPanelController.fillTextField(e, searchAnimeTextField);
    }
}
