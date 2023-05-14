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
    private String title;
    private String imageUrl;
    private double mean;
    private Status status;
    //    private String genre;
    //    private String studios;
    private int numEpisodes;
}
