package com.devsimple.brasileiraodigital.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class TeamDTO {

    private Long id;
    private String name;
    private String acronym;
    private String stadium;

}
