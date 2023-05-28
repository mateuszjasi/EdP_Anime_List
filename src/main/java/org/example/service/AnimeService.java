package org.example.service;

import com.google.gson.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.model.Anime;
import org.example.model.Status;
import org.example.panel.SearchPanel.SearchPanelController;

@RequiredArgsConstructor
public class AnimeService {
    private final String URL = "https://api.myanimelist.net/v2/anime";

    @SneakyThrows
    public HttpResponse<String> getResponse(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String VALUE = "dc7b2de23341fdd30dbea0949bf6c5e1";
        String KEY = "X-MAL-CLIENT-ID";
        HttpRequest request =
                HttpRequest.newBuilder().header(KEY, VALUE).uri(new URI(url)).build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public List<Integer> getAnimeIds(String title, int offset) {
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String response =
                getResponse(URL + "?offset=" + offset + "&q=" + encodedTitle).body();

        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

        if (jsonObject.has("error")) {
            return Collections.emptyList();
        } else {
            JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();

            return IntStream.range(0, jsonArray.size())
                    .mapToObj(i -> jsonArray
                            .get(i)
                            .getAsJsonObject()
                            .get("node")
                            .getAsJsonObject()
                            .get("id")
                            .getAsInt())
                    .collect(Collectors.toList());
        }
    }

    public List<Anime> getAnimeFromTitle(String title, int offset, SearchPanelController controller) {
        List<Integer> list = getAnimeIds(title, offset);
        List<Anime> animeList = new ArrayList<>();

        for (Integer id : list) {
            String response = getResponse(URL + "/" + id + "?fields=id,title,main_picture,mean,status,num_episodes")
                    .body();
            JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

            JsonElement meanElement = jsonObject.get("mean");
            String mean = meanElement != null ? meanElement.getAsString() : "N/A";

            Anime anime = Anime.builder()
                    .id(id)
                    .title(jsonObject.get("title").getAsString())
                    .imageUrl(jsonObject
                            .get("main_picture")
                            .getAsJsonObject()
                            .get("large")
                            .getAsString())
                    .mean(mean)
                    .status(Status.valueOf(jsonObject.get("status").getAsString()))
                    .numEpisodes(
                            jsonObject.get("num_episodes").getAsInt() != 0
                                    ? jsonObject.get("num_episodes").getAsString()
                                    : "?")
                    .build();
            animeList.add(anime);
            controller.updateProgressBar();
        }

        return animeList;
    }

    public Anime getAnimeFromId(int id) {
        String response = getResponse(URL + "/" + id + "?fields=id,title,main_picture,num_episodes")
                .body();
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

        return Anime.builder()
                .id(id)
                .title(jsonObject.get("title").getAsString())
                .imageUrl(jsonObject
                        .get("main_picture")
                        .getAsJsonObject()
                        .get("large")
                        .getAsString())
                .numEpisodes(
                        jsonObject.get("num_episodes").getAsInt() != 0
                                ? jsonObject.get("num_episodes").getAsString()
                                : "?")
                .build();
    }
}
