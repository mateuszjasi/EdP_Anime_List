package org.example;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;

public class AnimeListWindow extends JFrame {
    private JPanel MainPanel;
    private JTextField searchAnimeTextField;
    private JPanel titlePanel;
    private JButton searchButton;
    private JPanel searchPanel;

    public AnimeListWindow() {
        setContentPane(MainPanel);
        setTitle("My anime manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon app_icon = new ImageIcon("/logo.jpg");
        setIconImage(app_icon.getImage());
        titlePanel.setBackground(Color.decode("#364F6B"));
        searchPanel.setBackground(Color.decode("#F5F5F5"));
        searchAnimeTextField.setBackground(Color.decode("#F5F5F5"));
        searchAnimeTextField.setMargin(new Insets(0, 5, 0, 0));
        searchAnimeTextField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.decode("#364F6B")));
        searchButton.setBackground(Color.decode("#FC5185"));
        searchButton.setFocusable(false);
        Icon search_icon = new ImageIcon("search_icon.png");
        searchButton.setIcon(search_icon);
        searchButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#364F6B")));
        searchAnimeTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchAnimeTextField.getText().equals("Search Anime...")) {
                    searchAnimeTextField.setText("");
                    searchAnimeTextField.setForeground(Color.decode("#364F6B"));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchAnimeTextField.getText().isEmpty()) {
                    searchAnimeTextField.setText("Search Anime...");
                    searchAnimeTextField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }
}
