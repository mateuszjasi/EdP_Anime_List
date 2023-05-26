package org.example.Frame;

import static org.example.constants.Resolutions.*;
import static org.example.constants.Resolutions.mainWindowHeight;

import java.awt.*;
import org.example.panels.BodyPanel.BodyPanelView;
import org.example.panels.TitlePanel.TitlePanelView;

public class AnimeSearchFrameView extends AnimeSearchFrameModel {
    public AnimeSearchFrameView() {
        initFrame();

        marginPanel1 = initMarginPanel(marginWidth, mainWindowHeight);
        marginPanel2 = initMarginPanel(mainWindowWidth, marginHeight);
        marginPanel3 = initMarginPanel(marginWidth, mainWindowHeight);

        titlePanel = new TitlePanelView();
        bodyPanel = new BodyPanelView(titlePanel);

        add(marginPanel1, BorderLayout.WEST);
        add(marginPanel2, BorderLayout.SOUTH);
        add(marginPanel3, BorderLayout.EAST);
        add(titlePanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        revalidate();
    }
}
