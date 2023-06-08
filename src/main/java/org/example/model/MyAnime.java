package org.example.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MyAnime extends Anime {
    private Integer score;
    private Integer progress;
    private Integer progressMax;
    private String note;
    private String added;
    private String finished;
}
