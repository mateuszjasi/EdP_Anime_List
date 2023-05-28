package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MyAnime {
    private Integer id;
    private String imageUrl;
    private String title;
    private Status status;
    private Integer score;
    private Integer progress;
    private Integer progressMax;
    private String note;
    private String added;
    private String finished;
}
