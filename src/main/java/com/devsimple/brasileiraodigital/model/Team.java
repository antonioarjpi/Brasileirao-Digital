package com.devsimple.brasileiraodigital.model;

import com.devsimple.brasileiraodigital.dto.TeamDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(length = 20)
    private String name;

    @NotBlank @Column(length = 3)
    private String acronym;

    @NotBlank @Column(length = 35)
    private String stadium;

}
