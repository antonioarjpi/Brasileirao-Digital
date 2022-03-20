package com.devsimple.brasileiraodigital.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor
public class EndMatchDTO {

    private Long partida;
    private String home;
    private String visited;
    private String stadium;
    private Boolean finished;
    private LocalDateTime date;
    private Integer round;
    private Integer goalsHome;
    private Integer goalsVisited;

}
