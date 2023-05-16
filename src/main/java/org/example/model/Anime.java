package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Anime {
    private Integer id;
    private String imageUrl;
    private String title;
    private Status status;
    private String numEpisodes;
    private String mean;
    //    private String genre;
    //    private String studios;
}
