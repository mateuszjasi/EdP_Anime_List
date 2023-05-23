package org.example.panels.SearchPanel;

import static org.example.constants.AnimeSearchWindowResolutions.buttonHeight;
import static org.example.constants.AnimeSearchWindowResolutions.buttonWidth;
import static org.example.constants.Colors.*;
import static org.example.constants.Colors.colorLightGray;
import static org.example.constants.Fonts.searchFieldFont;
import static org.example.constants.Strings.idleSearchFieldText;
import static org.example.constants.Strings.searchIconPath;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.*;
import lombok.Getter;
import org.example.panels.UserPanel.UserPanelView;

@Getter
public abstract class SearchPanelModel extends JPanel {
    protected UserPanelView userPanel;
    protected SearchPanelController searchPanelController;
    protected JTextField searchAnimeTextField;
    protected JButton searchButton;
    protected JProgressBar searchProgressBar;

    protected void initPanel() {
        setPreferredSize(new Dimension(userPanel.getWidth(), userPanel.getHeight() / 2));
        setBackground(colorWhite);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
    }

    protected JProgressBar initProgressBar() {
        searchProgressBar = new JProgressBar();
        searchProgressBar.setPreferredSize(new Dimension(600, buttonHeight));
        searchProgressBar.setIndeterminate(false);
        searchProgressBar.setMaximum(10);
        return searchProgressBar;
    }

    protected JTextField initSearchField() {
        JTextField textField = new JTextField(idleSearchFieldText);
        textField.setPreferredSize(new Dimension(600, buttonHeight));
        textField.setBackground(colorWhite);
        textField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, colorLightGray));
        textField.setFont(searchFieldFont);
        textField.setForeground(colorLightGray);
        textField.addFocusListener((FocusListener) this);
        return textField;
    }

    protected JButton initSearchButton() {
        JButton button = new JButton();
        button.setBackground(colorPink);
        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        button.setIcon(new ImageIcon(searchIconPath));
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorLightGray));
        button.addActionListener((ActionListener) this);
        button.setFocusable(false);
        return button;
    }
}
