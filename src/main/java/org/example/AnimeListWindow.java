package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.util.List;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import lombok.SneakyThrows;
import org.example.model.Anime;
import org.example.service.AnimeService;

public class AnimeListWindow extends JFrame implements ActionListener, FocusListener {
    private final Color colorBlue = Color.decode("#394867");
    private final Color colorDarkBlue = Color.decode("#212A3E");
    private final Color colorWhite = Color.decode("#F1F6F9");
    private final Color colorPink = Color.decode("#FC5185");
    private final Color colorGray = Color.decode("#9BA4B5");
    private JTextField searchAnimeTextField;
    private JButton searchButton;
    private final AnimeService animeService;
    private int offset;
    private final String[] tableHeaders;
    private DefaultTableModel tableModel;
    private JTable resultTable;

    public AnimeListWindow() {
        tableHeaders = new String[] {"Id", "Image", "Title", "Status", "Eps.", "Score"};
        animeService = new AnimeService();
        offset = 0;
        initGUI();
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        JPanel bodyPanel = new JPanel();
        JLabel titleLabel = new JLabel();
        searchAnimeTextField = new JTextField("Search Anime...");
        searchButton = new JButton();
        JPanel marginPanel1 = new JPanel(), marginPanel2 = new JPanel(), marginPanel3 = new JPanel();

        setTitle("My Anime List");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("src/main/java/org/example/logo.jpg").getImage());
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());

        titleLabel.setText("Anime Search");
        titleLabel.setForeground(colorWhite);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setHorizontalAlignment(JLabel.LEFT);
        titleLabel.setBorder(new CompoundBorder(titleLabel.getBorder(), new EmptyBorder(15, 15, 15, 15)));

        mainPanel.setPreferredSize(new Dimension(1920, 80));
        mainPanel.setBackground(colorBlue);
        mainPanel.setLayout(new BorderLayout());

        bodyPanel.setBackground(Color.decode("#F5F5F5"));
        bodyPanel.setPreferredSize(new Dimension(1890, 985));
        bodyPanel.setLayout(new BorderLayout());

        marginPanel1.setBackground(colorBlue);
        marginPanel1.setPreferredSize(new Dimension(15, 1080));
        marginPanel2.setBackground(colorBlue);
        marginPanel2.setPreferredSize(new Dimension(1920, 15));
        marginPanel3.setBackground(colorBlue);
        marginPanel3.setPreferredSize(new Dimension(15, 1080));

        searchPanel.setPreferredSize(new Dimension(1890, 100));
        searchPanel.setBackground(colorGray);
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

        resultPanel.setPreferredSize(new Dimension(1890, 885));
        resultPanel.setLayout(new BorderLayout());

        searchAnimeTextField.setPreferredSize(new Dimension(600, 40));
        searchAnimeTextField.setBackground(colorWhite);
        searchAnimeTextField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, colorDarkBlue));
        searchAnimeTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchAnimeTextField.setForeground(Color.LIGHT_GRAY);
        searchAnimeTextField.addFocusListener(this);

        searchButton.setBackground(colorPink);
        searchButton.setPreferredSize(new Dimension(50, 40));
        searchButton.setIcon(new ImageIcon("src/main/java/org/example/search_icon.png"));
        searchButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorDarkBlue));
        searchButton.addActionListener(this);
        searchButton.setFocusable(false);

        tableModel = new DefaultTableModel(null, tableHeaders);
        resultTable = new JTable(tableModel);
        resultTable.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
        resultTable.setRowHeight(225);
        // resultTable.setPreferredSize(new Dimension(1800, 800));

        JScrollPane scrollPane = new JScrollPane(resultTable);
        // scrollPane.setPreferredSize(new Dimension(1800, 800));

        mainPanel.add(titleLabel);
        searchPanel.add(searchAnimeTextField);
        searchPanel.add(searchButton);
        resultPanel.add(scrollPane, BorderLayout.CENTER);
        bodyPanel.add(searchPanel, BorderLayout.NORTH);
        bodyPanel.add(resultPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.NORTH);
        add(marginPanel1, BorderLayout.WEST);
        add(marginPanel2, BorderLayout.SOUTH);
        add(marginPanel3, BorderLayout.EAST);
        add(bodyPanel, BorderLayout.CENTER);
        revalidate();
    }

    @SneakyThrows
    private void searchAnime(String animeTitle) {
        offset = 0;
        tableModel.setRowCount(0);
        List<Anime> animeList = animeService.getAnimeFromTitle(animeTitle, offset);
        // System.out.println(animeList.get(0).getId());
        for (int i = 0; i < animeList.size(); i++) {
            tableModel.addRow(new String[] {
                String.valueOf(animeList.get(i).getId()),
                null,
                animeList.get(i).getTitle(),
                animeList.get(i).getStatus().getString(),
                animeList.get(i).getNumEpisodes(),
                animeList.get(i).getMean()
            });
            ImageIcon image = new ImageIcon(new URL(animeList.get(i).getImageUrl()));
            Image scaledImage = image.getImage().getScaledInstance(150, 225, Image.SCALE_DEFAULT);
            resultTable.setValueAt(new ImageIcon(scaledImage), i, 1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchAnime(searchAnimeTextField.getText());
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == searchAnimeTextField
                && searchAnimeTextField.getText().equals("Search Anime...")) {
            searchAnimeTextField.setText("");
            searchAnimeTextField.setForeground(colorDarkBlue);
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
