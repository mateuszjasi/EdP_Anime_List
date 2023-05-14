package org.example;

import java.net.http.HttpResponse;
import java.util.List;
import lombok.SneakyThrows;
import org.example.model.Anime;
import org.example.service.AnimeService;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        AnimeService animeService = new AnimeService();
        HttpResponse<String> response = animeService.getResponse("https://api.myanimelist.net/v2/anime?q=Pokemon");
        //        List<Integer> list = animeService.getAnimeIds("Pokemon", 10);
        List<Integer> list = animeService.getAnimeIds("Pokemon", 0);
        List<Anime> animeList = animeService.getAnimeFromId(list);

        //        animeService.getAnimeFromId(list).forEach(System.out::println);
    }
}
