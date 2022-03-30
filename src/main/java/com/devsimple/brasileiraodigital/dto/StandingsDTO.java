package com.devsimple.brasileiraodigital.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StandingsDTO implements Comparable<StandingsDTO>{

    private Long id;
    private String team;
    private Integer rank;
    private Integer points;
    private Integer gamesPlayed;
    private Integer wins;
    private Integer draws;
    private Integer defeat;
    private Integer goals;
    private Integer goalsAllowed;

    @Override
    public int compareTo(StandingsDTO o) {
        return this.getPoints().compareTo(o.getPoints());
    }

}
