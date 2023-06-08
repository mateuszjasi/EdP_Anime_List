package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Anime {
    private Integer id;
    private String imageUrl;
    private String title;
    private Status status;
    private String numEpisodes;
    private String mean;
}
