package org.example.panels.SearchPanel;

import static org.example.constants.AnimeSearchWindowResolutions.buttonHeight;
import static org.example.constants.AnimeSearchWindowResolutions.buttonWidth;
import static org.example.constants.Colors.*;
import static org.example.constants.Colors.colorLightGray;
import static org.example.constants.Fonts.searchFieldFont;
import static org.example.constants.Strings.idleSearchFieldText;
import static org.example.constants.Strings.searchIconPath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import org.example.panels.UserPanel.UserPanelController;
import org.example.panels.UserPanel.UserPanelView;

public class SearchPanelView extends JPanel implements ActionListener, FocusListener {
    private final UserPanelView parent;
    private final SearchPanelController controller;
    private UserPanelController parentController;
    private final JTextField searchAnimeTextField;
    private final JButton searchButton;

    public SearchPanelView(UserPanelView parent) {
        this.parent = parent;
        controller = new SearchPanelController(this, parentController);
        searchAnimeTextField = new JTextField(idleSearchFieldText);
        searchButton = new JButton();

        initPanel();
        initSearchField();
        initSearchButton();

        add(searchAnimeTextField);
        add(searchButton);
    }

    private void initPanel() {
        setPreferredSize(new Dimension(parent.getWidth(), 75));
        setBackground(colorWhite);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
    }

    private void initSearchField() {
        searchAnimeTextField.setPreferredSize(new Dimension(600, buttonHeight));
        searchAnimeTextField.setBackground(colorWhite);
        searchAnimeTextField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, colorLightGray));
        searchAnimeTextField.setFont(searchFieldFont);
        searchAnimeTextField.setForeground(colorLightGray);
        searchAnimeTextField.addFocusListener(this);
    }

    private void initSearchButton() {
        searchButton.setBackground(colorPink);
        searchButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        searchButton.setIcon(new ImageIcon(searchIconPath));
        searchButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorLightGray));
        searchButton.addActionListener(this);
        searchButton.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.searchAnime(e);
    }

    @Override
    public void focusGained(FocusEvent e) {
        controller.clearTextField(e, searchAnimeTextField);
    }

    @Override
    public void focusLost(FocusEvent e) {
        controller.fillTextField(e, searchAnimeTextField);
    }

    public void setParentController(UserPanelController parentController) {
        this.parentController = parentController;
    }

    public JTextField getSearchAnimeTextField() {
        return searchAnimeTextField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
