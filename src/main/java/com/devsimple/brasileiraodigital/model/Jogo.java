package com.devsimple.brasileiraodigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor @Data
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "home_id")
    private Time home;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "visited_id")
    private Time visited;

    private Integer goalsHome;
    private Integer goalsVisited;

}
