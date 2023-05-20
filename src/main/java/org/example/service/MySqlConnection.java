package org.example.service;

import lombok.SneakyThrows;
import org.example.model.MyAnime;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
    private final Connection connection;

    @SneakyThrows
    public MySqlConnection() {
        String URL = "jdbc:mysql://localhost:3306/myanimelist";
        String USERNAME = "root";
        String PASSWORD = "root";
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public MyAnime getMyAnimeList() {
        return new MyAnime();
    }

    public void addAnime(int id, String title, String imageUrl, int numEpisodes, String state) {

    }

    public void updateProgress(int id, int numEpisodes) {

    }

    public void removeAnime(int id) {

    }

    @SneakyThrows
    public void closeConnection() {
        connection.close();
    }
}
