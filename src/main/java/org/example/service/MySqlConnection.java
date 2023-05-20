package org.example.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.example.model.MyAnime;
import org.example.model.Status;

public class MySqlConnection {
    private final Connection connection;

    @SneakyThrows
    public MySqlConnection() {
        String URL = "jdbc:mysql://localhost:3306/myanimelist";
        String USERNAME = "root";
        String PASSWORD = "root";
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        closeConnection();
    }

    @SneakyThrows
    public List<MyAnime> getMyAnimeList() {
        List<MyAnime> myAnimeList = new ArrayList<>();
        String query = "SELECT * FROM animelistdata";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            MyAnime myAnime = MyAnime.builder()
                    .id(resultSet.getInt("id"))
                    .imageUrl(resultSet.getString("imageUrl"))
                    .title(resultSet.getString("title"))
                    .state(Status.valueOf(resultSet.getString("state")))
                    .score(resultSet.getInt("score"))
                    .progress(resultSet.getInt("progress"))
                    .progressMax(resultSet.getInt("progressMax"))
                    .note(resultSet.getString("note"))
                    .added(resultSet.getString("added"))
                    .finished(resultSet.getString("finished"))
                    .build();
            myAnimeList.add(myAnime);
        }
        resultSet.close();
        statement.close();
        return myAnimeList;
    }

    @SneakyThrows
    public void addAnime(int id, String title, String imageUrl, int numEpisodes, String state) {
        String query = "INSERT INTO animelistdata (id, title, imageurl, progress_max, state) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, title);
        preparedStatement.setString(3, imageUrl);
        preparedStatement.setInt(4, numEpisodes);
        preparedStatement.setString(5, state);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @SneakyThrows
    public void updateProgress(int id, int numEpisodes) {
        String query = "UPDATE animelistdata SET progress = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, numEpisodes);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @SneakyThrows
    public void removeAnime(int id) {
        String query = "DELETE FROM animelistdata WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @SneakyThrows
    public void closeConnection() {
        connection.close();
    }
}
