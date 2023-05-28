package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Anime {
    private Integer id;
    private String imageUrl;
    private String title;
    private Status status;
    private String numEpisodes;
    private String mean;
}
