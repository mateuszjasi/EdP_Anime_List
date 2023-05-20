package org.example.AnimeSearchFrame;

import static org.example.constants.AnimeSearchWindowResolutions.*;
import static org.example.constants.Colors.*;
import static org.example.constants.Strings.*;

import java.awt.*;
import javax.swing.*;
import org.example.panels.BodyPanel.BodyPanelView;
import org.example.panels.TitlePanel.TitlePanelView;

public class AnimeSearchFrameView extends JFrame {
    private final JPanel marginPanel1 = new JPanel(), marginPanel2 = new JPanel(), marginPanel3 = new JPanel();

    public AnimeSearchFrameView() {
        initFrame();
        initMarginPanels();

        JPanel titlePanel = new TitlePanelView();
        JPanel bodyPanel = new BodyPanelView(marginPanel1, titlePanel);

        add(marginPanel1, BorderLayout.WEST);
        add(marginPanel2, BorderLayout.SOUTH);
        add(marginPanel3, BorderLayout.EAST);
        add(titlePanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        revalidate();
    }

    private void initFrame() {
        setTitle(appTitle);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(logoPath).getImage());
        setMinimumSize(new Dimension(mainWindowWidth, mainWindowHeight));
        setSize(mainWindowWidth, mainWindowHeight);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());
    }

    private void initMarginPanels() {
        marginPanel1.setBackground(colorBlue);
        marginPanel1.setPreferredSize(new Dimension(15, mainWindowHeight));
        marginPanel2.setBackground(colorBlue);
        marginPanel2.setPreferredSize(new Dimension(mainWindowWidth, 15));
        marginPanel3.setBackground(colorBlue);
        marginPanel3.setPreferredSize(new Dimension(15, mainWindowHeight));
    }
}
