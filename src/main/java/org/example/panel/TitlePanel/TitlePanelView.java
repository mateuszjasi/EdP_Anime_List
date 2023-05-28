package org.example.panel.TitlePanel;

import org.example.model.Views;

public class TitlePanelView extends TitlePanelModel {
    public TitlePanelView() {
        initTitlePanel();
        Views.titlePanelView = this;

        titleLabel = initTitleLabel();

        add(titleLabel);
    }
}
