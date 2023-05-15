package org.example;

import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        new AnimeListWindow();
        //        AnimeService animeService = new AnimeService();
        //        HttpResponse<String> response =
        // animeService.getResponse("https://api.myanimelist.net/v2/anime?q=Pokemon");
        //        List<Integer> list = animeService.getAnimeIds("Pokemon", 0);
        //        List<Anime> animeList = animeService.getAnimeFromId(list);
        //        animeService.getAnimeFromId(list).forEach(System.out::println);
    }
}
