package org.example.panel.TitlePanel;

public class TitlePanelView extends TitlePanelModel {
    public TitlePanelView() {
        initTitlePanel();

        titleLabel = initTitleLabel();

        add(titleLabel);
    }
}
