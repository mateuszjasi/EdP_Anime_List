package org.example.panels.OptionsPanel;

import static org.example.constants.AnimeSearchWindowResolutions.buttonHeight;
import static org.example.constants.AnimeSearchWindowResolutions.buttonWidth;
import static org.example.constants.Colors.*;
import static org.example.constants.Strings.*;
import static org.example.constants.Strings.searchIconPath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.example.panels.UserPanel.UserPanelController;
import org.example.panels.UserPanel.UserPanelView;

public class OptionsPanelView extends JPanel implements ActionListener, ChangeListener {
    private final UserPanelView parent;
    private final OptionsPanelController controller;
    private final JButton nextPageButton, previousPageButton, addToWatchingButton, addToPlanToWatchButton, myListButton;

    public OptionsPanelView(UserPanelView parent, UserPanelController parentController) {
        this.parent = parent;
        controller = new OptionsPanelController(this, parentController);
        nextPageButton = new JButton();
        previousPageButton = new JButton();
        addToWatchingButton = new JButton();
        addToPlanToWatchButton = new JButton();
        myListButton = new JButton();

        initPanel();
        initButton(nextPageButton, rightArrowPath);
        initButton(previousPageButton, leftArrowPath);
        initButton(addToWatchingButton, searchIconPath);
        initButton(addToPlanToWatchButton, searchIconPath);
        initButton(myListButton, searchIconPath);

        add(previousPageButton);
        add(addToWatchingButton);
        add(addToPlanToWatchButton);
        add(myListButton);
        add(nextPageButton);
    }

    private void initPanel() {
        setPreferredSize(new Dimension(parent.getWidth(), 75));
        setBackground(colorWhite);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
    }

    private void initButton(JButton button, String graphicPath) {
        button.setBackground(colorTeal);
        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        button.setIcon(new ImageIcon(graphicPath));
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorLightGray));
        button.addActionListener(this);
        button.setFocusable(false);
        if (button == nextPageButton || button == previousPageButton) {
            button.addChangeListener(this);
            button.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.newPage(e);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        controller.setPagingButtonColor(e);
    }

    public JButton getNextPageButton() {
        return nextPageButton;
    }

    public JButton getPreviousPageButton() {
        return previousPageButton;
    }

    public JButton getAddToWatchingButton() {
        return addToWatchingButton;
    }

    public JButton getAddToPlanToWatchButton() {
        return addToPlanToWatchButton;
    }

    public JButton getMyListButton() {
        return myListButton;
    }
}
