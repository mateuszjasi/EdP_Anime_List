package org.example.Frame;

import static org.example.constants.Resolutions.*;
import static org.example.constants.Colors.colorBlue;
import static org.example.constants.Strings.appTitle;
import static org.example.constants.Strings.logoPath;

import java.awt.*;
import javax.swing.*;
import lombok.Getter;

@Getter
public abstract class AnimeSearchFrameModel extends JFrame {
    protected JPanel marginPanel1;
    protected JPanel marginPanel2;
    protected JPanel marginPanel3;

    protected void initFrame() {
        setTitle(appTitle);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(logoPath).getImage());
        setMinimumSize(new Dimension(mainWindowWidth, mainWindowHeight));
        setSize(mainWindowWidth, mainWindowHeight);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());
    }

    protected JPanel initMarginPanel(int width, int height) {
        JPanel marginPanel = new JPanel();
        marginPanel.setBackground(colorBlue);
        marginPanel.setPreferredSize(new Dimension(width, height));
        return marginPanel;
    }
}
