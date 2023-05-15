package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class AnimeListWindow extends JFrame implements ActionListener, FocusListener {

    private JTextField searchAnimeTextField;
    private JButton searchButton;
    private JScrollPane scrollPane;

    public AnimeListWindow() {
        initGUI();
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        JPanel bodyPanel = new JPanel();
        searchAnimeTextField = new JTextField("Search Anime...");
        searchButton = new JButton();
        JLabel titleLabel = new JLabel();
        scrollPane = new JScrollPane();
        JPanel panel1 = new JPanel(), panel2 = new JPanel(), panel3 = new JPanel();

        setTitle("My Anime List");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("src/main/java/org/example/logo.jpg").getImage());
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        // setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout());

        titleLabel.setText("Anime Search");
        titleLabel.setForeground(Color.decode("#F1F6F9"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setHorizontalAlignment(JLabel.LEFT);
        titleLabel.setBorder(new CompoundBorder(titleLabel.getBorder(), new EmptyBorder(15, 15, 15, 15)));

        mainPanel.setPreferredSize(new Dimension(1920, 80));
        mainPanel.setBackground(Color.decode("#394867"));
        mainPanel.setLayout(new BorderLayout());

        bodyPanel.setBackground(Color.decode("#F5F5F5"));
        bodyPanel.setPreferredSize(new Dimension(1890, 985));
        bodyPanel.setLayout(new BorderLayout());

        panel1.setBackground(Color.decode("#394867"));
        panel1.setPreferredSize(new Dimension(15, 1080));
        panel2.setBackground(Color.decode("#394867"));
        panel2.setPreferredSize(new Dimension(1920, 15));
        panel3.setBackground(Color.decode("#394867"));
        panel3.setPreferredSize(new Dimension(15, 1080));

        searchPanel.setPreferredSize(new Dimension(1890, 100));
        searchPanel.setBackground(Color.decode("#9BA4B5"));
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

        resultPanel.setPreferredSize(new Dimension(1890, 885));
        resultPanel.setBackground(Color.decode("#F1F6F9"));

        searchAnimeTextField.setPreferredSize(new Dimension(600, 40));
        searchAnimeTextField.setBackground(Color.decode("#F1F6F9"));
        searchAnimeTextField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.decode("#364F6B")));
        searchAnimeTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchAnimeTextField.setForeground(Color.LIGHT_GRAY);
        searchAnimeTextField.addFocusListener(this);

        searchButton.setBackground(Color.decode("#FC5185"));
        searchButton.setPreferredSize(new Dimension(50, 40));
        searchButton.setIcon(new ImageIcon("src/main/java/org/example/search_icon.png"));
        searchButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#364F6B")));
        searchButton.addActionListener(this);

        mainPanel.add(titleLabel);
        searchPanel.add(searchAnimeTextField);
        searchPanel.add(searchButton);
        bodyPanel.add(searchPanel, BorderLayout.NORTH);
        bodyPanel.add(resultPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.NORTH);
        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.SOUTH);
        add(panel3, BorderLayout.EAST);
        add(bodyPanel, BorderLayout.CENTER);
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchAnimeTextField.getText();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == searchAnimeTextField
                && searchAnimeTextField.getText().equals("Search Anime...")) {
            searchAnimeTextField.setText("");
            searchAnimeTextField.setForeground(Color.decode("#364F6B"));
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == searchAnimeTextField
                && searchAnimeTextField.getText().isEmpty()) {
            searchAnimeTextField.setText("Search Anime...");
            searchAnimeTextField.setForeground(Color.LIGHT_GRAY);
        }
    }
}
