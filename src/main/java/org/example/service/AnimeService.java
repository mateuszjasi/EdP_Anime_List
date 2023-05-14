package org.example.service;

import com.google.gson.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.model.Anime;
import org.example.model.Status;

@RequiredArgsConstructor
public class AnimeService {

    private final String KEY = "X-MAL-CLIENT-ID";
    private final String VALUE = "dc7b2de23341fdd30dbea0949bf6c5e1";
    private final String URL = "https://api.myanimelist.net/v2/anime";

    @SneakyThrows
    public HttpResponse<String> getResponse(String url) {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request =
                HttpRequest.newBuilder().header(KEY, VALUE).uri(new URI(url)).build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public List<Integer> getAnimeIds(String title, int offset) {
        String response = getResponse(URL + "?offset=" + offset + "&q=" + title).body();

        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
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

    public List<Anime> getAnimeFromId(List<Integer> list) {
        List<Anime> animeList = new ArrayList<>();

        for (Integer id : list) {
            String response = getResponse(URL + "/" + id
                            + "?fields=id,title,main_picture,mean,status,media_type,genres,studios,num_episodes")
                    .body();
            JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

            Anime anime = Anime.builder()
                    .id(id)
                    .title(jsonObject.get("title").getAsString())
                    .imageUrl(jsonObject
                            .get("main_picture")
                            .getAsJsonObject()
                            .get("large")
                            .getAsString())
                    .mean(jsonObject.get("mean").getAsDouble())
                    .status(Status.valueOf(jsonObject.get("status").getAsString()))
                    .numEpisodes(jsonObject.get("num_episodes").getAsInt())
                    .build();

            animeList.add(anime);
        }

        return animeList;
    }
}
