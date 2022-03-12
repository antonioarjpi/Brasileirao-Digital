package com.devsimple.brasileiraodigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor @Data
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "home_id")
    private Team home;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "visited_id")
    private Team visited;

    private Integer goalsHome;
    private Integer goalsVisited;

}
