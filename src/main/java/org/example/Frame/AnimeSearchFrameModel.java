package org.example.Frame;

import static org.example.constants.Colors.colorBlue;
import static org.example.constants.Resolutions.*;
import static org.example.constants.Strings.appTitle;
import static org.example.constants.Strings.logoPath;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import lombok.Getter;
import org.example.panels.BodyPanel.BodyPanelView;
import org.example.panels.TitlePanel.TitlePanelView;

@Getter
public abstract class AnimeSearchFrameModel extends JFrame {
    protected JPanel marginPanel1;
    protected JPanel marginPanel2;
    protected JPanel marginPanel3;
    protected TitlePanelView titlePanel;
    protected BodyPanelView bodyPanel;

    protected void initFrame() {
        setTitle(appTitle);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(logoPath).getImage());
        setMinimumSize(new Dimension(mainWindowWidth, mainWindowHeight));
        setSize(mainWindowWidth, mainWindowHeight);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bodyPanel.getBodyPanelController().getMySqlConnection().closeConnection();
            }
        });
    }

    protected JPanel initMarginPanel(int width, int height) {
        JPanel marginPanel = new JPanel();
        marginPanel.setBackground(colorBlue);
        marginPanel.setPreferredSize(new Dimension(width, height));
        return marginPanel;
    }
}
