package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MyAnime {
    private Integer id;
    private String imageUrl;
    private String title;
    private Status state;
    private Integer score;
    private Integer progress;
    private Integer progressMax;
    private String note;
    private String added;
    private String finished;
}
