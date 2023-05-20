package org.example.panels.UserPanel;

import java.awt.*;
import javax.swing.*;
import org.example.panels.BodyPanel.BodyPanelView;
import org.example.panels.OptionsPanel.OptionsPanelView;
import org.example.panels.ResultPanel.ResultPanelView;
import org.example.panels.SearchPanel.SearchPanelView;

public class UserPanelView extends JPanel {
    private final BodyPanelView parent;
    private final SearchPanelView searchPanel;
    private final OptionsPanelView optionsPanel;

    public UserPanelView(BodyPanelView parent, ResultPanelView resultPanel) {
        this.parent = parent;
        UserPanelController controller = new UserPanelController(this, resultPanel);
        searchPanel = new SearchPanelView(this, controller);
        optionsPanel = new OptionsPanelView(this, controller);

        initPanel();

        add(searchPanel);
        add(optionsPanel);
    }

    private void initPanel() {
        setPreferredSize(new Dimension(parent.getWidth(), 150));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public SearchPanelView getSearchPanel() {
        return searchPanel;
    }

    public OptionsPanelView getOptionsPanel() {
        return optionsPanel;
    }
}
