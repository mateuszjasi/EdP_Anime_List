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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import lombok.SneakyThrows;
import org.example.model.Anime;
import org.example.service.AnimeService;

public class AnimeListWindow extends JFrame implements ActionListener, FocusListener, ChangeListener {
    private static final Color colorBlue = Color.decode("#394867");
    private static final Color colorLightGray = Color.LIGHT_GRAY;
    private static final Color colorPink = Color.decode("#FC5185");
    private static final Color colorWhite = Color.decode("#F5F5F5");
    private static final Color colorTeal = Color.decode("#3FC1C9");
    private static final int animeImageHeight = 220, animeImageWidth = 150;
    private static final int buttonWidth = 50, buttonHeight = 40;
    private static final int idColumnID = 0, imageColumnID = 1, titleColumnID = 2;
    private JTextField searchAnimeTextField;
    private JButton searchButton, nextPageButton, previousPageButton;
    private final AnimeService animeService;
    private int offset;
    private final String[] tableHeaders;
    private DefaultTableModel tableModel;
    private JTable resultTable;

    public AnimeListWindow() {
        tableHeaders = new String[] {"Id", "Image", "Title", "Status", "Eps.", "Score"};
        animeService = new AnimeService();
        offset = 0;
        setTitle("My Anime Searcher");
        initGUI();
    }

    private void initGUI() {
        JPanel titlePanel = new JPanel();
        JPanel userPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel optionsPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        JPanel bodyPanel = new JPanel();
        JLabel titleLabel = new JLabel();
        searchAnimeTextField = new JTextField("Search Anime...");
        searchButton = new JButton();
        nextPageButton = new JButton();
        previousPageButton = new JButton();
        JPanel marginPanel1 = new JPanel(), marginPanel2 = new JPanel(), marginPanel3 = new JPanel();
        JPanel insideMarginPanel1 = new JPanel(), insideMarginPanel2 = new JPanel(), insideMarginPanel3 = new JPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("src/main/java/org/example/logo.jpg").getImage());
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());

        titleLabel.setText("Anime Search");
        titleLabel.setForeground(colorWhite);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.LEFT);
        titleLabel.setBorder(new CompoundBorder(titleLabel.getBorder(), new EmptyBorder(15, 15, 15, 15)));

        titlePanel.setPreferredSize(new Dimension(getWidth(), 80));
        titlePanel.setBackground(colorBlue);
        titlePanel.setLayout(new BorderLayout());

        marginPanel1.setBackground(colorBlue);
        marginPanel1.setPreferredSize(new Dimension(15, getHeight()));
        marginPanel2.setBackground(colorBlue);
        marginPanel2.setPreferredSize(new Dimension(getWidth(), 15));
        marginPanel3.setBackground(colorBlue);
        marginPanel3.setPreferredSize(new Dimension(15, getHeight()));

        bodyPanel.setBackground(colorWhite);
        bodyPanel.setPreferredSize(
                new Dimension(getWidth() - marginPanel1.getWidth() * 2, getHeight() - titlePanel.getHeight() - 15));
        bodyPanel.setLayout(new BorderLayout());

        insideMarginPanel1.setBackground(colorWhite);
        insideMarginPanel1.setPreferredSize(new Dimension(30, bodyPanel.getHeight()));
        insideMarginPanel2.setBackground(colorWhite);
        insideMarginPanel2.setPreferredSize(new Dimension(30, bodyPanel.getHeight()));
        insideMarginPanel3.setBackground(colorWhite);
        insideMarginPanel3.setPreferredSize(new Dimension(bodyPanel.getWidth(), 30));

        resultPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 785));
        resultPanel.setLayout(new BorderLayout());

        userPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 150));
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

        searchPanel.setPreferredSize(new Dimension(userPanel.getWidth(), 75));
        searchPanel.setBackground(colorWhite);
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

        optionsPanel.setPreferredSize(new Dimension(userPanel.getWidth(), 75));
        optionsPanel.setBackground(colorWhite);
        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));

        nextPageButton.setBackground(colorTeal);
        nextPageButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        nextPageButton.setIcon(new ImageIcon("src/main/java/org/example/arrow_right.png"));
        nextPageButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorLightGray));
        nextPageButton.addActionListener(this);
        nextPageButton.addChangeListener(this);
        nextPageButton.setFocusable(false);
        nextPageButton.setEnabled(false);

        previousPageButton.setBackground(colorTeal);
        previousPageButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        previousPageButton.setIcon(new ImageIcon("src/main/java/org/example/arrow_left.png"));
        previousPageButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorLightGray));
        previousPageButton.addActionListener(this);
        previousPageButton.addChangeListener(this);
        previousPageButton.setFocusable(false);
        previousPageButton.setEnabled(false);

        searchAnimeTextField.setPreferredSize(new Dimension(600, buttonHeight));
        searchAnimeTextField.setBackground(colorWhite);
        searchAnimeTextField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, colorLightGray));
        searchAnimeTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchAnimeTextField.setForeground(colorLightGray);
        searchAnimeTextField.addFocusListener(this);

        searchButton.setBackground(colorPink);
        searchButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        searchButton.setIcon(new ImageIcon("src/main/java/org/example/search_icon.png"));
        searchButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorLightGray));
        searchButton.addActionListener(this);
        searchButton.setFocusable(false);

        tableModel = new DefaultTableModel(null, tableHeaders) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultTable = new JTable(tableModel);
        resultTable.getColumnModel().getColumn(idColumnID).setMinWidth(0);
        resultTable.getColumnModel().getColumn(idColumnID).setMaxWidth(0);
        resultTable.getColumnModel().getColumn(idColumnID).setWidth(0);
        resultTable.getColumnModel().getColumn(imageColumnID).setCellRenderer(new ImageRenderer());
        resultTable.getColumnModel().getColumn(imageColumnID).setMinWidth(animeImageWidth);
        resultTable.getColumnModel().getColumn(imageColumnID).setMaxWidth(animeImageWidth);
        resultTable.getColumnModel().getColumn(imageColumnID).setWidth(animeImageWidth);
        resultTable.getColumnModel().getColumn(titleColumnID).setCellRenderer(new TooltipTableCellRenderer());
        resultTable.setRowHeight(animeImageHeight);
        resultTable.getTableHeader().setResizingAllowed(false);
        resultTable.getTableHeader().setReorderingAllowed(false);
        resultTable.setRowSelectionAllowed(true);
        resultTable.setColumnSelectionAllowed(false);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        titlePanel.add(titleLabel);
        searchPanel.add(searchAnimeTextField);
        searchPanel.add(searchButton);
        optionsPanel.add(previousPageButton);
        optionsPanel.add(nextPageButton);
        resultPanel.add(scrollPane, BorderLayout.CENTER);
        userPanel.add(searchPanel);
        userPanel.add(optionsPanel);
        bodyPanel.add(userPanel, BorderLayout.NORTH);
        bodyPanel.add(insideMarginPanel1, BorderLayout.WEST);
        bodyPanel.add(insideMarginPanel2, BorderLayout.EAST);
        bodyPanel.add(insideMarginPanel3, BorderLayout.SOUTH);
        bodyPanel.add(resultPanel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
        add(marginPanel1, BorderLayout.WEST);
        add(marginPanel2, BorderLayout.SOUTH);
        add(marginPanel3, BorderLayout.EAST);
        add(bodyPanel, BorderLayout.CENTER);
        revalidate();
    }

    @SneakyThrows
    private void searchAnime(String animeTitle) {
        tableModel.setRowCount(0);
        List<Anime> animeList = animeService.getAnimeFromTitle(animeTitle, offset);
        for (int i = 0; i < animeList.size(); i++) {
            Anime anime = animeList.get(i);
            tableModel.addRow(new String[] {
                String.valueOf(anime.getId()),
                null,
                anime.getTitle(),
                anime.getStatus().getString(),
                anime.getNumEpisodes(),
                anime.getMean()
            });
            ImageIcon image = new ImageIcon(new URL(anime.getImageUrl()));
            Image scaledImage =
                    image.getImage().getScaledInstance(animeImageWidth, animeImageHeight, Image.SCALE_DEFAULT);
            resultTable.setValueAt(new ImageIcon(scaledImage), i, imageColumnID);
        }
        nextPageButton.setEnabled(animeList.size() >= 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton && !searchAnimeTextField.getText().equals("Search Anime...")) {
            offset = 0;
            searchAnime(searchAnimeTextField.getText());
        } else if (e.getSource() == nextPageButton) {
            offset += 10;
            previousPageButton.setEnabled(true);
            searchAnime(searchAnimeTextField.getText());
        } else if (e.getSource() == previousPageButton) {
            offset -= 10;
            previousPageButton.setEnabled(offset > 0);
            searchAnime(searchAnimeTextField.getText());
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == searchAnimeTextField
                && searchAnimeTextField.getText().equals("Search Anime...")) {
            searchAnimeTextField.setText("");
            searchAnimeTextField.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == searchAnimeTextField
                && searchAnimeTextField.getText().isEmpty()) {
            searchAnimeTextField.setText("Search Anime...");
            searchAnimeTextField.setForeground(colorLightGray);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!nextPageButton.isEnabled()) {
            nextPageButton.setBackground(colorLightGray);
        } else {
            nextPageButton.setBackground(colorTeal);
        }
        if (!previousPageButton.isEnabled()) {
            previousPageButton.setBackground(colorLightGray);
        } else {
            previousPageButton.setBackground(colorTeal);
        }
    }
}
