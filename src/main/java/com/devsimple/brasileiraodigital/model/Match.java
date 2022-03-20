package com.devsimple.brasileiraodigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor @Data
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "visited_id")
    private Team team2;

    private Boolean finished;
    private LocalDateTime date;
    private Integer round;
    private Integer goalsHome;
    private Integer goalsVisited;

}
